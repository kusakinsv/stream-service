package ru.one.stream.server.entities;

import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.one.stream.server.enums.Role;
import ru.one.stream.server.enums.UserStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="users_id_seq")
    @SequenceGenerator(name = "users_id_seq",  sequenceName = "users_id_seq", initialValue = 50)
    private Long id;

    @NotNull
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    //Добавить проверку наличия @ и .
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Playlist> playlist = new ArrayList<>(){{
        add(new Playlist("My music", true));
    }};

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "user_status")
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    public User() {
    }


}
