package ru.one.stream.desktop;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

@Component
public class Downloader {

    @SneakyThrows
    public void saveFileByUrl(String url, String name, String path) {
        try (InputStream inputStream = new BufferedInputStream(new URL(url).openStream());
             OutputStream os = new FileOutputStream(path + String.format("%s.mp3", name))) {
            os.write(inputStream.readAllBytes());
            os.flush();
        }
    }
}
