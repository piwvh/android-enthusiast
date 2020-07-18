package work.hamid.interview.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import work.hamid.interview.service.DataService;

@Controller
public class MainController {

    private final DataService service;

    @Autowired
    public MainController(DataService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("newest", service.newest().getItems());
        model.addAttribute("mostVoted", service.mostVoted().getItems());

        return "index";
    }

    @GetMapping("/question/{id}")
    public String question(@PathVariable long id, Model model) {
        try {
            model.addAttribute("question", service.question(id).getItems().get(0));
            return "question";
        }
        catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/search")
    public String search(Model model) {
        model.addAttribute("tags", service.tags().getItems());

        return "search";
    }
}
