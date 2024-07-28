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
import showdomilhao.repository.QuestionRepository;

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
    QuestionRepository questionRepository;

    @GetMapping("/")
    public ModelAndView indexPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home.jsp");
        return mv;
    }

    @GetMapping("/name")
    public ModelAndView namePage() {
        ModelAndView mv = new ModelAndView();
        this.questions = questionRepository.findAll();
        this.user = new Participant(null);
        this.answer = new Answer(1000);
        this.guests = new Guests();
        this.plaques = new Plaques();
        this.cards = new Cards();
        this.idsChoice = new ArrayList<Integer>();
        mv.setViewName("name.jsp");

        return mv;
    }

    @PostMapping("/question")
    public ModelAndView questionPage(Participant participant, @RequestParam int id, String guestsAvailable, String plaquesAvailable, String cardsAvailable, String skipAvailable, String nameUser) {
        ModelAndView mv = new ModelAndView();
        this.index = id-1;
        this.indexQuestion = this.answer.choiceQuestion(this.questions, this.idsChoice, this.index);
        this.idsChoice.add(this.indexQuestion);
        Question question = this.questions.get(this.indexQuestion);
        List<String> options = this.answer.createArrayOptions(question);
        List<Integer> listPlaques = new ArrayList<Integer>();
        if(this.index!=0 && (skipAvailable==null || skipAvailable.equals(""))) {
            this.answer.setPremiumStop(this.answer.getPremium());
            this.answer.setPremiumMiss(this.answer.getPremium()/2);
            this.answer.calculatePremium(this.index);
        } else {
            if (participant != null) {
                this.user = participant;
            }
        }
        if (guestsAvailable!=null && guestsAvailable.equals("false")) {
            this.guests.setAvailable(false);
        }
        if (plaquesAvailable!=null && plaquesAvailable.equals("false")) {
            this.plaques.setAvailable(false);
        } else {
            this.plaques.printHelp();
            listPlaques = this.plaques.getList();
        }
        if (cardsAvailable!=null && cardsAvailable.equals("false")) {
            this.cards.setAvailable(false);
        }
        if (skipAvailable!=null && !skipAvailable.equals("")) {
            this.user.setName(nameUser);
            this.user.setCanSkip(Integer.valueOf(skipAvailable));
        }
        
        String valuePremium = String.format("%.0f", this.answer.getPremium());
        String valueWrong = String.format("%.0f", this.answer.getPremiumMiss());
        String valueStop = String.format("%.0f", this.answer.getPremiumStop());

        mv.addObject("VALUE_PREMIUM", valuePremium);
        mv.addObject("VALUE_WRONG", valueWrong);
        mv.addObject("VALUE_STOP", valueStop);
        mv.addObject("PARTICIPANT", this.user);
        mv.addObject("QUESTION", question.getQuestion());
        mv.addObject("ANSWER_ONE", options.get(0));
        mv.addObject("ANSWER_TWO", options.get(1));
        mv.addObject("ANSWER_THREE", options.get(2));
        mv.addObject("ANSWER_FOUR", options.get(3));
        mv.addObject("CORRECT_ANSWER", question.getCorrect_answer());
        mv.addObject("GUESTS", this.guests);
        mv.addObject("PLAQUES", this.plaques);
        if (!listPlaques.isEmpty()) {
            mv.addObject("LIST_PLAQUES", listPlaques);
        }
        mv.addObject("CARDS", this.cards);
        mv.setViewName("question.jsp");

        return mv;

    }

}
