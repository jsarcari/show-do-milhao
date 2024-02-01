public class Participant {
    private String name;
    private Boolean stop = false;
    private int canSkip = 3;

    public Participant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Boolean getStop() {
        return stop;
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
}
