package ru.one.stream.server.service;

import ru.one.stream.server.models.UserDetailsDto;
import ru.one.stream.server.models.userspace.UserspaceDto;


public interface UserService {

    UserDetailsDto createUser(UserDetailsDto userTestDto);

    UserDetailsDto getUserDetailsByUsername(String username);

    UserspaceDto getUserspaceByUsername(String username);
}
