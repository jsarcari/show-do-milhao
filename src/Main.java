import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        int i = 0, j;
        String iAmRight;
        Participant person = new Participant("Juan");
        Boolean win = false, action;
        Answer answer = new Answer(1000);
        Guests guests = new Guests();
        Plaques plaques = new Plaques();
        List<Integer> ids = new ArrayList<Integer>();
        ArrayList<Question> listQuestions = new ArrayList<>();
        listQuestions.add(new Question(0, "Qual é a capital dos Estados Unidos?", "Washington", "easy", "Geografia", new String[] {"Nova York", "Miami", "Chicago"}));
        listQuestions.add(new Question(1, "Qual dessas é considerada uma doença na coluna vertebral?", "Artrose", "medium", "Ciência", new String[] {"Apendicite", "Gastrite", "Artrite"}));
        listQuestions.add(new Question(2, "Em um ano bissexto que mês tem um dia a mais?", "Fevereiro", "easy", "Conhecimentos gerais", new String[] {"Março", "Abril", "Outubro"}));
        listQuestions.add(new Question(3, "Qual alimento não é derivado do leite?", "Mostarda", "easy", "Conhecimentos gerais", new String[] {"Queijo", "Iogurte", "Coalhada"}));
        listQuestions.add(new Question(4, "Como é conhecida a campanha que alerta a sociedade sobre a prevenção do câncer de mama?", "Outubro rosa", "easy", "Conhecimentos gerais", new String[] {"Janeiro branco", "Setembro amarelo", "Novembro azul"}));
        listQuestions.add(new Question(5, "Qual desses órgãos faz parte do aparelho digestivo?", "Intestino", "easy", "Ciência", new String[] {"Baço", "Pulmão", "Rim"}));
        listQuestions.add(new Question(6, "Quem é o homem de aço das histórias em quadrinhos?", "Super homem", "easy", "Filmes, desenhos e televisão", new String[] {"Batman", "Hulk", "He-man"}));
        listQuestions.add(new Question(7, "Qual dessas palavras não é um palíndromo?", "Uva", "easy", "Português e literatura", new String[] {"Arara", "Reviver", "Osso"}));
        listQuestions.add(new Question(8, "Qual é a moeda oficial dos Estados Unidos?", "Dólar", "easy", "Conhecimentos gerais", new String[] {"Escudo", "Coroa", "Libra"}));
        listQuestions.add(new Question(9, "O saquê é uma bebida originária de qual país?", "Japão", "medium", "História e política", new String[] {"Estados Unidos", "Coréia do Sul", "China"}));
        listQuestions.add(new Question(10, "Quem libertou os escravos em 1888?", "Princesa Isabel", "easy", "História e política", new String[] {"Joana d'Arc", "Anita Garibaldi", "Imperatriz Leopoldina"}));
        listQuestions.add(new Question(11, "Que criatura mitológica é metade mulher e metade peixe?", "Sereia", "easy", "História e política", new String[] {"Medusa", "Cleópatra", "Iemanjá"}));
        listQuestions.add(new Question(12, "Qual atriz protagonizou ao lado de Leonardo Da Vinci o filme Titanic?", "Kate Winslet", "medium", "Filmes, desenhos e televisão", new String[] {"Cameron Diaz", "Bridget Fonda", "C. Zetta Jones"}));
        listQuestions.add(new Question(13, "Onde é mais usado o sismógrafo?", "Terremotos", "medium", "Geografia", new String[] {"Furacões", "Maremotos", "Tempestades"}));
        listQuestions.add(new Question(14, "Qual orixá é conhecida como a Rainha do Mar?", "Iemanjá", "medium", "Conhecimentos gerais", new String[] {"Mamãe Oxum", "Pomba-gira", "Iansã"}));
        listQuestions.add(new Question(15, "Qual o recipiente utilizado para conservar quente os liquidos que contém?", "Garrafa térmica", "easy", "Ciência", new String[] {"Jarra", "Bacia", "Taça"}));
        listQuestions.add(new Question(16, "A compensação por perda é chamada de:", "Indenização", "medium", "Matemática", new String[] {"Défict", "Indexação", "Indébito"}));
        listQuestions.add(new Question(17, "Qual é o nome dado aos segmentos ósseos dos dedos das mãos e dos pés?", "Falange", "hard", "Ciência", new String[] {"Clávicula", "Temporal", "Esterno"}));
        listQuestions.add(new Question(18, "Qual destes itens tem numerador e denominador?", "Fração", "medium", "Matemática", new String[] {"Soma", "Multiplicação", "Potência"}));
        listQuestions.add(new Question(19, "Qual autor escreveu \"Primeiras Estórias\"?", "Guimarães Rosa", "hard", "Português e literatura", new String[] {"Olavo Bilac", "Euclides da Cunha", "José de Alencar"}));
        listQuestions.add(new Question(20, "Quem foi eleito presidente da África do Sul em 1994?", "Nelson Mandela", "hard", "História e política", new String[] {"Nelson Piquet", "Nelson Eddy", "John Nelson"}));
        listQuestions.add(new Question(21, "Qual história Francis Ford Coppola dirigiu em três partes", "O poderoso chefão", "hard", "Filmes, desenhos e televisão", new String[] {"Titanic", "Sexta-feira treze", "Guerra nas estrelas"}));
        listQuestions.add(new Question(22, "Com quantos graus centígrados a água ferve?", "100", "medium", "Ciência", new String[] {"200", "170", "220"}));
        listQuestions.add(new Question(23, "Quantos signos astrológicos fazem o zoodíaco?", "12", "medium", "Geografia", new String[] {"9", "10", "11"}));
        listQuestions.add(new Question(24, "Como se chama o alimento extraído pelas plantas do solo?", "Seiva", "hard", "Ciência", new String[] {"Celulose", "Mitose", "Clorofila"}));
        listQuestions.add(new Question(25, "Qual jogador brasileiro de futebol recebeu o apelido de Imperador duarante sua passagem pela Itália?", "Adriano", "medium", "Esportes", new String[] {"Robinho", "Marcelinho Carioca", "Dunga"}));
        listQuestions.add(new Question(26, "Qual órgão do nosso corpo produz a urina", "Rim", "medium", "Ciência", new String[] {"Fígado", "Coração", "Boca"}));
        listQuestions.add(new Question(27, "Qual é o metal encontrado no interior de um termômetro?", "Mercúrio", "hard", "Ciência", new String[] {"Aço", "Alumínio", "Ferro"}));
        listQuestions.add(new Question(28, "Qual composto químico representa a fórmula Pb(NO3)2?", "Nitrato de Chumbo", "hard", "Ciência", new String[] {"Ácido Fluorídrico", "Cloreto de Cromilo", "Nitrato de Amônio"}));
        listQuestions.add(new Question(29, "Linha que estabelece a mais curta distância entre dois pontos é a:", "Reta", "medium", "Matemática", new String[] {"Curva", "Ângulo", "Zig-zague"}));
        listQuestions.add(new Question(30, "Quem é o primeiro substituto do presidente?", "Vice-presidente", "easy", "Conhecimentos gerais", new String[] {"Vereador", "Senador", "Deputado"}));
        listQuestions.add(new Question(31, "Quem dirigiu o filme \"Central do Brasil\" indicado para o Oscar como o melhor filme estrangeiro em 1999?", "Walter Salles Jr.", "hard", "Filmes, desenhos e televisão", new String[] {"Bruno Barreto", "Hector Babenco", "Arnaldo Jabor"}));
        listQuestions.add(new Question(32, "Qual a fórmula química da água?", "H20", "easy", "Ciência", new String[] {"HO", "H", "H02"}));
        listQuestions.add(new Question(33, "De quantos em quantos dias a lua muda de fase", "7", "medium", "Geografia", new String[] {"5", "8", "15"}));
        listQuestions.add(new Question(34, "Como é o nome do riacho em cujas margens foi proclamada a independência do Brasil?", "Ipiranga", "easy", "História e política", new String[] {"Cambuci", "Sacomâ", "Independente"}));
        while (answer.getRight() == true && person.getStop() == false && !win) {
            int iWantHelp=-1;
            j = answer.choiceQuestion(listQuestions,ids, i);
            ids.add(j);
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
                        System.out.println("Você ganhou R$.2f"+ answer.getPremiumMiss());
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
                        if (iWantHelp == 2) {
                            if (plaques.getAvailable()) {
                                plaques.setCorrectAnswer(printOptions.indexOf(ask.getCorrect_answer()));
                                plaques.printAnswers();
                                plaques.setAvailable(false);
                            } else {
                                System.out.println("Você não pode mais recorrer às placas");
                                action = false;
                            }
                        }
                        if (iWantHelp == 4) {
                            person.setStop(true);
                            System.out.println("Você ganhou R$.2f" + answer.getPremiumStop());
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
        }
    }
}