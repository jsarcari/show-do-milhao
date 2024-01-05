import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        int j = 0;
        String iAmRight;
        Participant person = new Participant("Juan");
        Answer answer = new Answer(1000);
        ArrayList<Question> listQuestions = new ArrayList<>();
        listQuestions.add(new Question("Qual é a capital dos Estados Unidos?", "Washington", "easy", "Geografia", new String[] {"Nova York", "Miami", "Chicago"}));
        listQuestions.add(new Question("Qual dessas é considerada uma doença na coluna vertebral?", "Artrose", "medium", "Ciência", new String[] {"Apendicite", "Gastrite", "Artrite"}));
        listQuestions.add(new Question("Em um ano bissexto que mês tem um dia a mais?", "Fevereiro", "easy", "Conhecimentos gerais", new String[] {"Março", "Abril", "Outubro"}));
        listQuestions.add(new Question("Qual alimento não é derivado do leite?", "Mostarda", "easy", "Conhecimentos gerais", new String[] {"Queijo", "Iogurte", "Coalhada"}));
        listQuestions.add(new Question("Qual desses órgãos faz parte do aparelho digestivo?", "Intestino", "easy", "Ciência", new String[] {"Baço", "Pulmão", "Rim"}));
        while (answer.getRight() == true && person.getStop() == false && j < listQuestions.size()) {
            int iWantHelp = 0;
            Question ask = listQuestions.get(j);
            do {
                System.out.println("Valendo R$%.2f".formatted(answer.getPremium()));
                System.out.println("%s".formatted(ask.getQuestion()));
                System.out.println(ask.getCorrect_answer());
                for (String option : ask.getIncorrect_answers()) {
                    System.out.println(option);
                }
                Scanner input = new Scanner(System.in);
                String myAnswer = input.nextLine();
                System.out.println("Você está certo disso? (y/n)");
                iAmRight = input.nextLine();
                if (iAmRight.equals("y")) {
                    answer.setRight(answer.validateAnswer(myAnswer, ask));
                    if (answer.getRight()) {
                        System.out.println("Certa resposta!");
                    } else {
                        System.out.println("Que pena. Você errou!");
                        answer.setRight(false);
                    }
                } else {
                    System.out.println("""
                            Você quer ajuda ou parar?
                            1 - Solicitar ajuda aos universitários
                            2 - Solicitar ajuda às placas
                            3 - Solicitar ajuda às cartas
                            4 - Parar
                            5 - Quero responder
                             """);
                    iWantHelp = input.nextInt();
                    if (iWantHelp == 4) {
                        person.setStop(true);
                    }
                }
            } while (iWantHelp != 4 && !iAmRight.equals("y"));
            if (j<6) {
                double premium = answer.getPremium()+1000;
                answer.setPremium(premium);
            } else if (j>6 && j<11) {
                double premium = answer.getPremium()+10000;
                answer.setPremium(premium);
            } else if (j==6 || j==11 || j==16) {
                double premium = answer.getPremium()*2;
                answer.setPremium(premium);
            }else if (j>11 && j<15) {
                double premium = answer.getPremium()+100000;
                answer.setPremium(premium);
            }
            j++;
        }
    }
}