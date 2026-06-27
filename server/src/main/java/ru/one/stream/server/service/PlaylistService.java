package ru.one.stream.server.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.one.stream.server.dto.PlaylistDto;
import ru.one.stream.server.entities.Playlist;
import ru.one.stream.server.mapper.PlaylistMapper;
import ru.one.stream.server.repositories.PlaylistRepository;
import ru.one.stream.server.repositories.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PlaylistService {
    private PlaylistRepository playlistRepository;
    private PlaylistMapper playlistMapper;
    private UserRepository userRepository;

    public PlaylistDto getLibrary(String username) {
        return userRepository.findUserByUsername(username).get()
                .getPlaylists().stream().filter(Playlist::getIsMain)
                .findFirst()
                .map(playlistMapper::toDto)
                .orElseThrow();
    }

    public PlaylistDto getPlayList(Long playlistId) {
        return playlistRepository.findById(playlistId).stream()
                .findFirst()
                .map(playlistMapper::toDto)
                .orElseThrow();
    }

    public List<Long> getPlaylistsId(String username) {
        return userRepository.findUserByUsername(username).get()
                .getPlaylists()
                .stream()
                .map(Playlist::getId).toList();
    }
}
