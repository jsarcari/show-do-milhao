package showdomilhao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import showdomilhao.model.Scores;
import showdomilhao.service.ScoresService;

@Controller
public class ScoresController {

    @Autowired
    private ScoresService service;

    @GetMapping("/score")
    public RedirectView createScore(String name, double premium) {

        this.service.create(new Scores(name, premium));

        return new RedirectView("scores");

    }

    @GetMapping("/scores")
    public ModelAndView viewScores() {

        ModelAndView mv = new ModelAndView();

        mv.addObject("LIST_SCORES", this.service.topTen());

        mv.setViewName("scores.jsp");

        return mv;

    }
}
