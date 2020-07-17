package work.hamid.interview.web.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class ApiResponse {

    private List<HashMap<String, Object>> items = new ArrayList<>();
}
