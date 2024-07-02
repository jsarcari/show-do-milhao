<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="img/Show_do_milhao.webp" />
<title>Show do Milhão</title>
<link rel="stylesheet" href="css/question.css" type="text/css" />
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
    String valueStop = "";
    if (request.getAttribute("VALUE_STOP") != null) {
        valueStop = (String)request.getAttribute("VALUE_STOP");
    }
%>
<body>
    <div class="container">
        <div class="container-left">
            <div class="question">
                <h1>${QUESTION}</h1>
            </div>
            <ol class="listQuestions">
                <li class="question-li li-one" onclick="areYouRight('one')"><div class="number one">1</div><div class="answer" id="one">${ANSWER_ONE}</div></li>
                <li class="question-li li-two" onclick="areYouRight('two')"><div class="number two">2</div><div class="answer" id="two">${ANSWER_TWO}</div></li>
                <li class="question-li li-three" onclick="areYouRight('three')"><div class="number three">3</div><div class="answer" id="three">${ANSWER_THREE}</div></li>
                <li class="question-li li-four" onclick="areYouRight('four')"><div class="number four">4</div><div class="answer" id="four">${ANSWER_FOUR}</div></li>
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
            <div class="people">
                <h2 class="name-user">${PARTICIPANT}</h2>
                <div class="body-head"></div>
                <div class="body-user"></div>
            </div>
            <div class="menu-help">
                <div>
                    <div class="button-help"></div>
                    <p>Ajuda</p>
                </div>
                <div>
                    <div class="button-stop"></div>
                    <p>Parar</p>
                </div>
            </div>
        </div>
        <div id="modal-help" class="modal">
            <div class="guests">
                <img src="./img/2639885_people_icon.svg">
                <p>Convidados</p>
            </div>
            <div class="plaques">

            </div>
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
                <p id="message">Certa resposta</p>
              </div>
              <form name="formSubmit" class="buttons-confirm" method="post" action="question?id=<%=idQuestion%>">
                <button type="submit" id="yes-correct">Continuar</button>
              </form>
            </div>
        </div>
        <div id="incorrectAnswer" class="modal">
            <!-- Modal content -->
            <div class="modal-content">
              <div class="content-ask">
                <p id="sad">Que pena. Você errou!</p>
                <p>Você ganhou <strong id="value-stop">R$ ${VALUE_WRONG}</strong></p>
              </div>
              <form class="buttons-confirm" method="get" action="/">
                <button type="submit" id="yes-incorrect">Continuar</button>
              </form>
            </div>
        </div>
    </div>
    <script>
        function areYouRight(id) {
            var divSelected = document.querySelector(".li-" + id);
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
                if (value === correctAnswer) {
                    var currentId = '<%=idQuestion%>';
                    if (currentId === '17') {
                        document.getElementById("message").textContent = "Parabéns! Você ganhou 1 milhão";
                        document.formSubmit.setAttribute("method","get");
                        document.formSubmit.action = "/";
                    }
                    modalCorrect.style.display = "block";
                    divSelected.style.backgroundColor = "#01b051";
                } else {
                    modalIncorrect.style.display = "block";
                    var options = document.querySelectorAll(".question-li");
                    for (var i=0; i<4; i++) {
                        var option = options[i];
                        var answer = option.querySelector(".answer");
                        if (answer.textContent === correctAnswer) {
                            option.style.backgroundColor = "#01b051";
                            break;
                        }
                    }
                }
            }
            // Close modal when button with id "yes-button" is clicked
            document.getElementById("no-button").onclick = function() {
                modalSure.style.display = "none";
                number.style.backgroundColor = "#fff";
                number.style.color = "blue";
            }
        }

        document.querySelector(".button-help").onclick = function() {
            document.getElementById("modal-help").style.display = "flex";
        }

        document.querySelector(".button-stop").onclick = function() {
            document.getElementById("sad").textContent = "";
            document.getElementById("incorrectAnswer").style.display = "block";
            document.getElementById("value-stop").textContent = 'R$ <%=valueStop%>';
        }

    </script>
</body>
</html>