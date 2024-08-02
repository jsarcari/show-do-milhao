package showdomilhao.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import static org.mockito.BDDMockito.then;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import showdomilhao.repository.ScoresRepository;
import showdomilhao.service.ScoresService;

@ExtendWith(MockitoExtension.class)
public class ScoresTest {

    @InjectMocks
    private ScoresService service;

    @Mock
    private ScoresRepository repository;

    @Mock
    private Scores score;

    @Captor
    private ArgumentCaptor<Scores> scoreCaptor;

    @Test
    void shouldSaveNewScore() {
        service.create(score);
        then(repository).should().save(scoreCaptor.capture());
        Scores savedScore = scoreCaptor.getValue();
        Assertions.assertEquals(score.getName(), savedScore.getName());
        Assertions.assertEquals(score.getPremium(), savedScore.getPremium());
    }
}
