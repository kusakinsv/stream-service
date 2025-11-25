package ru.one.stream.internetsercher.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.one.stream.internetsercher.models.MusicTrackDto;
import ru.one.stream.internetsercher.service.SearchService;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class MusicSearchController {
    private final SearchService searchService;

    @CrossOrigin
    @GetMapping("search")
    public ResponseEntity<?> searchTrack(@RequestParam(value = "trackName") String trackName) {
        List<MusicTrackDto> tracks = searchService.findMusicTrack(trackName);
        return ResponseEntity.ok(tracks);
    }
}

