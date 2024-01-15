import java.util.Random;

public class Guests extends Help {
    private int number;
    private int guest;
    private Boolean available = true;

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getGuest() {
        return guest;
    }

    public void setGuest(int guest) {
        this.guest = guest;
    }

    public void printHelp() {
        generateWrongAnswers();
        int num = getNumber()+1;
        int correct = getCorrectAnswer()+1;
        if(this.getGuest() == 0) {
            System.out.println("Universitário 1: " + num);
        } else {
            System.out.println("Universitário 1: " + correct);
        }
        if(this.getGuest() == 1) {
            System.out.println("Universitário 2: " + num);
        } else {
            System.out.println("Universitário 2: " + correct);
        }
        if(this.getGuest() == 2) {
            System.out.println("Universitário 3: " + num);
        } else {
            System.out.println("Universitário 3: " + correct);
        }
    }

    private void generateWrongAnswers() {
        Random generator = new Random();
        this.setNumber(this.correctAnswer);
        while (this.getNumber() == this.correctAnswer) {
            this.setNumber(generator.nextInt(4));
        }
        this.setGuest(generator.nextInt(4));
    }
}
