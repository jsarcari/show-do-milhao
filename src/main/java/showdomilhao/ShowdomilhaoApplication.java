package showdomilhao;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import showdomilhao.model.Question;
import showdomilhao.service.ReadApi;

@SpringBootApplication
public class ShowdomilhaoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ShowdomilhaoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ReadApi readApi = new ReadApi();
		ArrayList<Question> questions = readApi.getData();
		for (Question question: questions) {
			System.out.println(question.getQuestion());
		}
	}

}
