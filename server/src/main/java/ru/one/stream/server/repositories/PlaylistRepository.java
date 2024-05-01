package ru.one.stream.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.one.stream.server.entities.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}
