package ru.one.stream.internetsercher.service;


import ru.one.stream.internetsercher.models.MusicTrackDto;

import java.util.List;

public interface SearchService {

    List<MusicTrackDto> findMusicTrack(String trackName);
}
