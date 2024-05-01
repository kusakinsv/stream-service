package ru.one.stream.client.controller.thym;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.one.stream.commons.models.MusicTrackDto;
import ru.one.stream.client.service.ClientMusicService;
import ru.one.stream.client.utils.UserDataHolder;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/playlist")
public class PlaylistController {

    private final ClientMusicService clientMusicService;
    private final UserDataHolder userDataHolder;

    @GetMapping("/")
    private String loadWorkspace(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("currentUser", user.getUsername());
        return "userspace";
    }

    @PostMapping("/addToLibrary/{trackNumber}")
    public String addMusicTrackToLibrary(Model model, @AuthenticationPrincipal User user, @PathVariable int trackNumber) {
        List<MusicTrackDto> trackList = (List<MusicTrackDto>) userDataHolder.getDataMap().get("trackList");
        MusicTrackDto currentDto = trackList.get(trackNumber);
        model.addAttribute("currentUser", user.getUsername());
        model.addAttribute("trackList", trackList);
        MusicTrackDto dublicate = currentDto.createDublicate();
        dublicate.setName((String) userDataHolder.getDataMap().get("trackName"));
        clientMusicService.addMusicTrackToUserLibrary(dublicate, user.getUsername());
        return "redirect:/search";
    }

    @GetMapping("/deleteFromLibrary/{trackNumber}")
    public String deleteMusicTrackFromLibrary(Model model, @AuthenticationPrincipal User user, @PathVariable int trackNumber) {
        clientMusicService.deleteMusicTrackFromUserLibrary(user.getUsername(), trackNumber);
        return "redirect:/userspace";
    }


//    @PostMapping("/addToPlaylist/{trackNumber}/{playlistId}")
//    public void addMusicTrackToPlaylist (Model model, @PathVariable int trackNumber, @PathVariable Long playlistId) {
//        List<MusicTrackDto> trackList = (List<MusicTrackDto>) model.getAttribute("playlist");
//        MusicTrackDto currentDto = trackList.get(trackNumber-1);
//        musicService.addMusicTrackToPlaylist(currentDto, playlistId);
//    }


}
