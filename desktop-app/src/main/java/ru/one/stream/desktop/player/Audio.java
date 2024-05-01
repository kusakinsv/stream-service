package ru.one.stream.desktop.player;

import javazoom.jl.player.Player;
import lombok.SneakyThrows;

@Deprecated
public class Audio extends Thread {
    Player player;

    @SneakyThrows
    @Override
    public void run() {
        player.play();
        player.close(); //Закрываем
    }

    public Audio(Player player) {
        this.player = player;
    }
}
