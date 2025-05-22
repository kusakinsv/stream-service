package ru.one.stream.desktop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.one.stream.server.service.SearchEnginesService;
import ru.one.stream.server.service.SearchService;

@Configuration
@PropertySource("classpath:application.yaml")
@ComponentScan("ru.one.stream.desktop")
public class AppConfiguration {

    @Bean
    SearchService searchService() {
        return new SearchEnginesService();
    }

}
