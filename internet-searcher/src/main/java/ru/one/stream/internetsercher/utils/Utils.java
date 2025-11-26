package ru.one.stream.internetsercher.utils;

public class Utils {
    public static String toConvertedStringWithPlus(String name) {
        return name.toLowerCase().replaceAll(" ", "+");
    }

    public static String toConvertedStringWithSpace(String name) {
        return name.toLowerCase().replaceAll(" ", "%20");
    }
}
