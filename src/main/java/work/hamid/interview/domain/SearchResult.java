package work.hamid.interview.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class SearchResult {

    private List<HashMap<String, Object>> items = new ArrayList<>();
}
