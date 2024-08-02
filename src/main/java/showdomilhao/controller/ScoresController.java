package showdomilhao.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import showdomilhao.model.Scores;
import showdomilhao.repository.ScoresRepository;
import showdomilhao.service.ScoresService;

@Controller
public class ScoresController {

    @Autowired
    private ScoresService service;

    @Autowired
    private ScoresRepository repository;

    @GetMapping("/score")
    public RedirectView createScore(String name, double premium) {

        this.service.create(new Scores(name, premium));

        return new RedirectView("scores");

    }

    @GetMapping("/scores")
    public ModelAndView viewScores() {

        List<Scores> scores = new ArrayList<Scores>();
        List<Scores> topTen = new ArrayList<Scores>();

        repository.findAll(Sort.by(Sort.Direction.DESC, "premium")).forEach(item -> scores.add(new Scores(item.getName(), item.getPremium())));

        int i=0;
        while (i<10 && i<scores.size()) {
            topTen.add(new Scores(scores.get(i).getName(), scores.get(i).getPremium()));
            i++;
        }

        ModelAndView mv = new ModelAndView();

        mv.addObject("LIST_SCORES", topTen);

        mv.setViewName("scores.jsp");

        return mv;

    }
}
