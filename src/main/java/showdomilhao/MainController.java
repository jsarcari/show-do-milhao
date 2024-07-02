package showdomilhao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import showdomilhao.model.Answer;
import showdomilhao.model.Participant;
import showdomilhao.model.Question;
import showdomilhao.service.ReadApi;

@Controller
public class MainController {

    private ReadApi api;
    private ArrayList<Question> questions;
    private Answer answer;
    private int indexQuestion;
    private List<Integer> idsChoice;
    private int index = 0;
    private Participant user;

    @GetMapping("/")
    public ModelAndView indexPage() {
        ModelAndView mv = new ModelAndView();
        this.api = new ReadApi();
        this.questions = this.api.getData();
        this.user = new Participant(null);
        this.answer = new Answer(1000);
        this.idsChoice = new ArrayList<Integer>();
        mv.setViewName("name.jsp");

        return mv;
    }

    @PostMapping("/question")
    public ModelAndView questionPage(Participant participant, @RequestParam int id) {
        ModelAndView mv = new ModelAndView();
        this.index = id-1;
        this.indexQuestion = this.answer.choiceQuestion(this.questions, this.idsChoice, this.index);
        this.idsChoice.add(this.indexQuestion);
        Question question = this.questions.get(this.indexQuestion);
        List options = this.answer.createArrayOptions(question);
        if(this.index!=0) {
            this.answer.setPremiumStop(this.answer.getPremium());
            this.answer.setPremiumMiss(this.answer.getPremium()/2);
            this.answer.calculatePremium(this.index);
        } else {
            this.user = participant;
        }
        
        String valuePremium = String.format("%.0f", this.answer.getPremium());
        String valueWrong = String.format("%.0f", this.answer.getPremiumMiss());
        String valueStop = String.format("%.0f", this.answer.getPremiumStop());

        mv.addObject("VALUE_PREMIUM", valuePremium);
        mv.addObject("VALUE_WRONG", valueWrong);
        mv.addObject("VALUE_STOP", valueStop);
        mv.addObject("PARTICIPANT", user.getName());
        mv.addObject("QUESTION", question.getQuestion());
        mv.addObject("ANSWER_ONE", options.get(0));
        mv.addObject("ANSWER_TWO", options.get(1));
        mv.addObject("ANSWER_THREE", options.get(2));
        mv.addObject("ANSWER_FOUR", options.get(3));
        mv.addObject("CORRECT_ANSWER", question.getCorrect_answer());
        mv.setViewName("question.jsp");

        return mv;

    }
}
