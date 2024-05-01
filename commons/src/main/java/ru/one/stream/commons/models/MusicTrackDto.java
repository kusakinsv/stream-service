package ru.one.stream.commons.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class MusicTrackDto {

    private Long id;
    private String name;
    private String url;

    public MusicTrackDto createDublicate() {
        return new MusicTrackDto(this.name, this.url);
    }

    public MusicTrackDto(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @Override
    public String toString() {
        return String.format(
                "MusicTrackDto (id=%s, name=%s, url=%s)", this.id, this.name, this.url);
    }
}
