# Show do Milhão

Projeto pessoal inspirado no clássico programa e jogo Show do Milhão.

O jogo tem as seguintes funcionalidades:

* Acumulação do prêmio conforme o participante for acertando as perguntas, caso escolha parar vai receber como prêmio o valor adquirido na questão anterior, se errar vai receber a metade do valor adquirido na questão anterior.
* Contagem regressiva de 40 segundos para o participante responder cada questão.
* Opções de ajuda: Universitários, Placas e Cartas. Cada ajuda pode ser usada apenas uma vez por partida.
* Opção para pular a questão. Cada partida tem 3 pulos disponíveis.
* 8 categorias de questões para o participante escolher: "Ciências", "Conhecimentos gerais", "Esportes", "Filmes, desenhos e televisão", "Geografia", "História e política", "Matemática", 'Português e literatura", além da opção "Todos" para serem sorteadas questões de qualquer categoria durante a partida.
* Ranking com as 10 melhores pontuações.
* Voz do Silvio Santos e sons clássicos do Show do Milhão.

## Dependências e tecnologias

* Java JDK 17
* Spring
* Apache Tomcat
* MongoDB
* Docker
* JUnit

## Preparando o ambiente

Execute os seguintes comandos para configurar o banco de dados utilizado no jogo criando um cluster fragmentado utilizando Docker:

Criar uma rede no Docker onde apenas os componentes do Cluster vão acessar e executar:

`docker network create ShowDoMilhao`

Criar os containers com as imagens Mongo para os servidores de configuração, no qual será um conjunto de réplicas de três nós rodando na porta 27018:

`docker run --name mongo-config1 --net ShowDoMilhao -d mongo mongod --configsvr --replSet serverConfig --port 27018`

`docker run --name mongo-config2 --net ShowDoMilhao -d mongo mongod --configsvr --replSet serverConfig --port 27018`

`docker run --name mongo-config3 --net ShowDoMilhao -d mongo mongod --configsvr --replSet serverConfig --port 27018`

Acessar o bash do container do nó primário para acessar o servidor:

`docker exec -it mongo-config1 mongosh --port 27018`

Como esse servidor será um conjunto de réplicas, precisamos iniciá-lo:

```
rs.initiate({
    _id: "serverConfig",
    configsvr: true,
    members: [
        {_id: 0, host: "mongo-config1:27018"},
        {_id: 1, host: "mongo-config2:27018"},
        {_id: 2, host: "mongo-config3:27018"}
        ]
});
```

Agora vamos criar os fragmentos, servidores que vão armazenar os dados. Criaremos 2 fragmentos, cada um terá 3 nós:

`docker run --name mongo-shard1a --net ShowDoMilhao -d mongo mongod --shardsvr --replSet shard1 --port 27019`

`docker run --name mongo-shard1b --net ShowDoMilhao -d mongo mongod --shardsvr --replSet shard1 --port 27019`

`docker run --name mongo-shard1c --net ShowDoMilhao -d mongo mongod --shardsvr --replSet shard1 --port 27019`

`docker run --name mongo-shard2a --net ShowDoMilhao -d mongo mongod --shardsvr --replSet shard2 --port 27020`

`docker run --name mongo-shard2b --net ShowDoMilhao -d mongo mongod --shardsvr --replSet shard2 --port 27020`

`docker run --name mongo-shard2c --net ShowDoMilhao -d mongo mongod --shardsvr --replSet shard2 --port 27020`

Agora precisamos inicializar o conjunto de réplicas dos fragmentos:

Para o fragmento 1:

`docker exec -it mongo-shard1a mongosh --port 27019`

```
rs.initiate({
    _id: "shard1",
    members: [
        {_id: 0, host: "mongo-shard1a:27019"},
        {_id: 1, host: "mongo-shard1b:27019"},
        {_id: 2, host: "mongo-shard1c:27019"}
        ]
});
```

Para o fragmento 2:

`docker exec -it mongo-shard2a mongosh --port 27020`

```
rs.initiate({
    _id: "shard2",
    members: [
        {_id: 0, host: "mongo-shard2a:27020"},
        {_id: 1, host: "mongo-shard2b:27020"},
        {_id: 2, host: "mongo-shard2c:27020"}
        ]
});
```

Agora vamos criar o roteador (mongos) que vai se conectar com os shards e os servidores de configuração na porta 27021:

`docker run -p 27021:27021 --name mongo-router --net ShowDoMilhao -d mongo mongos --port 27021 --configdb serverConfig/mongo-config1:27018,mongo-config2:27018,mongo-config3:27018 --bind_ip_all`

Agora vamos adicionar as shard (fragmentos) ao servidor de roteamento:

`docker exec -it mongo-router mongosh --port 27021`

`sh.addShard("shard1/mongo-shard1a:27019","shard1/mongo-shard1b:27019","shard1/mongo-shard1c:27019")`

`sh.addShard("shard2/mongo-shard2a:27020","shard2/mongo-shard2b:27020","shard2/mongo-shard2c:27020")`

Após realizar todo o processo acima para criar o cluster fragmentado, recomendo usar as ferramentas Mongodb Compass e NoSQL Booster conectando no cluster fragmento através da porta 27021.

No NoSQL Booster, execute os seguintes comandos respectivamente para criar o usuário administrador, o banco de dados e as coleções fragmentadas:

`use admin`

```
db.createUser({
    user: "admin",
    pwd: "admin123",
    roles: [
        {role: "userAdminAnyDatabase", db: "admin"},
        {role: "readWriteAnyDatabase", db: "admin"}
        ]
})
```

`use Show_do_Milhao`

```
db.createCollection("questions",
{
    validator: {
        $jsonSchema: {
          bsonType: "object",
          "additionalProperties": false,
          required: [ "_id", "id", "question", "correct_answer", "difficulty", "category", "incorrect_answers" ],
          properties: {
             _id: {
                bsonType: "objectId",
                description: "Mongo object Id"
             },
             id: {
                bsonType: "int",
                description: "must be an integer and is required"
             },
             question: {
                bsonType: "string",
                description: "must be a string and is required"
             },
             correct_answer: {
                bsonType: "string",
                description: "must be a string and is required"
             },
             difficulty: {
                 enum: [ "easy", "medium", "hard" ],
                 description: "can only be one of the enum values and is required"
             },
             category: {
                enum: [ "Conhecimentos gerais", "Português e literatura", "Ciências", "História e política", "Matemática", "Filmes, desenhos e televisão", "Geografia", "Esportes" ],
                description: "can only be one of the enum values and is required"
             },
             gpa: {
                bsonType: [ "double" ],
                description: "must be a double if the field exists"
             },
             incorrect_answers: {
                bsonType: "array",
                minItems: 3,
                maxItems: 3,
                items: {
                    bsonType: "string"
                },
                description: "array of 3 elements of string type"
             }
          }
        }
    }
})
```

```
db.createCollection("scores", 
{
    validator: {
        $jsonSchema: {
          bsonType: "object",
          required: [ "name", "premium" ],
          properties: {
             name: {
                bsonType: "string",
                description: "must be a string and is required"
             },
             premium: {
                bsonType: "double",
                description: "must be a double and is required"
             }
          }
        }
    }
})
```

Após criar o usuário, o banco de dados e as coleções, vamos fragmentar as coleções criadas. No bash do container do roteador, ou seja, o mongos que está executando na porta 27021, execute os seguintes comandos:

`sh.shardCollection("Show_do_Milhao.questions",{_id:1})`

`sh.shardCollection("Show_do_Milhao.scores",{_id:1})`

Agora, no MongoDB Compass, importe na coleção *questions* o arquivo *questions.json* localizado no diretório raiz deste projeto para adicionar todas as questões no banco de dados.