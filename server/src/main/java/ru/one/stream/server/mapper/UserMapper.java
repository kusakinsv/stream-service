package ru.one.stream.server.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.one.stream.commons.models.UserDetailsDto;
import ru.one.stream.commons.models.userspace.UserspaceDto;
import ru.one.stream.server.entities.User;
import ru.one.stream.server.enums.Role;
import ru.one.stream.server.enums.UserStatus;

import java.util.LinkedList;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    PlaylistMapper playlistMapper;

    public UserDetailsDto toUserDetailsDto(User user){
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setId(user.getId());
        userDetailsDto.setUsername(user.getUsername());
        userDetailsDto.setPassword(user.getPassword());
        userDetailsDto.setEmail(user.getEmail());
        userDetailsDto.setRole(user.getRole().name());
        userDetailsDto.setUserStatus(user.getUserStatus().name());
        return userDetailsDto;
    };

    public User toUser(UserDetailsDto userDetailsDto) {
        User user = new User();
        user.setId(userDetailsDto.getId());
        user.setUsername(userDetailsDto.getUsername());
        user.setPassword(userDetailsDto.getPassword());
        user.setEmail(userDetailsDto.getEmail());
        user.setRole(Role.valueOf(userDetailsDto.getRole()));
        user.setUserStatus(UserStatus.valueOf(userDetailsDto.getUserStatus()));
        return user;
    };

    public UserspaceDto toUserspaceDto(User user) {
        UserspaceDto userSpaceDto = new UserspaceDto();
        userSpaceDto.setUsername(user.getUsername());
        userSpaceDto.setPlaylistDtos(new LinkedList<>(user.getPlaylist()
                .stream()
                .map(playlistMapper::toPlaylistDto)
                .collect(Collectors.toList())));
        return userSpaceDto;
    }
}
