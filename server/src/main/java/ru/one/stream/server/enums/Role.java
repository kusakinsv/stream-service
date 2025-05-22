package ru.one.stream.server.enums;

import lombok.Getter;

@Getter
public enum Role {
    USER("USER"),
    ADMIN("ADMIN");

    private final String roleValue;

    Role(String roleValue) {
        this.roleValue = roleValue;
    }


}
