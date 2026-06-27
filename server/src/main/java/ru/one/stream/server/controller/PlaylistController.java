package ru.one.stream.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.one.stream.server.dto.PlaylistDto;
import ru.one.stream.server.service.PlaylistService;

@RestController
@RequestMapping("api/v1/library")
@RequiredArgsConstructor
public class PlaylistController {

    private static final String USERNAME = "admin";

    private final PlaylistService playlistService;

    @GetMapping
    public ResponseEntity<PlaylistDto> getLibrary() {
        var librarary = playlistService.getLibrary(USERNAME);
        return ResponseEntity.ok( librarary);
    }
}
