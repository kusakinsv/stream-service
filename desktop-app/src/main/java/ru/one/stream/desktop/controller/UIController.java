package ru.one.stream.desktop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.one.stream.server.models.MusicTrackDto;
import ru.one.stream.server.service.SearchService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class UIController {
    @Autowired
    private SearchService searchService;
    private final List<MusicTrackDto> trackList = new ArrayList<>();

    @PostMapping("search")
    public String searchTrack(Model model, @RequestParam(value = "trackName", required = false) String trackName) {
        trackList.clear();
        trackList.addAll(searchService.findMusicTrack(trackName));
        if (trackList.isEmpty()) {
            model.addAttribute("playlist", trackList);
            model.addAttribute("mainPageMessage", "Не найдено");
            return "search";
        } else {
            String mainPageMessage = "Найденные треки: ";
            model.addAttribute("playlist", trackList);
            model.addAttribute("mainPageMessage", mainPageMessage);
            if (trackName == null) {
                return "no parameters";
            }
            return "search";
        }
    }
}

