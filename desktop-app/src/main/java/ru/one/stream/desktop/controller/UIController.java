package ru.one.stream.desktop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.one.stream.commons.models.MusicTrackDto;
import ru.one.stream.client.service.SearchService;
import ru.one.stream.desktop.player.AudioPlayer;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class UIController {
    @Autowired
    private SearchService searchService;
    private final List<MusicTrackDto> trackList = new ArrayList<>();

    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

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

    @GetMapping("/play/{number}")
    public String play(Model model, @PathVariable int number) {
        String mainPageMessage = "Проигрывается: № " + (number + 1) + " " + trackList.get(number).getName();
        model.addAttribute("playlist", trackList);
        model.addAttribute("mainPageMessage", mainPageMessage);
        if (AudioPlayer.isPlayed) AudioPlayer.stopMusic();
        AudioPlayer.playMusic(trackList.get(number).getUrl());
        return "search";
    }

    @GetMapping("/stop")
    public String stop(Model model) {
        String mainPageMessage = "Остановлено: " + AudioPlayer.getMusicTrack();
        model.addAttribute("playlist", trackList);
        model.addAttribute("mainPageMessage", mainPageMessage);
        AudioPlayer.stopMusic();
        return "search";
    }

//
//    @GetMapping("download/{number}")
//    public String addTrackToPlayList(Model model, @PathVariable int number) throws JsonProcessingException {
//        MusicTrack musicTrack;
//        musicTrack = trackList.get(number);
//        serviceLayer.addTrackToPlayList(musicTrack);
//        String mainPageMessage = "Музыкальный трек № " + (number + 1) + " " + musicTrack.getTrackInfo() + " Добавлен в плейлист";
//        model.addAttribute("playlist", trackList);
//        model.addAttribute("mainPageMessage", mainPageMessage);
//        return "search";
//    }
}

