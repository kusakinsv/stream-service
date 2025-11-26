package ru.one.stream.internetsercher.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ru.one.stream.internetsercher.models.MusicTrack;
import ru.one.stream.internetsercher.service.searchsystems.DuckDuckGoSearch;
import ru.one.stream.internetsercher.service.searchsystems.Rambler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

@Component
public class MainSearchService {


    private InSiteMp3Sercher inSiteMp3Sercher = new InSiteMp3Sercher();
    private final List<SearchSystem> systems = new ArrayList<>() {{
        add(new DuckDuckGoSearch());
        add(new Rambler());
    }};

    @SneakyThrows
    public List<MusicTrack> search(String trackName) {
        ConcurrentLinkedQueue<String> results = new ConcurrentLinkedQueue<>();
        Set<Thread> threads = new HashSet<>();
        for (SearchSystem system : systems) {
            Thread tr = new Thread(new Runnable() {
                @Override
                public void run() {
                    results.addAll(system.searchLinks(trackName));
                }
            });
            threads.add(tr);
            tr.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        return inSiteMp3Sercher.searchByLinks(results)
                .stream()
                .map(url -> new MusicTrack(trackName, url)).collect(Collectors.toList());
    }

}
