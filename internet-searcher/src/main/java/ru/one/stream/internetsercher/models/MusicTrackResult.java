package ru.one.stream.internetsercher.models;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MusicTrackResult {

    public MusicTrackResult(String name, String url) {
        this.name = name;
        this.url = url;
        this.hasCorsSecurity = false;
    }

    @NotEmpty
    private String name;
    @NotEmpty
    private String url;

    private boolean hasCorsSecurity;

    @Override
    public String toString() {
        return String.format(
                "MusicTrack (name=%s, url=%s)", this.name, this.url);
    }
}
