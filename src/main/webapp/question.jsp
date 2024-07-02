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
    .modal {
        display: none;
        /* Hidden by default */
        position: fixed;
        /* Stay in place */
        z-index: 1;
        /* Sit on top */
        padding-top: 100px;
        /* Location of the box */
        left: 0;
        top: 0;
        width: 100%;
        /* Full width */
        height: 100%;
        /* Full height */
        overflow: auto;
        /* Enable scroll if needed */
        background-color: rgb(0, 0, 0);
        /* Fallback color */
        background-color: rgba(0, 0, 0, 0.4);
        /* Black w/ opacity */
    }


        /* Modal Content */

    .modal-content {
        background: #a4363e;
        padding: 40px;
        border-radius: 30px;
        /*box-shadow: 5px 6px 0px -2px #620d15, -6px 5px 0px -2px #620d15,
            0px -2px 0px 2px #ee9191, 0px 10px 0px 0px #610c14,
            0px -10px 0px 1px #e66565, 0px 0px 180px 90px #0d2f66;*/
        width: 640px;
        margin: 0 auto;
    }

    .content-ask {
        font-family: "Skranji", cursive;
        background: radial-gradient(#fffbf3, #ffe19e);
        padding: 24px;
        box-sizing: border-box;
        border-radius: 20px 18px 20px 18px;
        box-shadow: 0px 0px 0px 6px #5e1e21, 0px 0px 8px 6px #84222b,
            inset 0px 0px 15px 0px #614506, 6px 6px 1px 1px #e66565,
            -6px 6px 1px 1px #e66565;
        text-align: center;
    }

    .content-ask p {
        font-size: 56px;
        padding: 40px;
        box-sizing: border-box;
        color: #461417;
    }

    .buttons-confirm {
        margin-top: 40px;
        display: flex;
        justify-content: normal;
        align-items: center;
        gap: 30px;
        box-sizing: border-box;
    }

    .buttons-confirm button {
        padding: 20px;
        flex: 1;
        border-radius: 20px;
        border: 2px solid #49181e;
        color: #fff;
        font-size: 32px;
        text-shadow: 1px 2px 3px #000000;
        cursor: pointer;
    }

    #yes-button {
        background: linear-gradient(#ced869, #536d1b);
        box-shadow: 0px 0px 0px 4px #7e1522, 0px 2px 0px 3px #e66565;
    }
    
    #no-button {
        background: linear-gradient(#ea7079, #891a1a);
        box-shadow: 0px 0px 0px 4px #7e1522, 0px 2px 0px 3px #e66565;
    }

</style>
</head>
<%
    String correct = "";
    if (request.getAttribute("CORRECT_ANSWER") != null) {
        correct = (String)request.getAttribute("CORRECT_ANSWER");
    }
    Integer idQuestion = 2;
    if (request.getParameter("id") != null) {
        idQuestion = Integer.valueOf(request.getParameter("id"));
        idQuestion++;
    }
%>
<body>
    <div class="container">
        <div class="container-left">
            <div class="question">
                <h1>${QUESTION}</h1>
            </div>
            <ol class="listQuestions">
                <li class="question-li" onclick="areYouRight('one')"><div class="number one">1</div><div class="answer" id="one">${ANSWER_ONE}</div></li>
                <li class="question-li" onclick="areYouRight('two')"><div class="number two">2</div><div class="answer" id="two">${ANSWER_TWO}</div></li>
                <li class="question-li" onclick="areYouRight('three')"><div class="number three">3</div><div class="answer" id="three">${ANSWER_THREE}</div></li>
                <li class="question-li" onclick="areYouRight('four')"><div class="number four">4</div><div class="answer" id="four">${ANSWER_FOUR}</div></li>
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
        <div id="areYouSure" class="modal">
            <!-- Modal content -->
            <div class="modal-content">
              <div class="content-ask">
                <p>Você está certo disso?</p>
              </div>
              <div class="buttons-confirm">
                <button id="yes-button">Sim</button><button id="no-button">Não</button>
              </div>
            </div>
        </div>
        <div id="correctAnswer" class="modal">
            <!-- Modal content -->
            <div class="modal-content">
              <div class="content-ask">
                <p>Certa resposta</p>
              </div>
              <form class="buttons-confirm" method="post" action="question?id=<%=idQuestion%>">
                <button type="submit" id="yes-correct">Continuar</button>
              </form>
            </div>
        </div>
        <div id="incorrectAnswer" class="modal">
            <!-- Modal content -->
            <div class="modal-content">
              <div class="content-ask">
                <p>Que pena. Você errou!</p>
                <p>A resposta correta é <strong><%=correct%>.</strong></p>
                <p>Você ganhou R$ ${VALUE_WRONG}</p>
              </div>
              <form class="buttons-confirm" method="get" action="/">
                <button type="submit" id="yes-incorrect">Continuar</button>
              </form>
            </div>
        </div>
    </div>
    <script>
        function areYouRight(id) {
            var value = document.getElementById(id).textContent;
            var modalSure = document.getElementById("areYouSure");
            var modalCorrect = document.getElementById("correctAnswer");
            var modalIncorrect = document.getElementById("incorrectAnswer");
            var number = document.querySelector("." + id);
            var correctAnswer = '<%=correct%>';
            modalSure.style.display = "block";
            number.style.backgroundColor = "red";
            number.style.color = "black";
            document.getElementById("yes-button").onclick = function() {
                modalSure.style.display = "none";
                console.log(value);
                console.log(correctAnswer);
                if (value === correctAnswer) {
                    modalCorrect.style.display = "block";
                } else {
                    modalIncorrect.style.display = "block";
                }
            }
            // Close modal when button with id "yes-button" is clicked
            document.getElementById("no-button").onclick = function() {
                modalSure.style.display = "none";
                number.style.backgroundColor = "#fff";
                number.style.color = "blue";
            }
        }
    </script>
</body>
</html>