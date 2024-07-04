<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="showdomilhao.model.Guests" import="showdomilhao.model.Participant" %>
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
    Integer currentIndex = 2;
    if (request.getParameter("id") != null) {
        idQuestion = Integer.valueOf(request.getParameter("id"));
        currentIndex = idQuestion;
        idQuestion++;
    }
    String valuePremium = "";
    if (request.getAttribute("VALUE_PREMIUM") != null) {
        valuePremium = (String)request.getAttribute("VALUE_PREMIUM");
    }
    String valueStop = "";
    if (request.getAttribute("VALUE_STOP") != null) {
        valueStop = (String)request.getAttribute("VALUE_STOP");
    }
    Guests guests = (Guests) request.getAttribute("GUESTS");
    Boolean canGuests = guests.getAvailable();
    Participant user = (Participant) request.getAttribute("PARTICIPANT");
    String nameUser = user.getName();
    int canSkip = user.getCanSkip();
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
                    <div class="premium-value">R$ ${VALUE_WRONG}</div>
                    <div class="text">errar</div>
                </div>
                <div>
                    <div class="premium-value">R$ ${VALUE_STOP}</div>
                    <div class="text">parar</div>
                </div>
                <div>
                    <div class="premium-value">R$ ${VALUE_PREMIUM}</div>
                    <div class="text">acertar</div>
                </div>
            </div>
        </div>
        <div class="container-right">
            <div class="people">
                <h2 class="name-user"><%=nameUser%></h2>
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
            <div class="content-help">
                <span class="close-menu">x</span>
                <div class="guests">
                    <img class="icon-help" id="select-guests" src="./img/2639885_people_icon.svg">
                    <p id="legend-guests">Convidados</p>
                </div>
                <div class="plaques">
                    <img class="icon-help" id="select-plaques" src="./img/PLACA-SINALIZE-DE-SINALIZACAO-NUMERAL-0-A-9-25X5CM.jpg">
                    <p>Placas</p>
                </div>
                <div class="cards">
                    <img class="icon-help" id="select-cards" src="./img/7455803-cartas-de-baralho-numero-3-vetor.jpg">
                    <p>Cartas</p>
                </div>
                <div class="skip">
                    <form name="skipSubmit" action="/question?id=<%=currentIndex%>" method="post">
                        <input type="hidden" name="skipAvailable" value="" />
                        <button type="submit" class="icon-help" id="select-skip"><img src="./img/912603-200.png" id="img-skip"></button>
                        <p id="legend-skip">Pular</p>
                    </form>
                </div>
            </div>
            <div class="content-guests">
                <span class="close-guests">x</span>
                <div class="guest">
                    <img class="icon-help" src="./img/person.svg">
                    <p id="answer-guest-1"></p>
                </div>
                <div class="guest">
                    <img class="icon-help" src="./img/person.svg">
                    <p id="answer-guest-2"></p>
                </div>
                <div class="guest">
                    <img class="icon-help" src="./img/person.svg">
                    <p id="answer-guest-3"></p>
                </div>
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
                <% if (!valuePremium.equals("1000000")) { %>
                <input type="hidden" name="guestsAvailable" value=""/>
                <% } %>
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
                    var premium = '<%=valuePremium%>';
                    if (premium === '1000000') {
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
            document.getElementById("modal-help").style.display = "block";
            if ('<%=canGuests%>' === "false") {
                var buttonGuests = document.getElementById("select-guests");
                buttonGuests.style.pointerEvents = "none";
                buttonGuests.style.opacity = "0.4";
                document.getElementById("legend-guests").style.textDecoration = "line-through";
            }
            if ('<%=canSkip%>' === "0") {
                var buttonSkip = document.getElementById("select-skip");
                var imgSkip = document.getElementById("img-skip");
                buttonSkip.style.pointerEvents = "none";
                imgSkip.style.pointerEvents = "none";
                imgSkip.style.opacity = "0.4";
                document.getElementById("legend-skip").style.textDecoration = "line-through";
            }
        }

        document.querySelector(".button-stop").onclick = function() {
            document.getElementById("sad").textContent = "";
            document.getElementById("incorrectAnswer").style.display = "block";
            document.getElementById("value-stop").textContent = 'R$ <%=valueStop%>';
        }

        document.querySelector(".close-menu").onclick = function() {
            document.getElementById("modal-help").style.display = "none";
        }

        document.querySelector(".close-guests").onclick = function() {
            var buttonGuests = document.getElementById("select-guests");
            document.getElementById("modal-help").style.display = "none";
            document.querySelector(".content-help").style.display = "flex";
            document.querySelector(".content-guests").style.display = "none";
            buttonGuests.style.pointerEvents = "none";
            buttonGuests.style.opacity = "0.4";
            document.getElementById("legend-guests").style.textDecoration = "line-through";

        }

        document.getElementById("select-guests").onclick = function() {
            if ('<%=canGuests%>'==="true") {
                document.querySelector(".content-help").style.display = "none";
                document.querySelector(".content-guests").style.display = "flex";
                document.formSubmit.guestsAvailable.value = "false";
                var correctOption = 0;
                var options = document.querySelectorAll(".question-li");
                    for (var i=0; i<4; i++) {
                        var option = options[i];
                        var answer = option.querySelector(".answer");
                        if (answer.textContent === '<%=correct%>') {
                            correctOption = option.querySelector(".number").textContent;
                            break;
                        }
                    }
                var incorrectOption = correctOption;
                while (incorrectOption == correctOption) {
                    incorrectOption = Math.floor(Math.random()*4);
                }
                var incorrectGuest = Math.floor(Math.random()*4);
                incorrectOption++;
                switch(incorrectGuest) {
                    case 0:
                        document.getElementById("answer-guest-1").textContent = incorrectOption;
                        document.getElementById("answer-guest-2").textContent = correctOption;
                        document.getElementById("answer-guest-3").textContent = correctOption;
                        break;
                    case 1:
                        document.getElementById("answer-guest-1").textContent = correctOption;
                        document.getElementById("answer-guest-2").textContent = incorrectOption;
                        document.getElementById("answer-guest-3").textContent = correctOption;
                        break;
                    case 2:
                        document.getElementById("answer-guest-1").textContent = correctOption;
                        document.getElementById("answer-guest-2").textContent = correctOption;
                        document.getElementById("answer-guest-3").textContent = incorrectOption;
                    default:
                        document.getElementById("answer-guest-1").textContent = correctOption;
                        document.getElementById("answer-guest-2").textContent = correctOption;
                        document.getElementById("answer-guest-3").textContent = correctOption;
                        break;
                }
            }
        }

        document.getElementById("select-skip").onclick = function() {
            var canSkip = '<%=canSkip%>';
            if (canSkip > 0) {
                canSkip--;
                canSkip.toString();
                document.skipSubmit.skipAvailable.value = canSkip;
            }
        }

    </script>
</body>
</html>