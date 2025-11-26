package ru.one.stream.internetsercher;

import ru.one.stream.internetsercher.service.freemusicstores.MusicResource;
import ru.one.stream.internetsercher.service.freemusicstores.Muzmo;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        MusicResource search = new Muzmo();
        search.search("лилая наша любовь");
    }
}
