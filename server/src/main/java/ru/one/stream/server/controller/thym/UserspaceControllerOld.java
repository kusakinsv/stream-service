//package ru.one.stream.server.controller.thym;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import ru.one.stream.server.service.UserService;
//import ru.one.stream.server.models.userspace.UserspaceDto;
//
//@Controller
//@RequiredArgsConstructor
//@RequestMapping
//public class UserspaceControllerOld {
//
//    private final UserService userService;
//
//    @GetMapping("/")
//    private String loadWorkspace(Model model, @AuthenticationPrincipal User user) {
//        model.addAttribute("currentUser", user.getUsername());
//        UserspaceDto userspace = userService.getUserspaceByUsername(user.getUsername());
//        model.addAttribute("positions", userspace.getPlaylistDtos().getFirst().getPositions());
//        model.addAttribute("playlist", userspace.getPlaylistDtos().getFirst());
//        return "userspace";
//    }
//
//    @GetMapping("/userspace")
//    public String successPage(Model model, @AuthenticationPrincipal User user) {
//        model.addAttribute("currentUser", user.getUsername());
//        UserspaceDto userspace = userService.getUserspaceByUsername(user.getUsername());
//        model.addAttribute("positions", userspace.getPlaylistDtos().getFirst().getPositions());
//        model.addAttribute("playlist", userspace.getPlaylistDtos().getFirst());
//        return "userspace";
//    }
//}
