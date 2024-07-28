function areYouRight(id, correctAnswer, premium) {
    var divSelected = document.querySelector(".li-" + id);
    var value = document.getElementById(id).textContent;
    var modalSure = document.getElementById("areYouSure");
    var modalCorrect = document.getElementById("correctAnswer");
    var modalIncorrect = document.getElementById("incorrectAnswer");
    var number = document.querySelector("." + id);
    modalSure.style.display = "block";
    number.style.backgroundColor = "red";
    number.style.color = "black";
    document.getElementById("yes-button").onclick = function() {
        modalSure.style.display = "none";
        if (value === correctAnswer) {
            if (premium === '1000000') {
                document.getElementById("message").textContent = "Parabéns! Você ganhou 1 milhão";
                document.formSubmit.setAttribute("method","get");
                document.formSubmit.action = "/score";
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
            if (premium === '1000000') {
                document.formWrongOrStop.premium.value = "0";
                document.getElementById("premiumWrongOrStop").textContent = "Você perdeu tudo.";
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

document.addEventListener('DOMContentLoaded', function() {
    const premiums = document.querySelectorAll('.premium-value');
    for (var i=0; i<3; i++) {
        var premium = premiums[i];
        var valuePremium = premium.textContent.split(' ');
        premium.textContent = valuePremium[0] + ' ' + valuePremium[1].replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    }
}, false);

document.querySelector(".close-menu").onclick = function() {
    document.getElementById("modal-help").style.display = "none";
}

function generateQuestionsEliminated(correctAnswer, options, num) {
    list = [];
    var i=0;
    while (i<num) {
        index = Math.floor(Math.random()*4);
        if ((options[index].querySelector(".answer").textContent !== correctAnswer) && !list.includes(index)) {
            list.push(index);
            i++;
        }
    }
    return list;
}

function showHelp(classHelp) {
    document.querySelector(".content-help").style.display = "none";
    document.querySelector("."+classHelp).style.display = "flex";
    switch(classHelp) {
        case "content-guests":
            document.formSubmit.guestsAvailable.value = "false";
            document.skipSubmit.guestsAvailable.value = "false";
            break;
        case "content-plaques":
            document.formSubmit.plaquesAvailable.value = "false";
            document.skipSubmit.plaquesAvailable.value = "false";
            break;
        case "content-cards":
            document.formSubmit.cardsAvailable.value = "false";
            document.skipSubmit.cardsAvailable.value = "false";
            break
    }
}

function closeHelp(classHelp) {
    document.getElementById("modal-help").style.display = "none";
    document.querySelector(".content-help").style.display = "flex";
    document.querySelector("."+classHelp).style.display = "none";
    switch(classHelp) {
        case "content-guests":
            var button = document.getElementById("select-guests");
            button.style.pointerEvents = "none";
            button.style.opacity = "0.4";
            document.getElementById("legend-guests").style.textDecoration = "line-through";
            break;
        case "content-plaques":
            var button = document.getElementById("select-plaques");
            button.style.pointerEvents = "none";
            button.style.opacity = "0.4";
            document.getElementById("legend-plaques").style.textDecoration = "line-through";
            break;
        case "content-cards":
            var button = document.getElementById("select-cards");
            button.style.pointerEvents = "none";
            button.style.opacity = "0.4";
            document.getElementById("legend-cards").style.textDecoration = "line-through";
            break;
    }
}

function generateValueCards(values) {
    var length = values.length;
    if ((4-length) > 0) {
        var n = 0;
        do {
            n = Math.floor(Math.random()*4);
        } while (values.includes(n));
        values.push(n);
        return generateValueCards(values);
    } else {
        return values;
    }

}

function disableButton(help) {
    var button = document.getElementById("select-"+help);
    button.style.pointerEvents = "none";
    button.style.opacity = "0.4";
    document.getElementById("legend-"+help).style.textDecoration = "line-through";
}