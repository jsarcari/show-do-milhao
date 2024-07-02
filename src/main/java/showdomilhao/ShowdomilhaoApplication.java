package showdomilhao;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// import showdomilhao.model.Answer;
// import showdomilhao.model.Cards;
// import showdomilhao.model.Guests;
// import showdomilhao.model.Option;
// import showdomilhao.model.Participant;
// import showdomilhao.model.Plaques;
// import showdomilhao.model.Question;
// import showdomilhao.service.ReadApi;

@SpringBootApplication
public class ShowdomilhaoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ShowdomilhaoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//int i = 0, j;
        System.out.println("Bem-vindo ao Show do Milhão!");
        /*Scanner input = new Scanner(System.in);
        String iAmRight;
        String name = input.nextLine();
        Participant person = new Participant(name);
        Boolean win = false, action;
        Answer answer = new Answer(1000);
        Guests guests = new Guests();
        Plaques plaques = new Plaques();
        Cards cards = new Cards();
        List<Integer> ids = new ArrayList<Integer>();
        List<Integer> emptyOptions = new ArrayList<Integer>();
        ReadApi read = new ReadApi();
        ArrayList<Question> listQuestions = read.getData();
        while (answer.getRight() == true && person.getStop() == false && !win) {
            emptyOptions.clear();
			answer.setEmptyOptions(emptyOptions);
            int iWantHelp=-1;
            j = answer.choiceQuestion(listQuestions,ids, i);
            ids.add(j);
            Question ask = listQuestions.get(j);
            List options = answer.createArrayOptions(ask);
            do {
                answer.printQuestion(ask);
                answer.printOptions(options);

                Integer myAnswer = Integer.parseInt(input.nextLine());
                String valueAnswer = options.get(myAnswer-1).toString();
                System.out.println("Você está certo disso? (y/n)");
                iAmRight = input.nextLine();
                if (iAmRight.equals("y")) {
                    answer.setRight(answer.validateAnswer(valueAnswer, ask));
                    if (answer.getRight()) {
                        answer.printCorrect();
                        if(answer.getPremium()==1000000) {
                            System.out.println("PARABÉNS %s! Você ganhou R$ 1 milhão!".formatted(person.getName()));
                            win = true;
                        }
                    } else {
                        answer.printIncorrect(ask);
                    }
                } else {
                    do {
                        action = true;
                        answer.printActions();
                        iWantHelp = Integer.parseInt(input.nextLine());
						Option option = new Option(iWantHelp, person, guests, plaques, cards, action, options, ask, answer);
						option.validateOption();
						action = option.getAction();
                    } while (!action);
                }
            } while (iWantHelp != 4 && iWantHelp != 0 && !iAmRight.equals("y"));
            if (iWantHelp!=0) {
                answer.calculatePremium(i);
                i++;
            }
        }*/
	}

}
