package work.hamid.interview.service;

import work.hamid.interview.domain.SearchResult;

public interface QuestionService {

    SearchResult newest();

    SearchResult mostVoted();
}
