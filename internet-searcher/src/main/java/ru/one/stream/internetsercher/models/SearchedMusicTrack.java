package ru.one.stream.internetsercher.models;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchedMusicTrack {

    @NotEmpty
    private String name;
    @NotEmpty
    private String url;

    @Override
    public String toString() {
        return String.format(
                "SearchedMusicTrack (name=%s, url=%s)", this.name, this.url);
    }
}
