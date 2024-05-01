package ru.one.stream.commons.models.userspace;

import lombok.Data;

import java.util.LinkedList;

@Data
public class UserspaceDto {

    private String username;

    private LinkedList<PlaylistDto> playlistDtos;

}
