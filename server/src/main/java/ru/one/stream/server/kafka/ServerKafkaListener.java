package ru.one.stream.server.kafka;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Async;
import ru.one.stream.commons.models.MusicTrackDto;
import ru.one.stream.commons.Message;
import ru.one.stream.commons.MessageHeader;
import ru.one.stream.commons.MessageType;
import ru.one.stream.server.gateway.ServerTransportService;
import ru.one.stream.server.service.ServerMusicService;
import ru.one.stream.server.service.UserServiceImpl;

import java.util.HashMap;

public class ServerKafkaListener implements ServerTransportService {
    @Value("${spring.kafka.request-topic}")
    String requestTopic;
    @Value("${spring.kafka.reply-topic}")
    String replyTopic;

    public ServerKafkaListener(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Autowired
    KafkaTemplate<String, Message> kafkaTemplate;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    ServerMusicService serverMusicService;

    @Autowired
    ObjectMapper objectMapper;

    @KafkaListener(topics = "${spring.kafka.request-topic}", groupId = "one", containerFactory = "kafkaListenerContainerFactory")
    @SendTo
    @Async
    @SneakyThrows
    public Message recieveAndSend(Message message)  {
        String uuid = message.getId();
        Object payload = null;
        if (message.getHeaders().get(MessageHeader.MESSAGE_TYPE.name()).equals(MessageType.USERS_GET_USER_DETAILS.name())) {
            String username = (String) message.getPayload();
            payload = userService.getUserDetailsByUsername(username);
        } else if (message.getHeaders().get(MessageHeader.MESSAGE_TYPE.name()).equals(MessageType.USERS_GET_USERSPACE.name())) {
            String username = (String) message.getPayload();
            payload = userService.getUserspaceByUsername(username);
        } else if (message.getHeaders().get(MessageHeader.MESSAGE_TYPE.name()).equals(MessageType.MUSIC_ADD_TRACK_TO_LIB.name())) {
            HashMap<String, Object> payloadBody = (HashMap<String, Object>) message.getPayload();
            System.out.println(payloadBody);
            MusicTrackDto musicTrackDto = objectMapper.convertValue(payloadBody.get("track"), new TypeReference<MusicTrackDto>() {});
            System.out.println(musicTrackDto);
            String username = (String) payloadBody.get("username");
            MusicTrackDto createdMusicTrackDto = serverMusicService.addMusicTrackToLibrary(musicTrackDto, username);
            payload = createdMusicTrackDto;
        } else if (message.getHeaders().get(MessageHeader.MESSAGE_TYPE.name()).equals(MessageType.MUSIC_DELETE_TRACK_FROM_LIB.name())) {
            HashMap<String, Object> payloadBody = (HashMap<String, Object>) message.getPayload();
            int positionNumber = (int) payloadBody.get("position");
            String username = (String) payloadBody.get("username");
            serverMusicService.deleteMusicTrackFromLibrary(username, positionNumber);
            payload = "success";
        }
        else if (message.getHeaders().get(MessageHeader.MESSAGE_TYPE.name()).equals(MessageType.MUSIC_RENAME_TRACK_FROM_PLAYLIST.name())) {
            HashMap<String, Object> payloadBody = (HashMap<String, Object>) message.getPayload();
            int positionNumber = (int) payloadBody.get("position");
            String username = (String) payloadBody.get("username");
            Long playlistId = (Long) payloadBody.get("playlistId");
            String newTrackName = (String) payloadBody.get("newTrackName");
            serverMusicService.renameMusicTrack(username, playlistId, positionNumber, newTrackName);
            payload = "success";
        }
        return new Message(uuid, payload);
    }


    private MessageType getMessageType(Message message){
        return MessageType.valueOf(message.getHeaders().get(MessageHeader.MESSAGE_TYPE.name()));
    }

}
