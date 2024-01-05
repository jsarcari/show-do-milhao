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

    public Boolean validateAnswer(String answerParticipant, Question question) {
        if (answerParticipant.equals(question.getCorrect_answer())) {
            return true;
        } else {
            return false;
        }
    }


}
