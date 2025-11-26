package ru.one.stream.internetsercher.service;

import ru.one.stream.internetsercher.models.MusicTrack;

import java.util.Set;

public interface SearchSystem extends SearchEngine {

    Set<String> searchLinks(String query);

    Set<MusicTrack> search(String query);
}
