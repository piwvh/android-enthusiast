package work.hamid.interview.service;

import work.hamid.interview.domain.SearchParams;
import work.hamid.interview.web.response.ApiResponse;

public interface QuestionService {

    ApiResponse newest();

    ApiResponse mostVoted();

    ApiResponse question(long id);

    ApiResponse tags();

    ApiResponse search(SearchParams params);
}
