package ru.one.stream.server.mapper;

import org.springframework.stereotype.Component;
import ru.one.stream.commons.models.userspace.PositionDto;
import ru.one.stream.server.entities.PlaylistPosition;

@Component
public class PlaylistPositionMapper {

    public PositionDto toPositionDto(PlaylistPosition position){
        PositionDto positionDto = new PositionDto();
        positionDto.setPosition(position.getPosition());
        positionDto.setTitle(position.getTitle());
        positionDto.setUrl(position.getTrack().getUrl());
        return positionDto;
    }
}
