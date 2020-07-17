package work.hamid.interview.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import work.hamid.interview.domain.SearchParams;
import work.hamid.interview.domain.SearchResult;

@Component
public class StackOverflowApi {

    private final RestTemplate restTemplate;
    private final String baseUrl = "https://api.stackexchange.com/2.2";

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public StackOverflowApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public SearchResult search(SearchParams params) {
        try {
            return restTemplate.getForObject(baseUrl + "/search/advanced?" + params.toQueryParams(), SearchResult.class);

        }
        catch (Exception e) {
            logger.error("Exception occurred fetching the api: {}", e.getMessage());
            return new SearchResult();
        }
    }
}
