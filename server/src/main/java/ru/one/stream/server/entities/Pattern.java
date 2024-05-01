package ru.one.stream.server.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;
import org.hibernate.annotations.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;


import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Data
@Entity
@Table(name = "pattern", uniqueConstraints = {
        @UniqueConstraint(columnNames = "title")})
@Indexed
public class Pattern {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pattern_id_seq")
    @SequenceGenerator(name = "pattern_id_seq",  sequenceName = "pattern_id_seq", initialValue = 50)
    private Long id;

    @Field
    private String title;

    @ManyToMany(mappedBy = "patterns", fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @Cascade({org.hibernate.annotations.CascadeType.REMOVE, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<MusicTrack> tracks = new HashSet<>();

    public Pattern(String title) {
        this.title = title.toLowerCase();
    }

    public void setTitle(String title) {
        this.title = title.toLowerCase();
    }

    public Pattern(Long id, String title, Set<MusicTrack> tracks) {
        this.id = id;
        this.title = title.toLowerCase();
        this.tracks = tracks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pattern pattern = (Pattern) o;
        return title.equals(pattern.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

    public Pattern() {
    }
}
