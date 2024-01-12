import java.util.Random;

public class Plaques extends Help {
    private Boolean available = true;

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public void printAnswers() {
        int correct = getCorrectAnswer()+1;
        System.out.println("Alternativa " + correct + ": " + generatePercentage() + "%");
    }

    public int generatePercentage() {
        Random generator = new Random();
        int numDado = generator.nextInt(50) + 50;
        return numDado;
    }
}
