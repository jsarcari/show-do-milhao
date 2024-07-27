package showdomilhao.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

public class Answer {
    //private Participant participant;
    private double premium;
    private double premiumStop = 0;
    private double premiumMiss = 0;
    private Boolean right = true;
    private List<Integer> emptyOptions = new ArrayList<Integer>();

    public Answer(double premium) {
        this.setPremium(premium);
    }

    public double getPremium() {
        return premium;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }


    public double getPremiumStop() {
        return premiumStop;
    }

    public List<Integer> getEmptyOptions() {
        return emptyOptions;
    }

    public void setPremiumStop(double premiumStop) {
        this.premiumStop = premiumStop;
    }

    public double getPremiumMiss() {
        return premiumMiss;
    }

    public void setPremiumMiss(double premiumMiss) {
        this.premiumMiss = premiumMiss;
    }

    public Boolean getRight() {
        return right;
    }

    public void setRight(Boolean right) {
        this.right = right;
    }

    public void setEmptyOptions(List<Integer> emptyOptions) {
        this.emptyOptions = emptyOptions;
    }

    public int choiceQuestion(ArrayList<Question> list, List<Integer> ids, int i) {
        Random generator = new Random();
        int id = 0;
        Boolean containsId = true;
        while (containsId) {
            id = generator.nextInt(list.size());
            if (!ids.contains(id)) {
                if (i<5 && list.get(id).getDifficulty().equals("easy")) {
                    containsId = false;
                }
                if (i>=5 && i<10 && list.get(id).getDifficulty().equals("medium")) {
                    containsId = false;
                }
                if (i>=10 && list.get(id).getDifficulty().equals("hard")) {
                    containsId = false;
                }
            }
        }
        return id;
    }
    
    public void printQuestion(Question question) {
    	System.out.println("Valendo R$%.2f".formatted(this.getPremium()));
        System.out.println("Se errar: R$%.2f Se parar: R$%.2f".formatted(this.getPremiumMiss(),this.getPremiumStop()));
        System.out.println("%s".formatted(question.getQuestion()));
    }
    
    public void printOptions(List<String> options) {
    	for (Object option : options) {
            if (!this.emptyOptions.contains(options.indexOf(option))) {
                System.out.println(options.indexOf(option)+1 + ". " +  option);
            } else {
                System.out.println(options.indexOf(option)+1 + ". ");
            }
        }
    }
    
    public void printCorrect() {
    	System.out.println("Certa resposta!");
        setPremiumStop(getPremium());
        setPremiumMiss(getPremium()/2);
    }
    
    public void printIncorrect(Question question) {
    	System.out.println("Que pena. Você errou!");
        System.out.println("A resposta certa é " + question.getCorrect_answer());
        System.out.println("Você ganhou R$%.2f".formatted(getPremiumMiss()));
        setRight(false);
    }

    public void printActions() {
        System.out.println("""
                Você quer ajuda, pular ou parar?
                0 - Pular
                1 - Solicitar ajuda aos universitários
                2 - Solicitar ajuda às placas
                3 - Solicitar ajuda às cartas
                4 - Parar
                5 - Quero responder
                """);
    }

    public List<String> createArrayOptions(Question question) {
        Random generator = new Random();
        int index = generator.nextInt(4);
        List<String> array = new ArrayList<String>(4);
        for(String option : question.getIncorrect_answers()) {
            array.add(option);
        }
        array.add(index, question.getCorrect_answer());
        return array;
    }

    public Boolean validateAnswer(String answerParticipant, Question question) {
        if (answerParticipant.equals(question.getCorrect_answer())) {
            return true;
        } else {
            return false;
        }
    }

    public void calculatePremium(int j) {
        double premium;
        if (j<5) {
            premium = getPremium()+1000;
            setPremium(premium);
        } else if (j>5 && j<10) {
            premium = getPremium()+10000;
            setPremium(premium);
        } else if (j==5 || j==10 || j==15) {
            premium = getPremium()*2;
            setPremium(premium);
        }else if (j>10 && j<15) {
            premium = getPremium()+100000;
            setPremium(premium);
        }
    }

}
