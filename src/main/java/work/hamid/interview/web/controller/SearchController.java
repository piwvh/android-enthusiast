package work.hamid.interview.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.hamid.interview.domain.SearchParams;
import work.hamid.interview.service.QuestionService;
import work.hamid.interview.web.response.ApiResponse;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
public class SearchController {

    private final QuestionService service;

    @Autowired
    public SearchController(QuestionService service) {
        this.service = service;
    }

    @PostMapping("/search")
    public ApiResponse search(@RequestBody @Valid SearchParams params) {
        return service.search(params);
    }
}
