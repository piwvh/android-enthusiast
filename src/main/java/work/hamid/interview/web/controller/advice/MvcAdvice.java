package work.hamid.interview.web.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import work.hamid.interview.util.HttpUtil;

// add global attributes to model

@ControllerAdvice
public class MvcAdvice {

    @ModelAttribute("baseUrl")
    public String baseUrl() {
        return HttpUtil.getBaseUrl();
    }
}
