package work.hamid.interview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.hamid.interview.domain.SearchParams;
import work.hamid.interview.domain.SearchResult;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final StackOverflowApi api;

    // return 10 newest android questions
    @Autowired
    public QuestionServiceImpl(StackOverflowApi api) {
        this.api = api;
    }

    public SearchResult newest() {
        var params = new SearchParams();
        params.setTagged(Collections.singletonList("android"));

        var results =  api.search(params);
        results.getItems().forEach(item -> item.put("date", parseDate((Integer) item.get("creation_date"))));

        return results;
    }

    // return 10 most voted android questions
    public SearchResult mostVoted() {
        var params = new SearchParams();
        params.setFromDate(LocalDateTime.now().minusWeeks(1));
        params.setTagged(Collections.singletonList("android"));
        params.setSort(SearchParams.Sort.VOTES);

        var results =  api.search(params);
        results.getItems().forEach(item -> item.put("date", parseDate((Integer) item.get("creation_date"))));

        return results;
    }

    private String parseDate(Integer epoch) {
        var ldt = Instant.ofEpochSecond(epoch).atZone(ZoneId.systemDefault()).toLocalDateTime();

        return ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
