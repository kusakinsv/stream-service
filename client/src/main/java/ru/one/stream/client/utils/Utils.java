package ru.one.stream.client.utils;

public class Utils {
    public static String toConvertedString(String name) {
        return name.toLowerCase().replaceAll(" ", "+");
    }
}
