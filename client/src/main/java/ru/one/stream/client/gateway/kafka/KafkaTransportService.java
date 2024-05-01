package ru.one.stream.client.gateway.kafka;

import lombok.SneakyThrows;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Component;
import ru.one.stream.client.gateway.ClientTransportService;
import ru.one.stream.commons.Message;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Component
public class KafkaTransportService implements ClientTransportService {

    @Value("${spring.kafka.request-topic}")
    String requestTopic;

    @Value("${spring.kafka.reply-topic}")
    String replyTopic;

    @Autowired
    ReplyingKafkaTemplate<String, Message, Message> kafkaTemplate;

    @SneakyThrows
    public Message sendMessage(Message message) {
        ProducerRecord<String, Message> record = new ProducerRecord<>(requestTopic, message);
        record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, replyTopic.getBytes()));
        RequestReplyFuture<String, Message, Message> sendAndReceive = kafkaTemplate.sendAndReceive(record);
        ConsumerRecord<String, Message> consumerRecord = sendAndReceive.completable().get();
        return consumerRecord.value();
    }


    public Message sendMessageAsync(Message message) throws ExecutionException, InterruptedException {
        ProducerRecord<String, Message> record = new ProducerRecord<>(requestTopic, message);
        record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, replyTopic.getBytes()));
        RequestReplyFuture<String, Message, Message> sendAndReceive = kafkaTemplate.sendAndReceive(record);
        ConsumerRecord<String, Message> consumerRecord = sendAndReceive.completable().get();
        return consumerRecord.value();
    }
}
