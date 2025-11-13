package ru.one.stream.desktop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.one.stream.server.models.MusicTrackDto;
import ru.one.stream.server.service.SearchService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UIController {
    private final SearchService searchService;

    @CrossOrigin
    @GetMapping("search")
    public ResponseEntity<?> searchTrack(@RequestParam(value = "trackName") String trackName) {
        List<MusicTrackDto> tracks = searchService.findMusicTrack(trackName);
        return ResponseEntity.ok(tracks);
    }
}

