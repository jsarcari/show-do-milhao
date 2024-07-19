package showdomilhao.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuestionTest {

    @Test 
    void testPremiumMissTenthAnswer() {
        String[] otherQuestions = {"13", "14", "16"};
        Question question = new Question(1000, "Quantas letras contém a escrita da bandeira nacional brasileira?", "15", "hard", "Conhecimentos gerais", otherQuestions);
        Answer answer = new Answer(50000);
        if (!answer.validateAnswer("16", question)) {
            answer.setPremiumMiss(answer.getPremium()/2);
        }

        Assertions.assertEquals(25000, answer.getPremiumMiss());
    }
}
