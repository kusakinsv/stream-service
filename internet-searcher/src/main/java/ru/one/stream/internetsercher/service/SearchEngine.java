package ru.one.stream.internetsercher.service;

import ru.one.stream.internetsercher.models.MusicTrackResult;

import java.util.Collection;

public interface SearchEngine {

    Collection<MusicTrackResult> search(String query);
}
