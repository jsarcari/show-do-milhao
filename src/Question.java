import java.lang.reflect.Array;

public class Question {
    private String question;
    private String correct_answer;
    private String difficulty;
    private String category;
    private String[] incorrect_answers;

    public Question(String question, String correct, String difficulty, String category, String[] other_answers) {
        this.setQuestion(question);
        this.setCorrect_answer(correct);
        this.setDifficulty(difficulty);
        this.setCategory(category);
        this.setIncorrect_answers(other_answers);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getCategory() {
        return category;
    }

    public String[] getIncorrect_answers() {
        return incorrect_answers;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setIncorrect_answers(String[] incorrect_answers) {
        this.incorrect_answers = incorrect_answers;
    }
}
