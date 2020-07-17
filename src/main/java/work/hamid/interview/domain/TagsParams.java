package work.hamid.interview.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class TagsParams extends AbstractParams {

    @Override
    public String toQueryParams() {
        return "site=" + site;
    }
}
