package ru.one.stream.internetsercher.utils;

public class Utils {
    public static String toConvertedString(String name) {
        return name.toLowerCase().replaceAll(" ", "+");
    }
}
