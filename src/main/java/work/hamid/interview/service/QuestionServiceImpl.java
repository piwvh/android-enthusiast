package work.hamid.interview.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.hamid.interview.domain.SearchParams;
import work.hamid.interview.exception.NotFoundException;
import work.hamid.interview.web.response.ApiResponse;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final StackOverflowApi api;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    // cached tags
    private ApiResponse tags;

    // return 10 newest android questions
    @Autowired
    public QuestionServiceImpl(StackOverflowApi api) {
        this.api = api;
    }

    public ApiResponse newest() {
        var params = new SearchParams();
        params.setTagged(Collections.singletonList("android"));

        var results =  api.search(params);
        results.getItems().forEach(item -> item.put("date", parseDate((Integer) item.get("creation_date"))));

        return results;
    }

    // return 10 most voted android questions from last week
    public ApiResponse mostVoted() {
        var params = new SearchParams();
        params.setFromDate(LocalDateTime.now().minusWeeks(1));
        params.setTagged(Collections.singletonList("android"));
        params.setSort(SearchParams.Sort.VOTES);

        var results = api.search(params);
        results.getItems().forEach(item -> item.put("date", parseDate((Integer) item.get("creation_date"))));

        return results;
    }

    @Override
    public ApiResponse question(long id) {
        var results = api.question(id);
        if(results.getItems().size() == 0) {
            logger.error("Questions api returned nothing.");
            throw new NotFoundException();
        }

        results.getItems().forEach(item -> {
            item.put("date", parseDate((Integer) item.get("creation_date")));
            var comments = (List<HashMap<String, Object>>) item.get("comments");
            if(comments != null) {
                comments.forEach(comment -> comment.put("date", parseDate((Integer) comment.get("creation_date"))));
            }

            var answers = (List<HashMap<String, Object>>) item.get("answers");
            if(answers != null) {
                answers.forEach(answer -> {
                    answer.put("date", parseDate((Integer) answer.get("creation_date")));

                    var comments2 = (List<HashMap<String, Object>>) answer.get("comments");
                    if(comments2 != null) {
                        comments2.forEach(comment -> comment.put("date", parseDate((Integer) comment.get("creation_date"))));
                    }
                });
            }
        });

        return results;
    }

    @Override
    public ApiResponse tags() {
        if(tags != null) {
            return tags;
        }
        tags = api.tags();

        return tags;
    }

    @Override
    public ApiResponse search(SearchParams params) {
        var results =  api.search(params);
        results.getItems().forEach(item -> item.put("date", parseDate((Integer) item.get("creation_date"))));

        return results;
    }

    private String parseDate(Integer epoch) {
        var ldt = Instant.ofEpochSecond(epoch).atZone(ZoneId.systemDefault()).toLocalDateTime();

        return ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
