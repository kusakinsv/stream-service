package ru.one.stream.internetsercher.service.freemusicstores;

import ru.one.stream.internetsercher.models.MusicTrack;
import ru.one.stream.internetsercher.service.SearchEngine;

import java.util.Set;

public interface MusicResource extends SearchEngine {
    Set<MusicTrack> search(String trackName);
}
