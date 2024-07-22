package showdomilhao.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("scores")
public class Scores {
    //@Id
    //private String id;

    private String name;
    private double premium;

    public Scores(String name, double premium) {
        super();
        //this.id = id;
        this.name = name;
        this.premium = premium;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPremium() {
        return premium;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }
}
