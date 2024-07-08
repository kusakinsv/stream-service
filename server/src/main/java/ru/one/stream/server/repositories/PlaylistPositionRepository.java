package ru.one.stream.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.one.stream.server.entities.PlaylistPosition;

import java.util.List;
import java.util.Optional;

public interface PlaylistPositionRepository extends JpaRepository<PlaylistPosition, Long> {

    @Query(value = "select * from playlist_position pp where playlist_id = (select id from playlist where user_id = (select id from users where username = ?1) and is_main = true)",
            nativeQuery = true)
    List<PlaylistPosition> findAllPlaylistPositionsByUsername(String username);

    @Query(value = "select * from playlist_position\n" +
            "where playlist_id = (\n" +
            "select id from playlist where user_id = (select id from users where username = ?1) and id = ?2\n" +
            ") and position = ?3",
            nativeQuery = true)
    Optional<PlaylistPosition> findPlaylistPositionByUsernameAndPlaylistIdAndPostitionNumber(String username, Long playlistId, int positionId);
}
