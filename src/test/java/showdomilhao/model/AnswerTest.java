package showdomilhao.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import showdomilhao.repository.QuestionRepository;

@ExtendWith(SpringExtension.class)
public class AnswerTest {

    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private Question question;

    @Test
    @DisplayName("Prêmio para a quinta pergunta")
    void testPremiumFifthCorrectAnswer() {
        Answer answer = new Answer(0);
        int i = 0;
        while (i<5) {
            answer.calculatePremium(i);
            i++;
        }

        Assertions.assertEquals(5000,answer.getPremium());
    }

    @Test
    @DisplayName("Prêmio para a décima pergunta")
    void testPremiumTenthCorrectAnswer() {
        Answer answer = new Answer(0);
        int i = 0;
        while (i<10) {
            answer.calculatePremium(i);
            i++;
        }

        Assertions.assertEquals(50000,answer.getPremium());
    }

    @Test
    @DisplayName("Prêmio para a décima sexta pergunta (Pergunta do milhão)")
    void testPremiumSixtenthCorrectAnswer() {
        Answer answer = new Answer(0);
        int i = 0;
        while (i<16) {
            answer.calculatePremium(i);
            i++;
        }

        Assertions.assertEquals(1000000,answer.getPremium());
    }

    @Test
    void testValidateAnswer() {
        Answer answer = new Answer(0);
        BDDMockito.given(questionRepository.findById(0)).willReturn(question);
        BDDMockito.given(question.getCorrect_answer()).willReturn("Washington");
        Assertions.assertEquals(false, answer.validateAnswer("Nova York", question));
    }
}
