package ru.one.stream.client.controller.thym;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.one.stream.client.models.ThymData;
import ru.one.stream.client.service.ClientMusicService;
import ru.one.stream.client.utils.UserDataHolder;
import ru.one.stream.commons.models.MusicTrackDto;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/position")
public class PositionController {

    private final ClientMusicService clientMusicService;
    private final UserDataHolder userDataHolder;

    @RequestMapping(value = "/rename/{newTrackName}", method = RequestMethod.POST)
//    @RequestMapping(value = "/rename", method = RequestMethod.POST)
    public String renamePositionFromPlaylist(Model model,
                                             @AuthenticationPrincipal User user,
                                             @ModelAttribute("thymData") ThymData thymData,
                                             @PathVariable String newTrackName) {

        System.out.println(thymData.getValue1());
//        clientMusicService.renamePositionInPlaylist(
//                user.getUsername(),
//                (Long) userDataHolder.getDataMap().get("playlistId"),
//                (int) userDataHolder.getDataMap().get("trackNumber"),
//                thymData.getValue1());
//        userDataHolder.getDataMap().remove("playlistId");
//        userDataHolder.getDataMap().remove("trackNumber");
        return "redirect:/userspace";
    }




//    @PostMapping("/addToPlaylist/{trackNumber}/{playlistId}")
//    public void addMusicTrackToPlaylist (Model model, @PathVariable int trackNumber, @PathVariable Long playlistId) {
//        List<MusicTrackDto> trackList = (List<MusicTrackDto>) model.getAttribute("playlist");
//        MusicTrackDto currentDto = trackList.get(trackNumber-1);
//        musicService.addMusicTrackToPlaylist(currentDto, playlistId);
//    }


}
