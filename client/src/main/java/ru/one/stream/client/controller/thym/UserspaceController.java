package ru.one.stream.client.controller.thym;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.one.stream.client.service.UserService;
import ru.one.stream.commons.models.userspace.UserspaceDto;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class UserspaceController {

    private final UserService userService;

    @GetMapping("/")
    private String loadWorkspace(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("currentUser", user.getUsername());
        UserspaceDto userspace = userService.getUserspaceByUsername(user.getUsername());
        model.addAttribute("positions", userspace.getPlaylistDtos().getFirst().getPositions());
        model.addAttribute("playlist", userspace.getPlaylistDtos().getFirst());
        return "userspace";
    }

    @GetMapping("/userspace")
    public String successPage(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("currentUser", user.getUsername());
        UserspaceDto userspace = userService.getUserspaceByUsername(user.getUsername());
        model.addAttribute("positions", userspace.getPlaylistDtos().getFirst().getPositions());
        model.addAttribute("playlist", userspace.getPlaylistDtos().getFirst());
        return "userspace";
    }
}
