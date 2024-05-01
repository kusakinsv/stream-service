package ru.one.stream.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.context.WebApplicationContext;
import ru.one.stream.client.service.SearchEnginesService;
import ru.one.stream.client.service.SearchService;
import ru.one.stream.client.utils.UserDataHolder;
import ru.one.stream.commons.Message;
//import ru.one.stream.commons.kafka.KafkaConsumerConfig;
//import ru.one.stream.commons.kafka.KafkaProducerConfig;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableAsync
@ComponentScan("ru.one.stream.client")
public class ClientConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    String bootstrapServers;
    @Value("${spring.kafka.reply-topic}")
    String replyTopic;


    @Bean
    public ReplyingKafkaTemplate<String, Message, Message> replyKafkaTemplate(ProducerFactory<String, Message> producerFactory, ConcurrentMessageListenerContainer<String, Message> replyContainer) {
        return new ReplyingKafkaTemplate<>(producerFactory, replyContainer);
    }
    @Bean
    public ConcurrentMessageListenerContainer<String, Message> replyContainer(ConsumerFactory<String, Message> consumerFactory) {
        ContainerProperties containerProperties = new ContainerProperties(replyTopic);
        return new ConcurrentMessageListenerContainer<>(consumerFactory, containerProperties);
    }

    @Bean
    public ProducerFactory<String, Message> producerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(
                props,
                new StringSerializer(),
                new JsonSerializer<>()
        );
    }

    @Bean
    public ConsumerFactory<String, Message> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "one");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                new JsonDeserializer<>(Message.class)
        );
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION,
            proxyMode = ScopedProxyMode.TARGET_CLASS)
    UserDataHolder userDataHolder(){
        return new UserDataHolder();
    }

    @Bean
    ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    SearchService searchService() {
        return new SearchEnginesService();
    }


}
