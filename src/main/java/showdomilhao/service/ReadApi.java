package showdomilhao.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import com.google.gson.Gson;

import showdomilhao.model.Question;

public class ReadApi {

    private static final String ADDRESS = "http://localhost:3000/";

    public static String readJson() {
        HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(ADDRESS)).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
		return response.body();
    }

    public static ArrayList<Question> getData() {
        ArrayList<Question> questions = new ArrayList<>();
        Gson gson = new Gson();
        String json = readJson();
        Question[] array = gson.fromJson(json, Question[].class);
        for(Question question : array) {
            questions.add(question);
        }

        return questions;
    }
}
