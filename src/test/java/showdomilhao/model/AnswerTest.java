package showdomilhao.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {

    @Test
    @DisplayName("Prêmio para a décima resposta correta")
    void testPremiumTenthCorrectAnswer() {
        Answer answer = new Answer(50000);
        int i = 9;
        answer.calculatePremium(i);

        Assertions.assertEquals(100000,answer.getPremium());
    }

    @Test 
    void testPremiumMissTenthAnswer() {
        Question question = new Question(0, "Quantas letras contém a escrita da bandeira nacional brasileira?", "15", "hard", "Conhecimentos gerais", "13", "14", "16");
        Answer answer = new Answer(50000);
        if (!answer.validateAnswer("16", question)) {
            answer.setPremiumMiss(answer.getPremium()/2);
        }

        Assertions.assertEquals(25000, answer.getPremiumMiss());
    }
}
