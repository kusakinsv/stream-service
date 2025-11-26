package ru.one.stream.internetsercher.models;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MusicTrack {

    @NotEmpty
    private String name;
    @NotEmpty
    private String url;
    private String duration;

    public MusicTrack(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @Override
    public String toString() {
        return String.format(
                "MusicTrackDto (name=%s, url=%s)", this.name, this.url);
    }
}
