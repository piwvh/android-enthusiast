package work.hamid.interview.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Configuration
public class AppConfig {

    private final AppProperties appProperties;

    @Autowired
    public AppConfig(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    // cors
    @Bean
    public CorsFilter corsFilter() {
        var source = new UrlBasedCorsConfigurationSource();
        var config = appProperties.getCors();
        if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
            source.registerCorsConfiguration("/**", config);
        }

        config.addAllowedOrigin("*");

        return new CorsFilter(source);
    }

    // set default locale
    @Bean
    public LocaleResolver localeResolver() {
        var localeResolver = new FixedLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);

        return localeResolver;
    }

    @Bean
    public RestTemplate restTemplate() {
        var requestFactory = new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().build());
        return new RestTemplate(requestFactory);
    }

    // jackson date format
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customDateFormat() {
        var format1 = "yyyy-MM-dd HH:mm:ss";
        var format2 = "yyyy-MM-dd";

        return builder -> {
            builder.simpleDateFormat(format1);
            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(format1)));
            builder.deserializers(new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(format1)));
            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(format2)));
            builder.deserializers(new LocalDateDeserializer(DateTimeFormatter.ofPattern(format2)));
        };
    }
}
