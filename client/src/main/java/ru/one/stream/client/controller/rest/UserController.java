package ru.one.stream.client.controller.rest;//package ru.one.stream.desktop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.one.stream.commons.models.UserDetailsDto;
import ru.one.stream.client.service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/getByUsername/{username}")
    private ResponseEntity<?> getUserByUserName(@PathVariable String username) {
        UserDetailsDto userTestDto = userService.getUserDetailsByUsername(username);
        return ResponseEntity.ok(userTestDto);
    }

}
