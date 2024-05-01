package ru.one.stream.commons.models;

import lombok.*;

@Getter
@Setter
public class UserDetailsDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;
    private String userStatus;

    public UserDetailsDto() {
    }

    public UserDetailsDto(Long id, String username, String email, String password, String role, String userStatus) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.userStatus = userStatus;
    }
}


