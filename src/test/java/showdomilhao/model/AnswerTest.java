package showdomilhao.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {

    @Test
    @DisplayName("Prêmio para a décima resposta correta")
    void testPremiumTenthCorrectAnswer() {
        Answer answer = new Answer(50000);
        int i = 10;
        answer.calculatePremium(i);

        Assertions.assertEquals(100000,answer.getPremium());
    }
}
