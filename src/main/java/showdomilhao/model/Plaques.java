package showdomilhao.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Plaques extends Help {
    private Boolean available = true;
    List<Integer> list = new ArrayList<Integer>();

    public Boolean getAvailable() {
        return available;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public void setList(List<Integer> plaques) {
        this.list = plaques;
    }

    public List<Integer> listPlaques(List<Integer> plaques, int num) {
        plaques.add(num);
        return plaques;
    }

    public void printHelp() {
        List<Integer> plaques = new ArrayList<Integer>();
        int sum = 100;
        int percentage = generatePercentage(sum,50);
        plaques = listPlaques(plaques, percentage);
        sum = 100-percentage;
        int result = percentage;
        percentage = generatePercentage(sum,0);
        result += percentage;
        sum = 100-result;
        plaques = listPlaques(plaques, percentage);
        percentage = generatePercentage(sum,0);
        result += percentage;
        sum = 100-result;
        plaques = listPlaques(plaques, percentage);
        plaques = listPlaques(plaques, sum);
        this.setList(plaques);
    }

    private int generatePercentage(int max, int min) {
        Random generator = new Random();
        int numDado = generator.nextInt(max-min) + min;

        return numDado;
    }
}
