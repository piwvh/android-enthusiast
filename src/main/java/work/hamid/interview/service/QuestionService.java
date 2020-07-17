package work.hamid.interview.service;

import work.hamid.interview.web.response.ApiResponse;

public interface QuestionService {

    ApiResponse newest();

    ApiResponse mostVoted();

    ApiResponse get(long id);
}
