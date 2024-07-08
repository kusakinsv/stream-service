package ru.one.stream.desktop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.one.stream.desktop.service.BrowserLauncher;


@SpringBootApplication
public class DesckopApp {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DesckopApp.class, args);
        BrowserLauncher launcher = context.getBean(BrowserLauncher.class);

        launcher.start();
    }
}
