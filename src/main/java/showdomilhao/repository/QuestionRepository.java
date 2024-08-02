package showdomilhao.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import showdomilhao.model.Question;

public interface QuestionRepository extends MongoRepository<Question, String> {
    
    List <Question> findAll();

    Question findById(int id);
}
