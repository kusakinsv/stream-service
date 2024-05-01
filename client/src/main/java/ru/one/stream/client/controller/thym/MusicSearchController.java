package ru.one.stream.client.controller.thym;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.one.stream.commons.models.MusicTrackDto;
import ru.one.stream.client.service.SearchService;
import ru.one.stream.client.utils.UserDataHolder;

import java.util.ArrayList;
import java.util.List;

@RequestMapping
@Controller
public class MusicSearchController {

    @Autowired
    UserDataHolder userDataHolder;

    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public String searchPage(Model model, @AuthenticationPrincipal User user) {
        if (userDataHolder.getDataMap().containsKey("trackList")) {
            model.addAttribute("currentUser", user.getUsername());
            model.addAttribute("trackList", userDataHolder.getDataMap().get("trackList"));
        } else {
            model.addAttribute("currentUser", user.getUsername());
        }
        return "search";
    }

    @PostMapping("/search")
    public String searchTrack(
            Model model,
            @RequestParam(value = "trackName", required = false) String trackName,
            @AuthenticationPrincipal User user
    ) {
        List<MusicTrackDto> trackList = new ArrayList<>();
        model.addAttribute("currentUser", user.getUsername());
        if (!isCorrect(trackName)){
            model.addAttribute("trackList", trackList);
            model.addAttribute("mainPageMessage", "Не корректное, или слишком короткое название композиции");
            return "search";
        }
        trackList.addAll(searchService.findMusicTrack(trackName));
        if (trackList.isEmpty()) {
            model.addAttribute("trackList", trackList);
            model.addAttribute("mainPageMessage", "Не найдено");
            return "search";
        } else {
            String mainPageMessage = "Найденные треки: ";
            model.addAttribute("trackList", trackList);
            model.addAttribute("mainPageMessage", mainPageMessage);
            userDataHolder.getDataMap().put("trackName", trackName);
            userDataHolder.getDataMap().put("trackList", trackList);
            if (trackName == null) {
                return "no parameters";
            }
            return "search";
        }
    }


    private boolean isCorrect(String trackname) {
        boolean isCorrect = trackname.length() >= 3;
        if (trackname.isEmpty() || trackname == null) isCorrect = false;
        return isCorrect;
    }
}
