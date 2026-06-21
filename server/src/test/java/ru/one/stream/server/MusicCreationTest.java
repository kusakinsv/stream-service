package ru.one.stream.server;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.one.stream.server.repositories.MusicTrackRepository;
import ru.one.stream.server.service.MusicTrackService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MusicCreationTest {

    @Autowired
    MusicTrackRepository musicTrackRepository;

    @Autowired
    MusicTrackService serverMusicService;

//    @Test
    public void testDelete(){
           serverMusicService.deleteMusicTrackFromLibrary("admin", 12);
    }

    @Test
    @Order(1)
    public void testRename(){
        serverMusicService.renameMusicTrack("admin", 1L, 7, "Пикник - Египтянин");
    }



}
