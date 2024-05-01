package ru.one.stream.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.one.stream.server.entities.MusicTrack;

import java.util.Optional;

public interface MusicTrackRepository extends JpaRepository<MusicTrack, Long> {

    Optional<MusicTrack> findByUrl(String url);
}
