package work.hamid.interview.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.hamid.interview.domain.SearchParams;
import work.hamid.interview.domain.SearchResult;
import work.hamid.interview.service.StackOverflowApi;

import java.time.LocalDateTime;
import java.util.Collections;

@RestController
@RequestMapping("api")
public class SearchController {

    private final StackOverflowApi api;

    @Autowired
    public SearchController(StackOverflowApi api) {
        this.api = api;
    }

    // return 10 newest android questions
    @GetMapping("/newest")
    public SearchResult newest() {
        var params = new SearchParams();
        params.setTagged(Collections.singletonList("android"));

        return api.search(params);
    }

    // return 10 most voted android questions
    @GetMapping("/most-voted")
    public SearchResult mostVoted() {
        var params = new SearchParams();
        params.setFromDate(LocalDateTime.now().minusWeeks(1));
        params.setTagged(Collections.singletonList("android"));
        params.setSort(SearchParams.Sort.VOTES);

        return api.search(params);
    }
}
