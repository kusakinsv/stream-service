package ru.one.stream.server;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.one.stream.server.entities.User;
import ru.one.stream.server.repositories.PlaylistRepository;
import ru.one.stream.server.repositories.UserRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PlaylistRepository playlistRepository;

    @Test
    @Order(1)
    public void testCreateUser(){
        User user = new User();
        System.out.println(user.toString());
    }

    @Test
    @Order(2)
    public void getTest(){
        User user = userRepository.findUserByUsername("admin").get();
        System.out.println(user.toString());
    }

}
