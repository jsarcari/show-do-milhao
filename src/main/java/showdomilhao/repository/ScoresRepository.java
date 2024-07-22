package showdomilhao.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import showdomilhao.model.Scores;

public interface ScoresRepository extends MongoRepository<Scores, String> {

    List <Scores> findAll();

}
