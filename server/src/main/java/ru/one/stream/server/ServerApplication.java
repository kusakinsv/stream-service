package ru.one.stream.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ServerApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ServerApplication.class, args);

//        MusicService musicService = context.getBean(MusicService.class);
//        var result =  musicService.findMusicTrackByPatternTitle("Группа крови на рукаве");
//        System.out.println();
//        result.forEach(System.out::println);
//        System.out.println(result.size());
//        System.exit(0);
    }
}
