package showdomilhao.model;

import java.util.List;
import java.util.Scanner;

public class Option {
    private String option;
    private Participant person;
    private Guests guests;
    private Plaques plaques;
    private Cards cards;
    private Boolean action;
    private List<String> options;
    private Question question;
    private Answer answer;

    public Option(String num, Participant person, Guests guests, Plaques plaques, Cards cards, Boolean action, List<String> options, Question question, Answer answer) {
        this.setOption(num);
        this.setPerson(person);
        this.setGuests(guests);
        this.setPlaques(plaques);
        this.setCards(cards);
        this.setAction(action);
        this.setOptions(options);
        this.setQuestion(question);
        this.setAnswer(answer);
    }

    public String getOption() {
        return this.option;
    }

    public Boolean getAction() {
        return this.action;
    }

    public void setOption(String num) {
        this.option = num;
    }

    public void setPerson(Participant person) {
        this.person = person;
    }

    public void setGuests(Guests guests) {
        this.guests = guests;
    }

    public void setPlaques(Plaques plaques) {
        this.plaques = plaques;
    }

    public void setCards(Cards cards) {
        this.cards = cards;
    }

    public void setAction(Boolean action) {
        this.action = action;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public void validateOption() {
        Scanner input = new Scanner(System.in);
        switch (this.getOption()) {
            case "0":
                if (this.person.getCanSkip() == 0) {
                    System.out.println("Você não pode mais pular!");
                    this.setAction(false);
                } else {
                    int skip = this.person.getCanSkip()-1;
                    this.person.setCanSkip(skip);
                }
                break;
            case "1":
                if (this.guests.getAvailable()) {
                    this.guests.setCorrectAnswer(this.options.indexOf(this.question.getCorrect_answer()));
                    this.guests.printHelp();
                    this.guests.setAvailable(false);
                } else {
                    System.out.println("Você não pode mais acionar aos universitários!");
                    this.setAction(false);
                }
                break;
            case "2":
                if (this.plaques.getAvailable()) {
                    this.plaques.setCorrectAnswer(this.options.indexOf(this.question.getCorrect_answer()));
                    this.plaques.printHelp();
                    this.plaques.setAvailable(false);
                } else {
                    System.out.println("Você não pode mais recorrer às placas");
                    this.setAction(false);
                }
                break;
            case "3":
                if (this.cards.getAvailable()) {
                    this.cards.setCorrectAnswer(options.indexOf(this.question.getCorrect_answer()));
                    this.cards.printHelp();
                    this.cards.generateValueCards();
                    Integer card = Integer.parseInt(input.nextLine());
                    int numQuestions = this.cards.readCard(card);
                    this.cards.printCards();
                    if(numQuestions!=4) {
                        List <Integer> emptyOptions = this.cards.generateQuestionsEliminated(this.question, options, numQuestions);
                        this.answer.setEmptyOptions(emptyOptions);
                    }
                    cards.setAvailable(false);
                } else {
                    System.out.println("Você não pode mais recorrer às cartas");
                    this.setAction(false);;
                }
                break;
            case "4":
                this.person.setStop(true);
                System.out.println("A resposta certa é " + question.getCorrect_answer());
                System.out.println("Você ganhou R$%.2f".formatted(this.answer.getPremiumStop()));
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
        input.close();
    }

}
