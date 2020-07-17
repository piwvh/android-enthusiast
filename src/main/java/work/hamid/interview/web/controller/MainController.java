package work.hamid.interview.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import work.hamid.interview.service.QuestionService;

@Controller
public class MainController {

    private final QuestionService service;

    @Autowired
    public MainController(QuestionService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("newest", service.newest().getItems());
        model.addAttribute("mostVoted", service.mostVoted().getItems());

        return "index";
    }

    @GetMapping("/question/{id}")
    public String question(@PathVariable int id) {
        return "question";
    }

}
