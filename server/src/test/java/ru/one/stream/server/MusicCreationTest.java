package ru.one.stream.server;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.one.stream.server.repositories.MusicTrackRepository;
import ru.one.stream.server.service.ServerMusicService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MusicCreationTest {

    @Autowired
    MusicTrackRepository musicTrackRepository;

    @Autowired
    ServerMusicService serverMusicService;

    @Test
    public void testDelete(){
        serverMusicService.deleteMusicTrackFromLibrary("admin", 12);
    }



}
