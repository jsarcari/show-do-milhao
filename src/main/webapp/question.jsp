<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="showdomilhao.model.Question" import="showdomilhao.model.Answer" import="showdomilhao.model.Guests" import="showdomilhao.model.Participant" import="showdomilhao.model.Plaques" import="showdomilhao.model.Cards" import="java.util.ArrayList" import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="img/Show_do_milhao.webp" />
<title>Show do Milhão</title>
<link rel="stylesheet" href="css/question.css" type="text/css" />
</head>
<%
    Question currentQuestion = (Question) request.getAttribute("QUESTION");
    String question = currentQuestion.getQuestion();
    String correct = currentQuestion.getCorrect_answer();
    Integer idQuestion = 2;
    Integer currentIndex = 2;
    if (request.getParameter("id") != null) {
        idQuestion = Integer.valueOf(request.getParameter("id"));
        currentIndex = idQuestion;
        idQuestion++;
    }
    Answer currentAnswer = (Answer) request.getAttribute("ANSWER");
    int iCorrect = currentAnswer.getIndexCorrect();
    String valuePremium = "";
    if (request.getAttribute("VALUE_PREMIUM") != null) {
        valuePremium = (String)request.getAttribute("VALUE_PREMIUM");
    }
    String valueStop = "";
    if (request.getAttribute("VALUE_STOP") != null) {
        valueStop = (String)request.getAttribute("VALUE_STOP");
    }
    String valueWrong = "";
    if (request.getAttribute("VALUE_WRONG") != null) {
        valueWrong = (String)request.getAttribute("VALUE_WRONG");
    }
    List<String> options = new ArrayList<String>();
    options = (List<String>) request.getAttribute("OPTIONS");
    Guests guests = (Guests) request.getAttribute("GUESTS");
    Boolean canGuests = guests.getAvailable();
    int incorrectGuest = 0, incorrectNumber = 0;
    if (canGuests==true) {
        guests.generateWrongAnswers(iCorrect);
        incorrectGuest = guests.getGuest();
        incorrectNumber = guests.getNumber();
        incorrectNumber++;
    }
    Plaques plaques = (Plaques) request.getAttribute("PLAQUES");
    List<Integer> listPlaques = new ArrayList<Integer>();
    Boolean canPlaques = plaques.getAvailable();
    if (canPlaques==true) {
        plaques.generateRandomValue(0, 0);
        listPlaques = plaques.getList();
    }
    if (listPlaques.size()==0) {
        listPlaques.add(26);
        listPlaques.add(24);
        listPlaques.add(25);
        listPlaques.add(25);
    }
    Cards cards = (Cards) request.getAttribute("CARDS");
    Boolean canCards = cards.getAvailable();
    Participant user = (Participant) request.getAttribute("PARTICIPANT");
    String nameUser = user.getName();
    String category = user.getCategory();
    int canSkip = user.getCanSkip();
%>
<body>
    <audio src="audio/perguntashowdomilhao.mp3" autoplay></audio>
    <audio src="audio/suspense-show-do-milhao.mp3" autoplay id="thriller-audio"></audio>
    <div class="container">
        <div class="container-left">
            <div class="question">
                <h1><%=question%></h1>
            </div>
            <ol class="listQuestions">
                <li class="question-li li-one" onclick="document.getElementById('audio-are-you-right').play();areYouRight('one','<%=correct%>')"><div class="number one">1</div><div class="answer" id="one"><%=options.get(0)%></div></li>
                <li class="question-li li-two" onclick="document.getElementById('audio-are-you-right').play();areYouRight('two','<%=correct%>')"><div class="number two">2</div><div class="answer" id="two"><%=options.get(1)%></div></li>
                <li class="question-li li-three" onclick="document.getElementById('audio-are-you-right').play();areYouRight('three','<%=correct%>')"><div class="number three">3</div><div class="answer" id="three"><%=options.get(2)%></div></li>
                <li class="question-li li-four" onclick="document.getElementById('audio-are-you-right').play();areYouRight('four','<%=correct%>')"><div class="number four">4</div><div class="answer" id="four"><%=options.get(3)%></div></li>
            </ol>
            <div class="premiums">
                <div>
                    <div class="premium-value"><% if (!valuePremium.equals("1000000")) { out.print("R$ "+valueWrong); } else { out.print("PERDEU TUDO"); } %></div>
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
            Guests:<%=canGuests%>,Plaques:<%=canPlaques%>,Cards:<%=canCards%>
        </div>
        <div class="container-right">
            <div class="people">
                <h2 class="name-user"><%=nameUser%></h2>
                <div class="body-head"></div>
                <div class="body-user"></div>
                <div id="timer">
                    <div id="bar-timer"><div id="progress-timer"></div></div><div id="second-timer">40</div>
                </div>
            </div>
            <div class="menu-help">
                <div>
                    <div class="button-help"></div>
                    <p id="legend-help">Ajuda</p>
                </div>
                <div>
                    <div class="button-stop"></div>
                    <p>Parar</p>
                </div>
            </div>
        </div>
        <input type="hidden" id="id-selected" name="idSelected" value="" />
        <div id="modal-help" class="modal">
            <div class="content-help">
                <span class="close-menu">x</span>
                <div class="guests">
                    <img class="icon-help" id="select-guests" src="./img/2639885_people_icon.svg">
                    <p id="legend-guests">Universitários</p>
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
                        <input type="hidden" name="categoryUser" value="<%=category%>" />
                        <input type="hidden" name="guestsAvailable" value="<%=canGuests%>"/>
                        <input type="hidden" name="plaquesAvailable" value="<%=canPlaques%>"/>
                        <input type="hidden" name="cardsAvailable" value="<%=canCards%>"/>
                        <button type="submit" class="icon-help" id="select-skip"><img src="./img/912603-200.png" id="img-skip"></button>
                        <p id="legend-skip">Pular</p>
                    </form>
                </div>
            </div>
            <div class="content-guests">
                <span class="close-guests" onclick="closeHelp('content-guests')">x</span>
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
                <span class="close-plaques" onclick="closeHelp('content-plaques')">x</span>
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
                    <span class="close-cards" onclick="closeHelp('content-cards')">x</span>
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
            <audio src="audio/silvio-santos-esta-certo-disso.mp3" id="audio-are-you-right"></audio>
            <div class="modal-content">
              <div class="content-ask">
                <p>Você está certo disso?</p>
              </div>
              <div class="buttons-confirm">
                <button onclick="document.getElementById('audio-are-you-right').muted=true;document.getElementById('audio-result').play();document.getElementById('thriller-audio').volume=0.3;validateAnswer()" id="yes-button">Sim</button><button id="no-button">Não</button>
              </div>
            </div>
        </div>
        <audio id="audio-result"></audio>
        <audio src="audio/silvio-santos-o-seu-tempo-acabou.mp3" id="time-out"></audio>
        <audio src="audio/silvio-santos-voce-entendeu-a-pergunta.mp3" id="do-you-understand"></audio>
        <div id="correctAnswer" class="modal">
            <!-- Modal content -->
            <div class="modal-content">
              <div class="content-ask">
                <p id="message">Certa resposta</p>
              </div>
              <form name="formSubmit" class="buttons-confirm" method="post" action="question?id=<%=idQuestion%>">
                <% if (!valuePremium.equals("1000000")) { %>
                    <input type="hidden" name="guestsAvailable" value="<%=canGuests%>"/>
                    <input type="hidden" name="plaquesAvailable" value="<%=canPlaques%>"/>
                    <input type="hidden" name="cardsAvailable" value="<%=canCards%>"/>
                <% } else { %>
                    <input type="hidden" name="name" value="<%=nameUser%>" />
                    <input type="hidden" name="premium" value="<%=valuePremium%>" />
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
                <p id="premiumWrongOrStop">Você ganhou <strong id="value-stop">R$ ${VALUE_WRONG}</strong></p>
              </div>
              <form name="formWrongOrStop" class="buttons-confirm" method="get" action="/score">
                <input type="hidden" name="name" value="<%=nameUser%>" />
                <input type="hidden" name="premium" value="<%=valueWrong%>" />
                <button type="submit" id="yes-incorrect">Continuar</button>
              </form>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        let second = 40;
        let cron;

        document.addEventListener('DOMContentLoaded', () => {
            cron = setInterval(() => { timer(); }, 1000);
        });

        document.querySelector(".button-help").onclick = function() {
            document.getElementById("modal-help").style.display = "block";
            if ('<%=canGuests%>' === "false") {
                disableButton('guests');
            }
            if ('<%=canPlaques%>' === "false") {
                disableButton('plaques');
            }
            if ('<%=canSkip%>' === "0") {
                disableButton('skip');
            }
            if ('<%=canCards%>' === "false") {
                disableButton('cards');
            }  
        }

        document.querySelector(".button-stop").onclick = function() {
            var premiumStop = '<%=valueStop%>';
            document.getElementById("sad").textContent = "";
            document.getElementById("incorrectAnswer").style.display = "block";
            document.getElementById("value-stop").textContent = 'R$ '+ premiumStop;
            document.formWrongOrStop.premium.value = premiumStop;
        }

        document.getElementById("select-guests").onclick = function() {
            if ('<%=canGuests%>'==="true") {
                showHelp("content-guests");
                var correctOption = '<%=iCorrect%>';
                correctOption++;
                switch('<%=incorrectGuest%>') {
                    case "0":
                        document.getElementById("answer-guest-1").textContent = '<%=incorrectNumber%>';
                        document.getElementById("answer-guest-2").textContent = correctOption;
                        document.getElementById("answer-guest-3").textContent = correctOption;
                        break;
                    case "1":
                        document.getElementById("answer-guest-1").textContent = correctOption;
                        document.getElementById("answer-guest-2").textContent = '<%=incorrectNumber%>';
                        document.getElementById("answer-guest-3").textContent = correctOption;
                        break;
                    case "2":
                        document.getElementById("answer-guest-1").textContent = correctOption;
                        document.getElementById("answer-guest-2").textContent = correctOption;
                        document.getElementById("answer-guest-3").textContent = '<%=incorrectNumber%>';
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
                showHelp("content-plaques");
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
                showHelp("content-cards");
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

        function timer() {
            if (second > 0) {
                second--;
                document.getElementById("second-timer").innerText = second;
                const progress = document.getElementById("progress-timer");
                progress.style.width = second*2.5 + 'px';
                progress.style.background = 'hsl(calc('+second*2.5*1.2+'), 80%, 50%)';
                if (second===20) {
                    document.getElementById("do-you-understand").play();
                }
            } else {
                var modalIncorrect = document.getElementById("incorrectAnswer");
                document.getElementById("sad").textContent = "Seu tempo acabou!";
                modalIncorrect.style.display = "block";
                var options = document.querySelectorAll(".question-li");
                document.getElementById("time-out").play();
                greenOnCorrectAnswer('<%=correct%>');
                if ('<%=valuePremium%>' === '1000000') {
                    document.formWrongOrStop.premium.value = "0";
                    document.getElementById("premiumWrongOrStop").textContent = "Você perdeu tudo.";
                }
                clearInterval(cron);
            }
        }

        document.addEventListener('DOMContentLoaded', function() {
            if('<%=valuePremium%>'==='1000000') {
                const buttonHelp = document.querySelector(".button-help");
                buttonHelp.title = "Você não pode solicitar ajuda";
                buttonHelp.style.pointerEvents = "none";
                buttonHelp.style.opacity = "0.4";
                document.getElementById("legend-help").style.textDecoration = "line-through";
            }
        }, false);

        function validateAnswer() {
            var id = document.getElementById("id-selected").value;
            var divSelected = document.querySelector(".li-" + id);
            var value = document.getElementById(id).textContent;
            var modalSure = document.getElementById("areYouSure");
            var modalCorrect = document.getElementById("correctAnswer");
            clearInterval(cron);
            modalSure.style.display = "none";
            if (value === '<%=correct%>') {
                if ('<%=valuePremium%>' === '1000000') {
                    document.getElementById("message").textContent = "Parabéns! Você ganhou 1 milhão";
                    document.formSubmit.setAttribute("method", "get");
                    document.formSubmit.action = "/score";
                }
                modalCorrect.style.display = "block";
                divSelected.style.backgroundColor = "#01b051";
            } else {
                showModalIncorrect('<%=correct%>', '<%=valuePremium%>');
            }
        }

    </script>
    <script type="text/javascript" src="./js/question.js"></script>
</body>
</html>