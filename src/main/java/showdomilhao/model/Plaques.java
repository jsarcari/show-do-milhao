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

    public void generateRandomValue(int sum, int index) {
        int value = 0;

        if (index == 0){
            value = generatePercentage(100, 50);
            list.add(value);
            sum += value;
            generateRandomValue(sum, 1);
        } else if (index == 3) {
            value = 100-sum;
            list.add(value);
        } else {
            value = generatePercentage((100 - sum), 0);
            list.add(value);
            sum += value;
            generateRandomValue(sum, index+1);
        }
    }

}

