package ru.one.stream.client.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.one.stream.commons.models.MusicTrackDto;
import ru.one.stream.client.gateway.ClientTransportService;
import ru.one.stream.commons.Message;
import ru.one.stream.commons.MessageHeader;
import ru.one.stream.commons.MessageType;

import java.util.HashMap;

@Component
public class ClientMusicService {

    @Autowired
    ClientTransportService clientTransportService;

    @Autowired
    ObjectMapper objectMapper;

    public MusicTrackDto addMusicTrackToPublicLibrary(MusicTrackDto musicTrackDto) {
        Message requestMessage = new Message();
        requestMessage.setHeader(MessageHeader.MESSAGE_TYPE.name(), MessageType.MUSIC_ADD_TRACK_TO_LIB.name());
        requestMessage.setPayload(musicTrackDto);
        Message response = clientTransportService.sendMessage(requestMessage);
        return (MusicTrackDto) response.getPayload();
    }

    public MusicTrackDto addMusicTrackToUserLibrary(MusicTrackDto musicTrackDto, String username) {
        Message requestMessage = new Message();
        requestMessage.setHeader(MessageHeader.MESSAGE_TYPE.name(), MessageType.MUSIC_ADD_TRACK_TO_LIB.name());
        requestMessage.setPayload(new HashMap<String, Object>() {{
            put("track", musicTrackDto);
            put("username", username);
        }});
        Message response = clientTransportService.sendMessage(requestMessage);
        return objectMapper.convertValue(response.getPayload(), new TypeReference<MusicTrackDto>() {});
    }

    public void deleteMusicTrackFromUserLibrary(String username, int postionNumber) {
        Message requestMessage = new Message();
        requestMessage.setHeader(MessageHeader.MESSAGE_TYPE.name(), MessageType.MUSIC_DELETE_TRACK_FROM_LIB.name());
        requestMessage.setPayload(new HashMap<String, Object>() {{
            put("username", username);
            put("position", postionNumber);
        }});
        clientTransportService.sendMessage(requestMessage);
    }

    public void renamePositionInPlaylist(String username, Long playlistId, int postionNumber, String newTrackName) {
        Message requestMessage = new Message();
        requestMessage.setHeader(MessageHeader.MESSAGE_TYPE.name(), MessageType.MUSIC_RENAME_TRACK_FROM_PLAYLIST.name());
        requestMessage.setPayload(new HashMap<String, Object>() {{
            put("username", username);
            put("playlistId", playlistId);
            put("position", postionNumber);
            put("newTrackName", newTrackName);
        }});
        clientTransportService.sendMessage(requestMessage);
    }

}
