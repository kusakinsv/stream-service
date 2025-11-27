package ru.one.stream.internetsercher.models;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class MusicTrack {

    @NotEmpty
    private String name;
    @NotEmpty
    private String url;

    @Override
    public String toString() {
        return String.format(
                "MusicTrack (name=%s, url=%s)", this.name, this.url);
    }
}
