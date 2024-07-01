<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show do Milhão</title>
<style>
    html, body, div, span, applet, object, iframe,
    h1, h2, h3, h4, h5, h6, p, blockquote, pre,
    a, abbr, acronym, address, big, cite, code,
    del, dfn, em, img, ins, kbd, q, s, samp,
    small, strike, strong, sub, sup, tt, var,
    b, u, i, center,
    dl, dt, dd, ul, fieldset, form, label, legend,
    table, caption, tbody, tfoot, thead, tr, th, td,
    article, aside, canvas, details, embed, 
    figure, figcaption, footer, header, hgroup, 
    menu, nav, output, ruby, section, summary,
    time, mark, audio, video {
        margin: 0;
        padding: 0;
        border: 0;
        font-family: Arial, Helvetica, sans-serif;
        vertical-align: baseline;
    }
    .container {
        display: flex;
        width: 100vw;
        height: 100vh;
    }
    .container-left {
        width: 50vw;
        height: 100vh;
        background-color: #2553a3;
    }
    .question {
        top: 10px;
        width: 48vw;
        height: 22vh;
        border-radius: 0px 30px 30px 0px;
        color: #fff;
        font-style: italic;
        font-weight: bold;
        text-transform: uppercase;
        padding: 10px 10px 10px 50px;
        background-color: #e20000;
        position: absolute;
        display: flex;
        align-items: center;
        font-size: 22px;
        text-shadow: 2px 2px 6px #000000;
    }
    .listQuestions {
        position: absolute;
        top: 260px;
        left: 10px;
        width: 40vw;
    }
    .question-li {
        height: 10vh;
        border-radius: 18px;
        color: #fff;
        font-style: italic;
        font-weight: bold;
        text-transform: uppercase;
        padding: 10px 10px 10px 50px;
        margin-bottom: 14px;
        background-color: #e20000;
        display: flex;
        align-items: center;
        font-size: 28px;
        text-shadow: 2px 2px 6px #000000;
        gap: 2%;
        cursor: pointer;
    }
    .number {
        border-radius: 50%;
        background-color: #fff;
        color: blue;
        font-size: 30px;
        padding: 2px 10px;
        text-shadow: none;
        font-style: normal;
    }
    .container-right {
        width: 50vw;
        height: 70vh;
        background-color: #d4d4d4;
    }
    #name-user {
        margin-top: 20px;
        text-align: center;
    }
    .body-head {
        width: 220px;
        height: 220px;
        border-radius: 50%;
        position: absolute;
        right: 18%;
        margin: 0 auto;
        background-color: #fff;
        bottom: 54%;
    }
    .body-user {
        width: 25vw;
        height: 25vh;
        background-color: #fff;
        position: absolute;
        bottom: 30vh;
        border-radius: 50% 50% 0 0;
        right: 12%;
        margin: 0 auto;
    }
    .premiums {
        display: flex;
        position: absolute;
        bottom: 30px;
        gap: 4%;
        left: 70px;
    }
    .premium-value {
        background-color: #ffc509;
        color: #e20000;
        font-weight: bold;
        font-size: 28px;
        padding: 12px;
        width: 150px;
        height: 30px;
        border-radius: 10px;
        text-align: center;
    }
    .text {
        color: #fff;
        font-style: italic;
        font-weight: bold;
        text-transform: uppercase;
        font-size: 22px;
        text-shadow: 2px 2px 6px #000000;
        text-align: center;
        padding-top: 10px;
    }
</style>
</head>
<%
    String question = "Hello!";
    if (request.getAttribute("QUESTION") != null) {
        question = (String)request.getAttribute("QUESTION");
    }
%>
<body>
    <div class="container">
        <div class="container-left">
            <div class="question">
                <h1><%= question %></h1>
            </div>
            <ol class="listQuestions">
                <li class="question-li" onclick="areYouRight('one')"> <div class="number">1</div> <div class="answer" id="one"> ${ANSWER_ONE}</div></li>
                <li class="question-li" onclick="areYouRight('two')"> <div class="number">2</div> <div class="answer" id="two"> ${ANSWER_TWO}</div></li>
                <li class="question-li" onclick="areYouRight('three')"> <div class="number">3</div> <div class="answer" id="three"> ${ANSWER_THREE}</div></li>
                <li class="question-li" onclick="areYouRight('four')"> <div class="number">4</div> <div class="answer" id="four"> ${ANSWER_FOUR}</div></li>
            </ol>
            <div class="premiums">
                <div>
                    <div class="premium-value">${VALUE_WRONG}</div>
                    <div class="text">errar</div>
                </div>
                <div>
                    <div class="premium-value">${VALUE_STOP}</div>
                    <div class="text">parar</div>
                </div>
                <div>
                    <div class="premium-value">${VALUE_PREMIUM}</div>
                    <div class="text">acertar</div>
                </div>
            </div>
        </div>
        <div class="container-right">
            <h2 id="name-user">${PARTICIPANT}</h2>
            <div class="body-head"></div>
            <div class="body-user"></div>
        </div>
    </div>
    <script>
        function areYouRight(id) {
            var value = document.getElementById(id).textContent;
            alert('Você está certo disso? ' + value);
        }
    </script>
</body>
</html>