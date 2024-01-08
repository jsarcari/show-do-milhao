import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Answer {
    private Participant participant;
    private double premium;
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

    public Boolean getRight() {
        return right;
    }

    public void setRight(Boolean right) {
        this.right = right;
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


}
