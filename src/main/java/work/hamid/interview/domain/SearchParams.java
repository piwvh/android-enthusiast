package work.hamid.interview.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Data
public class SearchParams {

    private int page = 1;

    private int pageSize = 10;

    private LocalDateTime fromDate;

    private LocalDateTime toDate;

    private String q;

    private List<String> tagged = new ArrayList<>();

    private Sort sort = Sort.CREATION;

    private Order order = Order.DESC;

    private String site = "stackoverflow";

    public enum Sort {
        ACTIVITY,
        VOTES,
        CREATION,
        RELEVANCE
    }

    public enum Order {
        ASC,
        DESC
    }

    public String toQueryParams() {
        var params = new ArrayList<String>();
        params.add("page=" + page);
        params.add("pagesize=" + pageSize);
        if(fromDate != null) {
            params.add("fromdate=" + fromDate.atZone(ZoneId.systemDefault()).toEpochSecond());
        }
        if(toDate != null) {
            params.add("todate=" + toDate.atZone(ZoneId.systemDefault()).toEpochSecond());
        }
        if(q != null) {
            params.add("q=" + q);
        }
        if(tagged.size() > 0) {
            params.add("tagged=" + String.join(";", tagged));
        }
        params.add("sort=" + sort.name().toLowerCase());
        params.add("order=" + order.name().toLowerCase());
        params.add("site=" + site);

        return String.join("&", params);
    }
}
