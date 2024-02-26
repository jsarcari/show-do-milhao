import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;

public class JSONRead  {
	
	public static ArrayList<Question> WebServiceComumnication() throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		String url = "http://localhost:3000/";
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		return readJSON(response.body());
		
	}

    public static ArrayList<Question> readJSON(String json) {
        ArrayList<Question> questions = new ArrayList<>();
        Gson gson = new Gson();
        Question[] array = gson.fromJson(json, Question[].class);
        for(Question question : array) {
            questions.add(question);
        }

        return questions;
    }


}
