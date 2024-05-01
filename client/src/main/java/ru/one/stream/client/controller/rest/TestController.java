package ru.one.stream.client.controller.rest;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.requestreply.RequestReplyMessageFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.one.stream.client.dto.UserTestDto;
import ru.one.stream.client.gateway.kafka.KafkaTransportService;
import ru.one.stream.client.service.UserService;
import ru.one.stream.commons.Message;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/v1/test")
public class TestController {

    @Value("${spring.kafka.request-topic}")
    String requestTopic;
    @Value("${spring.kafka.reply-topic}")
    String replyTopic;

    @Autowired
    ReplyingKafkaTemplate<String, Message, Message> kafkaTemplate;

    @Autowired
    KafkaTransportService transportService;

    @Autowired
    UserService userService;

    private List<UserTestDto> USERS = Stream.of(
            new UserTestDto(1L, "Ivan"),
            new UserTestDto(2L, "Sergei"),
            new UserTestDto(3L, "Petr")
    ).collect(Collectors.toList());

    @GetMapping("/getUserspace/{username}")
    public ResponseEntity<?> getUserSpace(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserspaceByUsername(username));
    }

    @GetMapping("admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin(){
        return "admin ok";
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserTestDto getById(@PathVariable Long id){
        return USERS.stream().filter(developer -> developer.getId().equals(id))
                .findFirst().orElse(null);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserTestDto> create(@RequestBody UserTestDto user){
        this.USERS.add(user);
        return USERS;
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('developers:write')")
    public void deleteById(@PathVariable Long id){
        this.USERS.removeIf(developer -> developer.getId().equals(id));
    }

    @GetMapping("test")
    @ResponseBody
    public ResponseEntity<?> test() throws InterruptedException, ExecutionException {
        Message requestMessage = new Message("Hello");
        requestMessage.setId(UUID.randomUUID().toString());
        Message response = transportService.sendMessage(requestMessage);
        return ResponseEntity.ok("Принято: " + response.getPayload());
    }


    @GetMapping("test2")
    @ResponseBody
    public ResponseEntity<?> test2() throws InterruptedException, ExecutionException {
        Message requestMessage = new Message("Hello");
        String corId = UUID.randomUUID().toString();
        requestMessage.setId(corId);
            RequestReplyMessageFuture<String, Message> future = kafkaTemplate
                    .sendAndReceive(MessageBuilder.withPayload(requestMessage)
                            .setHeader(KafkaHeaders.TOPIC, requestTopic.getBytes())
                            .setHeader(KafkaHeaders.REPLY_TOPIC, replyTopic.getBytes())
                            .build());
        var reply = (Message) future.completable().get().getPayload();
            return ResponseEntity.ok("Принято: " + reply.getPayload());
        }


    @GetMapping("test3")
    @ResponseBody
    public CompletableFuture<ResponseEntity<?>> test3() {
        Message requestMessage = new Message("Hello");
        String corId = UUID.randomUUID().toString();
        requestMessage.setId(corId);
        ProducerRecord<String, Message> record = new ProducerRecord<>(requestTopic, requestMessage);
        record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, replyTopic.getBytes()));

        return CompletableFuture.supplyAsync(() -> {
            RequestReplyFuture<String, Message, Message> sendAndReceive = kafkaTemplate.sendAndReceive(record);
            try {
                ConsumerRecord<String, Message> consumerRecord = sendAndReceive.completable().get();
                return consumerRecord.value().getPayload();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException("Ошибка при получении ответа из Kafka", e);
            }
        }).thenApply(payload -> ResponseEntity.ok("Принято: " + payload));
    }


    @GetMapping("test4")
    @Async
    public CompletableFuture<ResponseEntity<?>> test4() {
        Message requestMessage = new Message("Hello");
        String corId = UUID.randomUUID().toString();
        requestMessage.setId(corId);

        RequestReplyMessageFuture<String, Message> future = kafkaTemplate
                .sendAndReceive(MessageBuilder.withPayload(requestMessage)
                        .setHeader(KafkaHeaders.TOPIC, requestTopic.getBytes())
                        .setHeader(KafkaHeaders.REPLY_TOPIC, replyTopic.getBytes())
                        .build());

        return future.completable().thenApply(reply -> {
            // Обработка ответа
            String payload = (String) reply.getPayload();
            return ResponseEntity.ok("Принято: " + payload);
        });
    }
}

