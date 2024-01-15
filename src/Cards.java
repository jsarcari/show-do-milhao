import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cards extends Help {

    private int valueUm;
    private int valueDois;
    private int valueTres;
    private int valueQuatro;
    private Boolean available = true;

    public int getValueUm() {
        return valueUm;
    }

    public void setValueUm(int valueUm) {
        this.valueUm = valueUm;
    }

    public int getValueDois() {
        return valueDois;
    }

    public void setValueDois(int valueDois) {
        this.valueDois = valueDois;
    }

    public int getValueTres() {
        return valueTres;
    }

    public void setValueTres(int valueTres) {
        this.valueTres = valueTres;
    }

    public int getValueQuatro() {
        return valueQuatro;
    }

    public void setValueQuatro(int valueQuatro) {
        this.valueQuatro = valueQuatro;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    protected void printHelp() {
        System.out.println("""
                Escolha uma carta:
                1 - Carta Um
                2 - Carta Dois
                3 - Carta Três
                4 - Carta Quatro
                """);
    }

    public List<Integer> generateQuestionsEliminated(Question question, List options, int num) {
        List<Integer> list = new ArrayList<Integer>();
        Random generate = new Random();
        int i = 0, index;
        while (i<num) {
            index = generate.nextInt(4);
            if (!question.getCorrect_answer().equals(options.get(index)) && !list.contains(index)) {
                list.add(index);
                i++;
            }
        }

        return list;
    }

    public void printCards() {
        if (getValueUm() != 4) {
            System.out.println("Carta 1: " + getValueUm());
        } else {
            System.out.println("Carta 1: K");
        }
        if (getValueDois() != 4) {
            System.out.println("Carta 2: " + getValueDois());
        } else {
            System.out.println("Carta 2: K");
        }
        if (getValueTres() != 4) {
            System.out.println("Carta 3: " + getValueTres());
        } else {
            System.out.println("Carta 3: K");
        }
        if (getValueQuatro() != 4) {
            System.out.println("Carta 4: " + getValueQuatro());
        } else {
            System.out.println("Carta 4: K");
        }
    }

    public void generateValueCards() {
        int n = 0;
        Random generator = new Random();
        List<Integer> values = new ArrayList<Integer>();
        setValueUm(generator.nextInt(4));
        values.add(getValueUm());
        do {
            setValueDois(generator.nextInt(4));
            n = getValueDois();
        } while (values.contains(n));
        values.add(getValueDois());
        do {
            setValueTres(generator.nextInt(4));
            n = getValueTres();
        } while (values.contains(n));
        values.add(getValueTres());
        do {
            setValueQuatro(generator.nextInt(4));
            n = getValueQuatro();
        } while (values.contains(n));
        values.add(getValueQuatro());
        setValueUm(values.get(0)+1);
        setValueDois(values.get(1)+1);
        setValueTres(values.get(2)+1);
        setValueQuatro(values.get(3)+1);
    }

    public int readCard(int card) {
        int num = 0;
        switch(card) {
            case 1:
                num = getValueUm();
                break;
            case 2:
                num = getValueDois();
                break;
            case 3:
                num = getValueTres();
                break;
            case 4:
                num = getValueQuatro();
                break;
            default:
                System.out.println("Número de cartas inválido");
        }

        return num;
    }
}
