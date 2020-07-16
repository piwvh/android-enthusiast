package work.hamid.interview.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
@Data
public class AppProperties {

    private CorsConfiguration cors = new CorsConfiguration();
}