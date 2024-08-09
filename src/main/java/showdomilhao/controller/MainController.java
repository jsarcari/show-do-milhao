package showdomilhao.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import showdomilhao.model.Answer;
import showdomilhao.model.Cards;
import showdomilhao.model.Guests;
import showdomilhao.model.Participant;
import showdomilhao.model.Plaques;
import showdomilhao.model.Question;
import showdomilhao.service.HelpService;
import showdomilhao.service.QuestionService;

@Controller
public class MainController {

    private List<Question> questions;
    private Answer answer;
    private int indexQuestion;
    private List<Integer> idsChoice;
    private int index = 0;
    private Participant user;
    private Guests guests;
    private Plaques plaques;
    private Cards cards;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private HelpService helpService;

    @GetMapping("/")
    public ModelAndView indexPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home.jsp");
        return mv;
    }

    @GetMapping("/name")
    public ModelAndView namePage() {
        ModelAndView mv = new ModelAndView();
        this.questions = questionService.list();
        this.user = new Participant();
        this.answer = new Answer(0);
        this.guests = new Guests();
        this.plaques = new Plaques();
        this.cards = new Cards();
        this.idsChoice = new ArrayList<Integer>();
        mv.setViewName("name.jsp");

        return mv;
    }

    @PostMapping("/question")
    public ModelAndView questionPage(Participant participant, @RequestParam int id, String guestsAvailable, String plaquesAvailable, String cardsAvailable, String skipAvailable, String nameUser, String categoryUser) {
        ModelAndView mv = new ModelAndView();
        this.index = id-1;
        List<Boolean> listHelp = helpService.listAvailabilities(guestsAvailable, plaquesAvailable, cardsAvailable, this.index);
        if (this.index==0 && participant != null) {
            this.user = participant;
        }
        this.guests.setAvailable(listHelp.get(0));
        this.plaques.setAvailable(listHelp.get(1));
        this.cards.setAvailable(listHelp.get(2));
        if (skipAvailable!=null && !skipAvailable.equals("")) {
            this.user.setName(nameUser);
            this.user.setCategory(categoryUser);
            this.user.setCanSkip(Integer.valueOf(skipAvailable));
        } else {
            this.answer.calculatePremium(this.index);
        }

        this.indexQuestion = this.answer.choiceQuestion(this.questions, this.idsChoice, this.index, this.user.getCategory());
        this.idsChoice.add(this.indexQuestion);
        Question question = this.questions.get(this.indexQuestion);
        List<String> options = this.answer.createArrayOptions(question);
        
        String valuePremium = String.format("%.0f", this.answer.getPremium());
        String valueWrong = String.format("%.0f", this.answer.getPremiumLose());
        String valueStop = String.format("%.0f", this.answer.getPremiumStop());

        mv.addObject("VALUE_PREMIUM", valuePremium);
        mv.addObject("VALUE_WRONG", valueWrong);
        mv.addObject("VALUE_STOP", valueStop);
        mv.addObject("ANSWER", this.answer);
        mv.addObject("PARTICIPANT", this.user);
        mv.addObject("QUESTION", question);
        mv.addObject("OPTIONS", options);
        mv.addObject("GUESTS", this.guests);
        mv.addObject("PLAQUES", this.plaques);
        mv.addObject("CARDS", this.cards);
        mv.setViewName("question.jsp");

        return mv;

    }

}
