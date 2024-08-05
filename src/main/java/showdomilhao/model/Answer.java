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
    private double premiumLose = 0;
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

    public double getPremiumLose() {
        return premiumLose;
    }

    public void setPremiumLose(double premiumLose) {
        this.premiumLose = premiumLose;
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

    public int choiceQuestion(List<Question> list, List<Integer> ids, int i, String category) {
        Random generator = new Random();
        int id = 0;
        Boolean containsId = true;
        while (containsId) {
            id = generator.nextInt(list.size());
            if (!ids.contains(id) && (list.get(id).getCategory().equals(category) || category.equals("Todos"))) {
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
        double premium, currentlyPremium;
        currentlyPremium = getPremium();
        if (j<5) {
            premium = currentlyPremium+1000;
            setPremium(premium);
        } else if (j>5 && j<10) {
            premium = currentlyPremium+10000;
            setPremium(premium);
        } else if (j==5 || j==10 || j==15) {
            premium = currentlyPremium*2;
            setPremium(premium);
        }else if (j>10 && j<15) {
            premium = currentlyPremium+100000;
            setPremium(premium);
        }
        setPremiumStop(currentlyPremium);
        setPremiumLose(currentlyPremium/2);
    }

}
