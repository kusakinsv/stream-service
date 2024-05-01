package ru.one.stream.client.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.one.stream.commons.models.MusicTrackDto;
import ru.one.stream.client.service.ClientMusicService;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1/music")
public class MusicController {

    private final ClientMusicService clientMusicService;

    @PostMapping
    public ResponseEntity<?> addMusic(@RequestBody MusicTrackDto dto) {
        return ResponseEntity.ok(clientMusicService.addMusicTrackToPublicLibrary(dto));
    }

//    @PostMapping("/addToPlaylist/{playlistId}")
//    public ResponseEntity<?> addMusicToPlaylist(@RequestBody MusicTrackDto dto, @PathVariable Long playlistId) {
//        return ResponseEntity.ok(musicService.addMusicTrackToPlaylist(dto, playlistId));
//    }




}

