package ru.one.stream.server.dto;

import lombok.Data;

import java.util.List;

@Data
public class PlaylistDto {

    private Long id;

    private String playlistTitle;

    private List<PositionDto> positions;

    private boolean isMain;

}
