package showdomilhao.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Plaques extends Help {

    private Boolean available = true;

    List<Integer> list = new ArrayList<Integer>();

    private int generatePercentage(int max, int min) {
        Random generator = new Random();
        return generator.nextInt((max - min) + 1) + min;
    }

    public Boolean getAvailable() {
        return available;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public void printHelp() {
        int sum = 0;

        for (int i = 0; i < 4; i++) {
            sum += generateRandomValue(sum, i);
        }

    }

    public int generateRandomValue(int sum, int index) {
        int value = 0;

        if (index != 3) {
            value = generatePercentage((100 - sum), 0);
            list.add(value);
        }else{
            value = 100 - sum;
            list.add(value);
        }

        return value;
    }

}
