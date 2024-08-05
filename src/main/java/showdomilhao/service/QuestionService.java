package showdomilhao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import showdomilhao.model.Question;
import showdomilhao.repository.QuestionRepository;

@Service
public class QuestionService {
    
    @Autowired
    private QuestionRepository repository;

    public List<Question> list() {
        return repository.findAll();
    }
}
