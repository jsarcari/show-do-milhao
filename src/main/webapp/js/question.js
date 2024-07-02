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

document.querySelector(".button-stop").onclick = function() {
    document.getElementById("sad").textContent = "";
    document.getElementById("incorrectAnswer").style.display = "block";
    document.getElementById("value-stop").textContent = 'R$ <%=valueStop%>';
}