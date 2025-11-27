package ru.one.stream.internetsercher.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.one.stream.internetsercher.models.MusicTrack;
import ru.one.stream.internetsercher.service.MainSearchServiceImpl;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class MusicSearchController {
    private final MainSearchServiceImpl searchService;

    @CrossOrigin
    @GetMapping("search")
    public ResponseEntity<?> searchTrack(@RequestParam(value = "query") String trackName) {
        List<MusicTrack> tracks = searchService.search(trackName);
        return ResponseEntity.ok(tracks);
    }
}

