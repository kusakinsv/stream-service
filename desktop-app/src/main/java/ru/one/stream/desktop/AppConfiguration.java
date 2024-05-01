package ru.one.stream.desktop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.one.stream.client.service.SearchEnginesService;
import ru.one.stream.client.service.SearchService;

@Configuration
@PropertySource("classpath:application.yaml")
@ComponentScan("ru.one.stream.desktop")
public class AppConfiguration {

    @Bean
    SearchService searchService() {
        return new SearchEnginesService();
    }

}
