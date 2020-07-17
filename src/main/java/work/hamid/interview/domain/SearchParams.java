package work.hamid.interview.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class SearchParams extends AbstractParams {

    private int page = 1;

    private int pageSize = 10;

    @PastOrPresent @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fromDate;

    @PastOrPresent @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime toDate;

    private String q;

    private List<String> tagged = new ArrayList<>();

    private Sort sort = Sort.CREATION;

    private Order order = Order.DESC;

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
