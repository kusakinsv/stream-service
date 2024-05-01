package ru.one.stream.desktop.player;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import lombok.Getter;

import java.io.*;
import java.net.URL;
import java.util.UUID;

@Getter
@Deprecated
public class AudioPlayer {

    public static Thread playerThread;
    public static Player player;
    public static boolean isPlayed = false;
    public static String musicTrack;

    public static void playMusic(String trackUrl) {
        try {
            File soundFile = downloadFile(trackUrl);
            FileInputStream fileInputStream = new FileInputStream(soundFile);
            player = new Player(fileInputStream);
            playerThread = new Audio(player);
            musicTrack = trackUrl;
            playerThread.start();
            isPlayed = true;
        } catch (IOException | JavaLayerException exc) {
            exc.printStackTrace();
        }
    }

    public static void stopMusic() {
        player.close();
        playerThread.interrupt();
        isPlayed = false;
    }

    static File downloadFile(String urlAdress) throws IOException {
        File file = File.createTempFile(String.valueOf(UUID.randomUUID()), ".mp3");
        URL url = new URL(urlAdress);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count = 0;
        while ((count = bis.read(buffer, 0, 1024)) != -1) {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
        return file;
    }

    public static String getMusicTrack() {
        return musicTrack;
    }
}
