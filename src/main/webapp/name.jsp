<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="img/Show_do_milhao.webp" />
<title>Show do Milhão</title>
<link rel="stylesheet" href="css/name.css" type="text/css" />
</head>
<body>
<div class="container">
  <img src="./img/Jogodomilhao1999.webp" class="logo-game">
  <form method="post" action="question?id=1" class="formUser">
    <div class="category-select">
        <label for="category" class="label-category">Escolha a categoria</label>
        <select name="category" class="select">
          <option value="Todos">Todos</option>
          <option value="Ciências">Ciências</option>
          <option value="Conhecimentos gerais">Conhecimentos gerais</option>
          <option value="Esportes">Esportes</option>
          <option value="Filmes, desenhos e televisão">Filmes, desenhos e televisão</option>
          <option value="Geografia">Geografia</option>
          <option value="História e política">História e política</option>
          <option value="Matemática">Matemática</option>
          <option value="Português e literatura">Português e literatura</option>
        </select>
      </div>
    <input type="text" name="name" placeholder="Digite o seu nome" class="input-name" required />
    <button class="button-play" type="submit">Iniciar</button>
</form>
</div>
</body>
</html>