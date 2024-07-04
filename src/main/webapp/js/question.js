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

document.querySelector(".close-plaques").onclick = function() {
    var buttonGuests = document.getElementById("select-plaques");
    document.getElementById("modal-help").style.display = "none";
    document.querySelector(".content-help").style.display = "flex";
    document.querySelector(".content-plaques").style.display = "none";
    buttonGuests.style.pointerEvents = "none";
    buttonGuests.style.opacity = "0.4";
    document.getElementById("legend-plaques").style.textDecoration = "line-through";

}
