import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        int i = 0, j = 0;
        String iAmRight;
        Participant person = new Participant("Juan");
        Boolean win = false, action;
        Answer answer = new Answer(1000);
        Guests guests = new Guests();
        ArrayList<Question> listQuestions = new ArrayList<>();
        listQuestions.add(new Question("Qual é a capital dos Estados Unidos?", "Washington", "easy", "Geografia", new String[] {"Nova York", "Miami", "Chicago"}));
        listQuestions.add(new Question("Qual dessas é considerada uma doença na coluna vertebral?", "Artrose", "medium", "Ciência", new String[] {"Apendicite", "Gastrite", "Artrite"}));
        listQuestions.add(new Question("Em um ano bissexto que mês tem um dia a mais?", "Fevereiro", "easy", "Conhecimentos gerais", new String[] {"Março", "Abril", "Outubro"}));
        listQuestions.add(new Question("Qual alimento não é derivado do leite?", "Mostarda", "easy", "Conhecimentos gerais", new String[] {"Queijo", "Iogurte", "Coalhada"}));
        listQuestions.add(new Question("Como é conhecida a campanha que alerta a sociedade sobre a prevenção do câncer de mama?", "Outubro rosa", "easy", "Conhecimentos gerais", new String[] {"Janeiro branco", "Setembro amarelo", "Novembro azul"}));
        listQuestions.add(new Question("Qual desses órgãos faz parte do aparelho digestivo?", "Intestino", "easy", "Ciência", new String[] {"Baço", "Pulmão", "Rim"}));
        listQuestions.add(new Question("Quem é o homem de aço das histórias em quadrinhos?", "Super homem", "easy", "Filmes, desenhos e televisão", new String[] {"Batman", "Hulk", "He-man"}));
        listQuestions.add(new Question("Qual dessas palavras não é um palíndromo?", "Uva", "easy", "Português e literatura", new String[] {"Arara", "Reviver", "Osso"}));
        listQuestions.add(new Question("Qual é a moeda oficial dos Estados Unidos?", "Dólar", "easy", "Conhecimentos gerais", new String[] {"Escudo", "Coroa", "Libra"}));
        listQuestions.add(new Question("O saquê é uma bebida originária de qual país?", "Japão", "medium", "História e política", new String[] {"Estados Unidos", "Coréia do Sul", "China"}));
        listQuestions.add(new Question("Quem libertou os escravos em 1888?", "Princesa Isabel", "easy", "História e política", new String[] {"Joana d'Arc", "Anita Garibaldi", "Imperatriz Leopoldina"}));
        listQuestions.add(new Question("Que criatura mitológica é metade mulher e metade peixe?", "Sereia", "easy", "História e política", new String[] {"Medusa", "Cleópatra", "Iemanjá"}));
        listQuestions.add(new Question("Qual atriz protagonizou ao lado de Leonardo Da Vinci o filme Titanic?", "Kate Winslet", "medium", "Filmes, desenhos e televisão", new String[] {"Cameron Diaz", "Bridget Fonda", "C. Zetta Jones"}));
        listQuestions.add(new Question("Onde é mais usado o sismógrafo?", "Terremotos", "medium", "Geografia", new String[] {"Furacões", "Maremotos", "Tempestades"}));
        listQuestions.add(new Question("Qual orixá é conhecida como a Rainha do Mar?", "Iemanjá", "medium", "Conhecimentos gerais", new String[] {"Mamãe Oxum", "Pomba-gira", "Iansã"}));
        listQuestions.add(new Question("Qual o recipiente utilizado para conservar quente os liquidos que contém?", "Garrafa térmica", "easy", "Ciência", new String[] {"Jarra", "Bacia", "Taça"}));
        listQuestions.add(new Question("A compensação por perda é chamada de:", "Indenização", "medium", "Matemática", new String[] {"Défict", "Indexação", "Indébito"}));
        listQuestions.add(new Question("Qual é o nome dado aos segmentos ósseos dos dedos das mãos e dos pés?", "Falange", "hard", "Ciência", new String[] {"Clávicula", "Temporal", "Esterno"}));
        listQuestions.add(new Question("Qual destes itens tem numerador e denominador?", "Fração", "medium", "Matemática", new String[] {"Soma", "Multiplicação", "Potência"}));
        listQuestions.add(new Question("Qual autor escreveu \"Primeiras Estórias\"?", "Guimarães Rosa", "hard", "Português e literatura", new String[] {"Olavo Bilac", "Euclides da Cunha", "José de Alencar"}));
        listQuestions.add(new Question("Quem foi eleito presidente da África do Sul em 1994?", "Nelson Mandela", "hard", "História e política", new String[] {"Nelson Piquet", "Nelson Eddy", "John Nelson"}));
        listQuestions.add(new Question("Qual história Francis Ford Coppola dirigiu em três partes", "O poderoso chefão", "hard", "Filmes, desenhos e televisão", new String[] {"Titanic", "Sexta-feira treze", "Guerra nas estrelas"}));
        while (answer.getRight() == true && person.getStop() == false && !win) {
            int iWantHelp=-1;
            Question ask = listQuestions.get(j);
            List printOptions = answer.createArrayOptions(ask);
            do {
                System.out.println("Valendo R$%.2f".formatted(answer.getPremium()));
                System.out.println("Se errar: R$%.2f Se parar: R$%.2f".formatted(answer.getPremiumMiss(),answer.getPremiumStop()));
                System.out.println("%s".formatted(ask.getQuestion()));
                for (Object option : printOptions) {
                    System.out.println(printOptions.indexOf(option)+1 + ". " + option);
                }
                Scanner input = new Scanner(System.in);
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
                            System.out.println("PARABÉNS! Você ganhou R$ 1 milhão!");
                            win = true;
                        }
                    } else {
                        System.out.println("Que pena. Você errou!");
                        System.out.println("Você ganhou R$"+ answer.getPremiumMiss());
                        answer.setRight(false);
                    }
                } else {
                    do {
                        action = true;
                        answer.printActions();
                        iWantHelp = input.nextInt();
                        if (iWantHelp == 1) {
                            if (guests.getAvailable()) {
                                guests.setCorrectAnswer(printOptions.indexOf(ask.getCorrect_answer()));
                                guests.printAnswers();
                                guests.setAvailable(false);
                            } else {
                                System.out.println("Você não pode mais acionar aos universitários!");
                                action = false;
                            }
                        }
                        if (iWantHelp == 4) {
                            person.setStop(true);
                            System.out.println("Você ganhou R$" + answer.getPremiumStop());
                        }
                        if (iWantHelp == 0 && person.getCanSkip() == 0) {
                            System.out.println("Você não pode mais pular!");
                            action = false;
                        }
                    } while (!action);
                }
            } while (iWantHelp != 4 && iWantHelp != 0 && !iAmRight.equals("y"));
            if (iWantHelp!=0) {
                answer.calculatePremium(i);
                i++;
            } else {
                int skip = person.getCanSkip()-1;
                person.setCanSkip(skip);
            }
            j++;
        }
    }
}