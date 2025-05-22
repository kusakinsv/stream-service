package ru.one.stream.server.utils;

import lombok.Getter;

import java.util.HashMap;

@Getter
public class UserDataHolder {

    private final HashMap<String, Object> dataMap = new HashMap<>();

}
