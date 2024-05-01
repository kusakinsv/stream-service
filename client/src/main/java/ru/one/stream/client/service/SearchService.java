package ru.one.stream.client.service;

import ru.one.stream.commons.models.MusicTrackDto;

import java.util.List;

public interface SearchService {

    List<MusicTrackDto> findMusicTrack(String trackName);
}
