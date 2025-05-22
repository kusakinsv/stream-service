//package ru.one.stream.server.controller.thym;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import ru.one.stream.server.models.ThymData;
//import ru.one.stream.server.service.ClientMusicService;
//import ru.one.stream.server.utils.UserDataHolder;
//
//@Controller
//@RequiredArgsConstructor
//@RequestMapping("/position")
//public class PositionControllerOld {
//
//    private final ClientMusicService clientMusicService;
//    private final UserDataHolder userDataHolder;
//
//    @RequestMapping(value = "/rename/{newTrackName}", method = RequestMethod.POST)
////    @RequestMapping(value = "/rename", method = RequestMethod.POST)
//    public String renamePositionFromPlaylist(Model model,
//                                             @AuthenticationPrincipal User user,
//                                             @ModelAttribute("thymData") ThymData thymData,
//                                             @PathVariable String newTrackName) {
//
//        System.out.println(thymData.getValue1());
////        clientMusicService.renamePositionInPlaylist(
////                user.getUsername(),
////                (Long) userDataHolder.getDataMap().get("playlistId"),
////                (int) userDataHolder.getDataMap().get("trackNumber"),
////                thymData.getValue1());
////        userDataHolder.getDataMap().remove("playlistId");
////        userDataHolder.getDataMap().remove("trackNumber");
//        return "redirect:/userspace";
//    }
//
//
//
//
////    @PostMapping("/addToPlaylist/{trackNumber}/{playlistId}")
////    public void addMusicTrackToPlaylist (Model model, @PathVariable int trackNumber, @PathVariable Long playlistId) {
////        List<MusicTrackDto> trackList = (List<MusicTrackDto>) model.getAttribute("playlist");
////        MusicTrackDto currentDto = trackList.get(trackNumber-1);
////        musicService.addMusicTrackToPlaylist(currentDto, playlistId);
////    }
//
//
//}
