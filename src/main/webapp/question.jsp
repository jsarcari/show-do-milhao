<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="showdomilhao.model.Guests" import="showdomilhao.model.Participant" import="showdomilhao.model.Plaques" import="showdomilhao.model.Cards" import="java.util.ArrayList" import="java.util.List" %>
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
    Plaques plaques = (Plaques) request.getAttribute("PLAQUES");
    Boolean canPlaques = plaques.getAvailable();
    List<Integer> listPlaques = new ArrayList<Integer>();
    if (request.getAttribute("LIST_PLAQUES") != null) {
        listPlaques = (List<Integer>)request.getAttribute("LIST_PLAQUES");
    } else {
        listPlaques.add(26);
        listPlaques.add(24);
        listPlaques.add(25);
        listPlaques.add(25);
    }
    Cards cards = (Cards) request.getAttribute("CARDS");
    Boolean canCards = cards.getAvailable();
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
                <li class="question-li li-one" onclick="areYouRight('one','<%=correct%>','<%=valuePremium%>')"><div class="number one">1</div><div class="answer" id="one">${ANSWER_ONE}</div></li>
                <li class="question-li li-two" onclick="areYouRight('two','<%=correct%>','<%=valuePremium%>')"><div class="number two">2</div><div class="answer" id="two">${ANSWER_TWO}</div></li>
                <li class="question-li li-three" onclick="areYouRight('three','<%=correct%>','<%=valuePremium%>')"><div class="number three">3</div><div class="answer" id="three">${ANSWER_THREE}</div></li>
                <li class="question-li li-four" onclick="areYouRight('four','<%=correct%>','<%=valuePremium%>')"><div class="number four">4</div><div class="answer" id="four">${ANSWER_FOUR}</div></li>
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
                    <p id="legend-plaques">Placas</p>
                </div>
                <div class="cards">
                    <img class="icon-help" id="select-cards" src="./img/card-2.jpg">
                    <p id="legend-cards">Cartas</p>
                </div>
                <div class="skip">
                    <form name="skipSubmit" action="question?id=<%=currentIndex%>" method="post">
                        <input type="hidden" name="skipAvailable" value="" />
                        <input type="hidden" name="nameUser" value="<%=nameUser%>" />
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
            <div class="content-plaques">
                <span class="close-plaques">x</span>
                <div class="plaque">
                    <img class="icon-help" src="./img/5009608119_1.jpg">
                    <p id="percent-plaque-1"></p>
                </div>
                <div class="plaque">
                    <img class="icon-help" src="./img/1091352_placa-em-aluminio-5x8cm-numero-2-sinali_s1_636271809534888000.jpg">
                    <p id="percent-plaque-2"></p>
                </div>
                <div class="plaque">
                    <img class="icon-help" src="./img/images.jpeg">
                    <p id="percent-plaque-3"></p>
                </div>
                <div class="plaque">
                    <img class="icon-help" src="./img/1091364_placa-em-aluminio-5x8cm-numero-4-sinali_m1_636271809800088000.jpg">
                    <p id="percent-plaque-4"></p>
                </div>
            </div>
            <div class="content-cards">
                <h3 class="title-help">Escolha uma carta:</h3>
                <p>K - Nenhuma alternativa eliminada.</p>
                <p>A - 1 alternativa eliminada.</p>
                <p>2 - 2 alternativas eliminadas.</p>
                <p>3 - 3 alternativas eliminadas.</p>
                <div class="container-cards">
                    <span class="close-cards">x</span>
                    <div class="card">
                        <img class="icon-card" src="./img/101503-de-cartao-de-jogo-gratuito-gratis-vetor.png" id="card-1">
                    </div>
                    <div class="card">
                        <img class="icon-card" src="./img/101503-de-cartao-de-jogo-gratuito-gratis-vetor.png" id="card-2">
                    </div>
                    <div class="card">
                        <img class="icon-card" src="./img/101503-de-cartao-de-jogo-gratuito-gratis-vetor.png" id="card-3">
                    </div>
                    <div class="card">
                        <img class="icon-card" src="./img/101503-de-cartao-de-jogo-gratuito-gratis-vetor.png" id="card-4">
                    </div>
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
                <input type="hidden" name="plaquesAvailable" value=""/>
                <input type="hidden" name="cardsAvailable" value=""/>
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
    <script type="text/javascript">
        document.querySelector(".button-help").onclick = function() {
            document.getElementById("modal-help").style.display = "block";
            if ('<%=canGuests%>' === "false") {
                var buttonGuests = document.getElementById("select-guests");
                buttonGuests.style.pointerEvents = "none";
                buttonGuests.style.opacity = "0.4";
                document.getElementById("legend-guests").style.textDecoration = "line-through";
            }
            if ('<%=canPlaques%>' === "false") {
                var buttonGuests = document.getElementById("select-plaques");
                buttonGuests.style.pointerEvents = "none";
                buttonGuests.style.opacity = "0.4";
                document.getElementById("legend-plaques").style.textDecoration = "line-through";
            }
            if ('<%=canSkip%>' === "0") {
                var buttonSkip = document.getElementById("select-skip");
                var imgSkip = document.getElementById("img-skip");
                buttonSkip.style.pointerEvents = "none";
                imgSkip.style.pointerEvents = "none";
                imgSkip.style.opacity = "0.4";
                document.getElementById("legend-skip").style.textDecoration = "line-through";
            }
            if ('<%=canCards%>' === "false") {
                var buttonCards = document.getElementById("select-cards");
                buttonCards.style.pointerEvents = "none";
                buttonCards.style.opacity = "0.4";
                document.getElementById("legend-cards").style.textDecoration = "line-through";
            }  
        }

        document.querySelector(".button-stop").onclick = function() {
            document.getElementById("sad").textContent = "";
            document.getElementById("incorrectAnswer").style.display = "block";
            document.getElementById("value-stop").textContent = 'R$ <%=valueStop%>';
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

        document.getElementById("select-plaques").onclick = function() {
            if ('<%=canPlaques%>'==="true") {
                document.querySelector(".content-help").style.display = "none";
                document.querySelector(".content-plaques").style.display = "flex";
                document.formSubmit.plaquesAvailable.value = "false";
                var correctOption = 0;
                var options = document.querySelectorAll(".question-li");
                var indexCorrect = 0;
                for (var i=0; i<4; i++) {
                    var option = options[i];
                    var answer = option.querySelector(".answer");
                    if (answer.textContent === '<%=correct%>') {
                        correctOption = option.querySelector(".number").textContent;
                        indexCorrect = i;
                        break;
                    }
                }
                var index = 1;
                var notUsed = '3';
                if (correctOption != '1') {
                    document.getElementById("percent-plaque-1").textContent = '<%=listPlaques.get(1)%>' + "%";
                } else {
                    document.getElementById("percent-plaque-1").textContent = '<%=listPlaques.get(0)%>' + "%";
                    notUsed = '1';
                }
                if (correctOption != '2') {
                    document.getElementById("percent-plaque-2").textContent = '<%=listPlaques.get(2)%>' + "%";
                } else {
                    document.getElementById("percent-plaque-2").textContent = '<%=listPlaques.get(0)%>' + "%";
                    notUsed = '2';
                }
                if (correctOption != '3') {
                    document.getElementById("percent-plaque-3").textContent = '<%=listPlaques.get(3)%>' + "%";
                } else {
                    document.getElementById("percent-plaque-3").textContent = '<%=listPlaques.get(0)%>' + "%";
                    notUsed = '3';
                }
                if (correctOption != '4') {
                    switch (notUsed) {
                        case '1':
                            document.getElementById("percent-plaque-4").textContent = '<%=listPlaques.get(1)%>' + "%";
                            break;
                        case '2':
                            document.getElementById("percent-plaque-4").textContent = '<%=listPlaques.get(2)%>' + "%";
                            break;
                        case '3':
                            document.getElementById("percent-plaque-4").textContent = '<%=listPlaques.get(3)%>' + "%";
                            break;
                    }
                } else {
                        document.getElementById("percent-plaque-4").textContent = '<%=listPlaques.get(0)%>' + "%";
                }
            }
        }

        document.getElementById("select-cards").onclick = function() {
            if ('<%=canCards%>'==="true") {
                document.querySelector(".content-help").style.display = "none";
                document.querySelector(".content-cards").style.display = "block";
                document.formSubmit.cardsAvailable.value = "false";
                var valuesGenerated = [];
                valuesGenerated = generateValueCards([]);
                var cardsList = [];
                cardsList = document.querySelectorAll(".icon-card");
                for (var i=0; i<4; i++) {
                    cardsList[i].addEventListener("click", (event) => {
                        for (var j=0; j<4; j++) {
                            var index = j+1;
                            document.getElementById("card-" + index).src = "./img/card-" + valuesGenerated[j] + ".jpg";
                            document.getElementById("card-" + index).style.pointerEvents = "none";
                        }
                        var cardSelected = event.target.getAttribute('src');
                        var indexImg = cardSelected.split('-')[1];
                        var emptyQuestions = indexImg.split('.')[0];
                        var options = document.querySelectorAll(".question-li");
                        switch (emptyQuestions) {
                            case '0':
                                var optionsEliminated = generateQuestionsEliminated('<%=correct%>', options, 1);
                                options[optionsEliminated[0]].querySelector(".answer").textContent = '';
                                options[optionsEliminated[0]].querySelector(".number").style.display = "none";
                                options[optionsEliminated[0]].style.pointerEvents = "none";
                                break;
                            case '1':
                                var optionsEliminated = generateQuestionsEliminated('<%=correct%>', options, 2);
                                for (var i=0; i<2; i++) {
                                    options[optionsEliminated[i]].querySelector(".answer").textContent = '';
                                    options[optionsEliminated[i]].querySelector(".number").style.display = "none";
                                    options[optionsEliminated[i]].style.pointerEvents = "none";
                                }
                                break;
                            case '2':
                                var optionsEliminated = generateQuestionsEliminated('<%=correct%>', options, 3);
                                for (var i=0; i<3; i++) {
                                    options[optionsEliminated[i]].querySelector(".answer").textContent = '';
                                    options[optionsEliminated[i]].querySelector(".number").style.display = "none";
                                    options[optionsEliminated[i]].style.pointerEvents = "none";
                                }
                                break;
                            case '3':
                                var optionsEliminated = generateQuestionsEliminated('<%=correct%>', options, 0);
                                break;
                        }
                    });
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
    <script type="text/javascript" src="./js/question.js"></script>
</body>
</html>