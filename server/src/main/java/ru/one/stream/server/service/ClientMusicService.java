package ru.one.stream.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.one.stream.server.models.MusicTrackDto;

@Component
public class ClientMusicService {


    @Autowired
    ObjectMapper objectMapper;

    public MusicTrackDto addMusicTrackToPublicLibrary(MusicTrackDto musicTrackDto) {
       return null;
    }

    public MusicTrackDto addMusicTrackToUserLibrary(MusicTrackDto musicTrackDto, String username) {
    return null;
    }

    public void deleteMusicTrackFromUserLibrary(String username, int postionNumber) {

    }

    public void renamePositionInPlaylist(String username, Long playlistId, int postionNumber, String newTrackName) {

    }

}
