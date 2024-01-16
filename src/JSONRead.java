import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class JSONRead  {

    public static ArrayList<Question> readFile() throws FileNotFoundException {
        ArrayList<Question> questions = new ArrayList<>();
        Gson gson = new Gson();
        BufferedReader json = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/src/questions.json"));
        Question[] array = gson.fromJson(json, Question[].class);
        for(Question que : array) {
            questions.add(que);
        }

        return questions;
    }


}
