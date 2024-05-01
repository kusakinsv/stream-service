package ru.one.stream.server.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public NewTopic topic1() {
        return new NewTopic("client", 1, (short) 1);
    }

    @Bean
    public NewTopic topic2() {
        return new NewTopic("server", 1, (short) 1);
    }
}
