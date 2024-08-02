package showdomilhao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import showdomilhao.model.Scores;
import showdomilhao.repository.ScoresRepository;

@Service
public class ScoresService {
    
    @Autowired
    private ScoresRepository repository;

    public void create(Scores scores) {
        repository.save(scores);
    }
}
