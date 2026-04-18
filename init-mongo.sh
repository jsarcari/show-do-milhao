#!/bin/bash

echo "Aguardando containers (30s para garantir)..."
sleep 30

# 1. Config Servers
mongosh --host mongo-config1 --port 27018 --eval '
  if (!rs.status().ok) {
    rs.initiate({
      _id: "serverConfig",
      configsvr: true,
      members: [
        {_id: 0, host: "mongo-config1:27018"},
        {_id: 1, host: "mongo-config2:27018"},
        {_id: 2, host: "mongo-config3:27018"}
      ]
    });
  }
'

# 2. Shards (Repita a lógica de verificação se quiser limpar os logs)
mongosh --host mongo-shard1a --port 27019 --eval 'if (!rs.status().ok) rs.initiate({_id: "shard1", members: [{_id: 0, host: "mongo-shard1a:27019"},{_id: 1, host: "mongo-shard1b:27019"},{_id: 2, host: "mongo-shard1c:27019"}]});'
mongosh --host mongo-shard2a --port 27020 --eval 'if (!rs.status().ok) rs.initiate({_id: "shard2", members: [{_id: 0, host: "mongo-shard2a:27020"},{_id: 1, host: "mongo-shard2b:27020"},{_id: 2, host: "mongo-shard2c:27020"}]});'

sleep 15

# 3. Router - Onde estava o SyntaxError
echo "Finalizando configuração no Router..."
mongosh --host mongo-router --port 27021 --eval '
  // Adiciona Shards (ignora erro se já existirem)
  try { sh.addShard("shard1/mongo-shard1a:27019,mongo-shard1b:27019,mongo-shard1c:27019"); } catch(e){}
  try { sh.addShard("shard2/mongo-shard2a:27020,mongo-shard2b:27020,mongo-shard2c:27020"); } catch(e){}

  // Criar Usuário usando getSiblingDB em vez de "use admin"
  var adminDB = db.getSiblingDB("admin");
  if (!adminDB.getUser("admin")) {
    adminDB.createUser({
      user: "admin",
      pwd: "admin123",
      roles: [{role: "userAdminAnyDatabase", db: "admin"}, {role: "readWriteAnyDatabase", db: "admin"}]
    });
  }

  sh.enableSharding("Show_do_Milhao");
  
  // Criar coleção com shard key
  var showDB = db.getSiblingDB("Show_do_Milhao");
  if (!showDB.getCollectionNames().includes("questions")) {
     showDB.createCollection("questions");
     sh.shardCollection("Show_do_Milhao.questions", {_id: 1});
  }
'

# 4. Importação (Só importa se a coleção estiver vazia para não duplicar)
echo "Verificando importação..."
COUNT=$(mongosh --host mongo-router --port 27021 --quiet --eval 'db.getSiblingDB("Show_do_Milhao").questions.countDocuments()')

if [ "$COUNT" -eq 0 ]; then
    mongoimport --host mongo-router --port 27021 --db Show_do_Milhao --collection questions --file /questions.json --jsonArray
else
    echo "Dados já presentes ($COUNT registros). Pulando importação."
fi

echo "Ambiente pronto para o Show do Milhão!"