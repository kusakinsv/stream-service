package ru.one.stream.internetsercher.service;

import ru.one.stream.internetsercher.models.MusicTrack;

import java.util.Collection;

public interface SearchEngine {

    Collection<MusicTrack> search(String query);
}
