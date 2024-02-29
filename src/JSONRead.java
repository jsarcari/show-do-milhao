import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;

public class JSONRead  {
	
	public static String WebServiceCommunication() throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		String url = "http://localhost:3000/";
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		return response.body();
		
	}

    public static ArrayList<Question> jsonToQuestions() throws IOException, InterruptedException {
        ArrayList<Question> questions = new ArrayList<>();
        Gson gson = new Gson();
        String json = WebServiceCommunication();
        Question[] array = gson.fromJson(json, Question[].class);
        for(Question question : array) {
            questions.add(question);
        }

        return questions;
    }


}
