package work.hamid.interview.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class QuestionParams extends AbstractParams {

    private long id;

    private String filter = "!.PJ-af7CD0pjq)Sibp*SX6O_dkdl8ke5up4CcMjVfS*4zu*rJjs4fNq6M9Z9wm";

    public QuestionParams(long id) {
        this.id = id;
    }

    @Override
    public String toQueryParams() {
        var params = new ArrayList<String>();

        params.add("site=" + site);
        params.add("filter=" + filter);

        return String.join("&", params);
    }
}
