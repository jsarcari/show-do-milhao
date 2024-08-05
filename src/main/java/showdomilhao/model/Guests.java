package showdomilhao.model;

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

    public void generateWrongAnswers(int indexCorrect) {
        Random generator = new Random();
        this.setNumber(indexCorrect);
        while (this.getNumber() == indexCorrect) {
            this.setNumber(generator.nextInt(4));
        }
        this.setGuest(generator.nextInt(4));
    }
}
