package ru.one.stream.client.service;

import ru.one.stream.commons.models.UserDetailsDto;
import ru.one.stream.commons.models.userspace.UserspaceDto;


public interface UserService {

    UserDetailsDto createUser(UserDetailsDto userTestDto);

    UserDetailsDto getUserDetailsByUsername(String username);

    UserspaceDto getUserspaceByUsername(String username);
}
