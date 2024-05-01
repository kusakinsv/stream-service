package ru.one.stream.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.one.stream.commons.models.UserDetailsDto;
import ru.one.stream.commons.models.userspace.UserspaceDto;
import ru.one.stream.server.mapper.UserMapper;
import ru.one.stream.server.repositories.UserRepository;

@Component
@RequiredArgsConstructor
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDetailsDto getUserDetailsByUsername(String username) {
        var optionalUser = userRepository.findUserByUsername(username);
        if (optionalUser.isPresent()) return userMapper.toUserDetailsDto(optionalUser.get());
        else throw new RuntimeException("User not found");
    }

    public UserspaceDto getUserspaceByUsername(String username) {
        var optionalUser = userRepository.findUserByUsername(username);
        if (optionalUser.isPresent()) return userMapper.toUserspaceDto(optionalUser.get());
        else throw new RuntimeException("User not found");
    }

//    public void createUser(User user) {
//        if (userRepository.findUserByUsername(userDto.getUsername()).isEmpty()) {
//            try {
//                User user = new User();
//                user.setUsername(userDto.getUsername());
//                user.setPassword(userDto.getPassword());
//                userRepository.save(user);
//            } catch (Exception e) {
//                throw new CreationException("Ошибка при создании пользователя");
//            }
//        } else {
//            throw new RepeatException("Пользователь с таким именем уже есть");
//        }
//    }
}
