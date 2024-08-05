package showdomilhao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    public List<Scores> topTen() {
        List<Scores> scores = new ArrayList<Scores>();
        List<Scores> topTen = new ArrayList<Scores>();

        repository.findAll(Sort.by(Sort.Direction.DESC, "premium")).forEach(item -> scores.add(new Scores(item.getName(), item.getPremium())));

        int i=0;
        while (i<10 && i<scores.size()) {
            topTen.add(new Scores(scores.get(i).getName(), scores.get(i).getPremium()));
            i++;
        }

        return topTen;
    }
}
