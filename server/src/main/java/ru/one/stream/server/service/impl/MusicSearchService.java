package ru.one.stream.server.service.impl;

import ru.one.stream.server.dto.MusicTrackDto;
import ru.one.stream.server.service.SearchService;

import java.util.List;

public class MusicSearchService implements SearchService {

    @Override
    public List<MusicTrackDto> searchMusicTrack() {
        return List.of();
    }
}
