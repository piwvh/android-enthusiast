package work.hamid.interview.web.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import work.hamid.interview.util.HttpUtil;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

// add global attributes to model

@ControllerAdvice
public class MvcAdvice {

    @ModelAttribute("baseUrl")
    public String baseUrl() {
        return HttpUtil.getBaseUrl();
    }

    @ModelAttribute("today")
    public String today() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
