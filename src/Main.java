import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        int i = 0, j, numQuestions = 0;
        System.out.println("Qual é o seu nome?");
        Scanner input = new Scanner(System.in);
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
        JSONRead read = new JSONRead();
        ArrayList<Question> listQuestions = read.readFile();
        while (answer.getRight() == true && person.getStop() == false && !win) {
            emptyOptions.clear();
            int iWantHelp=-1;
            j = answer.choiceQuestion(listQuestions,ids, i);
            ids.add(j);
            Question ask = listQuestions.get(j);
            List printOptions = answer.createArrayOptions(ask);
            do {
                answer.printQuestion(ask);
                for (Object option : printOptions) {
                    if (!emptyOptions.contains(printOptions.indexOf(option))) {
                        System.out.println(printOptions.indexOf(option)+1 + ". " +  option);
                    } else {
                        System.out.println(printOptions.indexOf(option)+1 + ". ");
                    }
                }

                Integer myAnswer = Integer.parseInt(input.nextLine());
                String valueAnswer = printOptions.get(myAnswer-1).toString();
                System.out.println("Você está certo disso? (y/n)");
                iAmRight = input.nextLine();
                if (iAmRight.equals("y")) {
                    answer.setRight(answer.validateAnswer(valueAnswer, ask));
                    if (answer.getRight()) {
                        System.out.println("Certa resposta!");
                        answer.setPremiumStop(answer.getPremium());
                        answer.setPremiumMiss(answer.getPremium()/2);
                        if(answer.getPremium()==1000000) {
                            System.out.println("PARABÉNS %s! Você ganhou R$ 1 milhão!".formatted(person.getName()));
                            win = true;
                        }
                    } else {
                        System.out.println("Que pena. Você errou!");
                        System.out.println("A resposta certa é " + ask.getCorrect_answer());
                        System.out.println("Você ganhou R$%.2f".formatted(answer.getPremiumMiss()));
                        answer.setRight(false);
                    }
                } else {
                    do {
                        action = true;
                        answer.printActions();
                        iWantHelp = Integer.parseInt(input.nextLine());
                        switch (iWantHelp) {
                            case 0:
                                if (person.getCanSkip() == 0) {
                                    System.out.println("Você não pode mais pular!");
                                    action = false;
                                } else {
                                    int skip = person.getCanSkip()-1;
                                    person.setCanSkip(skip);
                                }
                                break;
                            case 1:
                                if (guests.getAvailable()) {
                                    guests.setCorrectAnswer(printOptions.indexOf(ask.getCorrect_answer()));
                                    guests.printHelp();
                                    guests.setAvailable(false);
                                } else {
                                    System.out.println("Você não pode mais acionar aos universitários!");
                                    action = false;
                                }
                                break;
                            case 2:
                                if (plaques.getAvailable()) {
                                    plaques.setCorrectAnswer(printOptions.indexOf(ask.getCorrect_answer()));
                                    plaques.printHelp();
                                    plaques.setAvailable(false);
                                } else {
                                    System.out.println("Você não pode mais recorrer às placas");
                                    action = false;
                                }
                                break;
                            case 3:
                                if (cards.getAvailable()) {
                                    cards.setCorrectAnswer(printOptions.indexOf(ask.getCorrect_answer()));
                                    cards.printHelp();
                                    cards.generateValueCards();
                                    Integer card = Integer.parseInt(input.nextLine());
                                    numQuestions = cards.readCard(card);
                                    cards.printCards();
                                    if(numQuestions!=4) {
                                        emptyOptions = cards.generateQuestionsEliminated(ask, printOptions, numQuestions);
                                    }
                                    cards.setAvailable(false);
                                } else {
                                    System.out.println("Você não pode mais recorrer às cartas");
                                    action = false;
                                }
                                break;
                            case 4:
                                person.setStop(true);
                                System.out.println("Você ganhou R$%.2f".formatted(answer.getPremiumStop()));
                                break;
                            default:
                                System.out.println("Opção inválida");
                                break;
                        }
                    } while (!action);
                }
            } while (iWantHelp != 4 && iWantHelp != 0 && !iAmRight.equals("y"));
            if (iWantHelp!=0) {
                answer.calculatePremium(i);
                i++;
            }
        }
    }
}