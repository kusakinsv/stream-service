package ru.one.stream.server.service;

import ru.one.stream.server.models.MusicTrackDto;

import java.util.List;

public interface SearchService {

    List<MusicTrackDto> findMusicTrack(String trackName);
}
