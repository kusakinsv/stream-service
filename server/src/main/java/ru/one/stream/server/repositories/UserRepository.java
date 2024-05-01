package ru.one.stream.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.one.stream.server.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);
}
