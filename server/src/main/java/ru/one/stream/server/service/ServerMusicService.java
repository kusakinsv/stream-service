package ru.one.stream.server.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.one.stream.commons.models.MusicTrackDto;
import ru.one.stream.server.entities.MusicTrack;
import ru.one.stream.server.entities.Pattern;
import ru.one.stream.server.entities.Playlist;
import ru.one.stream.server.entities.PlaylistPosition;
import ru.one.stream.server.repositories.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ServerMusicService {

    @Autowired
    MusicTrackRepository musicTrackRepository;

    @Autowired
    PatternRepository patternRepository;

    @Autowired
    MusicTrackFullTextRepository fullTextSearchRepository;

    @Autowired
    PlaylistRepository playlistRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PlaylistPositionRepository playlistPositionRepository;

    //todo допустим, чтобы искал не по всем паттернам а только у этого трека
    public MusicTrack addMusicTrack(String url, String patternTitle) {
        Optional<MusicTrack> optionalMusicTrack = musicTrackRepository.findByUrl(url);
        Optional<Pattern> foundedPattern = patternRepository.findByTitle(patternTitle.toLowerCase());
        if (optionalMusicTrack.isPresent()) {
            MusicTrack foundedTrack = optionalMusicTrack.get();
            if (foundedPattern.isPresent()) {
                foundedTrack.getPatterns().add(foundedPattern.get());
            } else {
                foundedTrack.getPatterns().add(new Pattern(patternTitle));
            }
            return musicTrackRepository.save(foundedTrack);
        } else {
            MusicTrack newTrack = new MusicTrack();
            newTrack.setTrackName(patternTitle);
            newTrack.setUrl(url);
            newTrack.setCreationDate(LocalDateTime.now());
            if (foundedPattern.isPresent()) {
                newTrack.getPatterns().add(foundedPattern.get());
            } else {
                newTrack.getPatterns().add(new Pattern(patternTitle));
            }
            return musicTrackRepository.save(newTrack);
        }
    }

    public MusicTrackDto addMusicTrackToPlayList(MusicTrackDto musicTrackDto, Long playListId) {
        MusicTrack musicTrack = this.addMusicTrack(musicTrackDto.getUrl(), musicTrackDto.getName());
        Playlist playlist = playlistRepository.getById(playListId);
        Set<PlaylistPosition> positions = playlist.getPlaylistPositions();
        int lastPosition = positions.size();
        PlaylistPosition newPlaylistPosition = new PlaylistPosition();
        newPlaylistPosition.setPosition(lastPosition + 1);
        newPlaylistPosition.setTrack(musicTrack);
        newPlaylistPosition.setTitle(musicTrackDto.getName());
        positions.add(newPlaylistPosition);
        playlistRepository.save(playlist);
        musicTrackDto.setId(musicTrack.getId());
        return musicTrackDto;
    }

    public MusicTrackDto addMusicTrackToLibrary(MusicTrackDto musicTrackDto, String username) {
        MusicTrack musicTrack = this.addMusicTrack(musicTrackDto.getUrl(), musicTrackDto.getName());
        Playlist playlist = userRepository.findUserByUsername(username).get().getPlaylist().stream().findFirst().get();
        var positions = playlist.getPlaylistPositions();
        int lastPosition = positions.size();
        PlaylistPosition newPlaylistPosition = new PlaylistPosition();
        newPlaylistPosition.setPosition(lastPosition + 1);
        newPlaylistPosition.setTrack(musicTrack);
        newPlaylistPosition.setTitle(musicTrackDto.getName());
        positions.add(newPlaylistPosition);
        playlistRepository.save(playlist);
        musicTrackDto.setId(musicTrack.getId());
        return musicTrackDto;
    }

    public void deleteMusicTrackFromLibrary(String username, int positionNumber) {
        List<PlaylistPosition> playlistPositions = playlistPositionRepository
                .findAllPlaylistPositionsByUsername(username);
        var positionForDelete = playlistPositions.stream().filter(pos -> pos.getPosition() == positionNumber).findFirst().get();
        playlistPositionRepository.delete(positionForDelete);
        playlistPositions.remove(positionForDelete);
        reOrderPositions(playlistPositions);
    }

    private List<PlaylistPosition> reOrderPositions(List<PlaylistPosition> positions) {
        for (int i = 0; i < positions.size(); i++) {
            positions.get(i).setPosition(i+1);
        }
        return playlistPositionRepository.saveAll(positions);
    }


    /**
     * Полнотекстовый поиск
     */
    public List<MusicTrack> findMusicTrackByPatternTitle(String name) {
        return fullTextSearchRepository.findMusicTrackByPatternTitle(name);
    }
}
