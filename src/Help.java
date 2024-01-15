public abstract class Help {
    protected int correctAnswer;

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    protected abstract void printHelp();

}
