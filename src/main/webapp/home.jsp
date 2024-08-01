<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="img/Show_do_milhao.webp" />
<title>Show do Milhão</title>
<link rel="stylesheet" href="css/home.css" type="text/css" />
</head>
<body>
    <audio src="audio/silvio-santos-abertura-show-do-milhao.mp3" autoplay loop id="audio-home"></audio>
    <div id="audio">
        <img src="img/audio.svg" id="button-audio" />
        <div id="no-audio"></div>
    </div>
<div>
    <img src="./img/Jogodomilhao1999.webp" class="logo-game">
    <div class="buttons-home">
        <a href="/name" class="button-home">Jogar</a>
        <a href="/scores" class="button-home">Placar</a>
    </div>
</div>
<script type="text/javascript" src="./js/home.js"></script>
</body>
</html>