package ru.one.stream.client.mapper;

import ru.one.stream.commons.models.MusicTrackDto;
import ru.one.stream.client.utils.TrackUtils;

public class MusicTrackMapper {

    public MusicTrackDto toMusicTrackDto(String url) {
        MusicTrackDto musicTrackDto = new MusicTrackDto();
        musicTrackDto.setName(TrackUtils.constructNameFromLink(url));
        musicTrackDto.setUrl(url);
        return musicTrackDto;
    }

    public MusicTrackDto createMusicTrackDto(String name, String url) {
        MusicTrackDto musicTrackDto = new MusicTrackDto();
        musicTrackDto.setName(name);
        musicTrackDto.setUrl(url);
        return musicTrackDto;
    }
}
