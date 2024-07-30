package showdomilhao.model;

public class Participant {
    private String name;
    private Boolean stop = false;
    private int canSkip = 3;
    private String category;

    public Participant(String category, String name) {
        this.setName(name);
        this.setCategory(category);
    }

    public String getName() {
        return name;
    }

    public Boolean getStop() {
        return stop;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }

    public int getCanSkip() {
        return canSkip;
    }

    public void setCanSkip(int canSkip) {
        this.canSkip = canSkip;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
