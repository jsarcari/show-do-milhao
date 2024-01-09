import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Answer {
    private Participant participant;
    private double premium;
    private double premiumStop = 0;
    private double premiumMiss = 0;
    private Boolean right = true;

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

    public List createArrayOptions(Question question) {
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
        if (j<4) {
            premium = getPremium()+1000;
            setPremium(premium);
        } else if (j>4 && j<9) {
            premium = getPremium()+10000;
            setPremium(premium);
        } else if (j==4 || j==9 || j==14) {
            premium = getPremium()*2;
            setPremium(premium);
        }else if (j>9 && j<14) {
            premium = getPremium()+100000;
            setPremium(premium);
        }
    }

}
