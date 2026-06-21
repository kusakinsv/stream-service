package ru.one.stream.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.one.stream.server.dto.UserDetailsDto;
import ru.one.stream.server.mapper.UserMapper;
import ru.one.stream.server.repositories.UserRepository;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDetailsDto createNewUser(UserDetailsDto userTestDto) {
        return null;
    }

    @Override
    public UserDetailsDto getUserByUsername(String userTestDto) {
        return null;
    }

    @Override
    public UserDetailsDto getUserById(String userTestDto) {
        return null;
    }

    @Override
    public UserDetailsDto createNewUser(String username) {
        return null;
    }
}
