package ru.one.stream.server.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.one.stream.commons.models.userspace.PlaylistDto;
import ru.one.stream.commons.models.userspace.PositionDto;
import ru.one.stream.server.entities.Playlist;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlaylistMapper {

    @Autowired
    PlaylistPositionMapper positionsMapper;

    public PlaylistDto toPlaylistDto(Playlist playlist){
        PlaylistDto playlistDto = new PlaylistDto();
        playlistDto.setId(playlist.getId());
        playlistDto.setPlaylistTitle(playlist.getTitle());
        playlistDto.setMain(playlist.isMain());
        List<PositionDto> positions = playlist.getPlaylistPositions().stream().map(positionsMapper::toPositionDto)
                .sorted(Comparator.comparingInt(PositionDto::getPosition)).collect(Collectors.toList());
        playlistDto.setPositions(positions);
        return playlistDto;
    }
}
