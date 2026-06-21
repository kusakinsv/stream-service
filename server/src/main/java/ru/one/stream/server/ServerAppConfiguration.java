package ru.one.stream.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import ru.one.stream.server.service.SearchService;
import ru.one.stream.server.service.impl.MusicSearchService;

@Configuration
@ComponentScan("ru.one.stream.server")
@PropertySource("classpath:application.yaml")
public class ServerAppConfiguration {

    @Bean
    ObjectMapper objectMapper(){
        return new ObjectMapper();
    }


    @Bean
    SearchService searchService() {
        return new MusicSearchService();
    }
}
