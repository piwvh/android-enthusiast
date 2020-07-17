package work.hamid.interview.util;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class HttpUtil {

    public static String getBaseUrl() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    }

    public static String getUrl(String uri) {
        return getBaseUrl() + "/"  + uri;
    }
}
