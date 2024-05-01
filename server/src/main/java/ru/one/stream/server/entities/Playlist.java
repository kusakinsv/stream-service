package ru.one.stream.server.entities;

import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "playlist")
public class Playlist {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="playlist_id_seq")
    @SequenceGenerator(name = "playlist_id_seq",  sequenceName = "playlist_id_seq", initialValue = 50)
    private Long id;

    private String title;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "playlist_id", referencedColumnName = "id")
    private Set<PlaylistPosition> playlistPositions = new LinkedHashSet<>();

    private boolean isMain;

    public Playlist(String title, boolean isMain) {
        this.title = title;
        this.isMain = isMain;
    }

    public Playlist(String title) {
        this.title = title;
        this.isMain = false;
    }

    public Playlist() {
    }
}
