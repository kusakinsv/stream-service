package ru.one.stream.server.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MusicTrackDto {

    private Long id;
    private String name;
    private String url;

    public MusicTrackDto(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public MusicTrackDto createDublicate() {
        return new MusicTrackDto(this.name, this.url);
    }

    @Override
    public String toString() {
        return String.format(
                "MusicTrackDto (id=%s, name=%s, url=%s)", this.id, this.name, this.url);
    }
}
