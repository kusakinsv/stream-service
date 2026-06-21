package ru.one.stream.server.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MusicTrackDto {

    private Long id;
    private String name;
    private String duration;
    private String url;

    @Override
    public String toString() {
        return String.format(
                "MusicTrackDto (id=%s, name=%s, duration=%s, url=%s)", this.id, this.name, this.duration, this.url);
    }
}
