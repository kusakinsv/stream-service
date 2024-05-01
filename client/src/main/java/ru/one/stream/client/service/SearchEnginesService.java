package ru.one.stream.client.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ru.one.stream.commons.models.MusicTrackDto;
import ru.one.stream.client.mapper.MusicTrackMapper;
import ru.one.stream.client.searchsystems.DuckDuckGoSearch;
import ru.one.stream.client.searchsystems.GoogleSearch;
import ru.one.stream.client.searchsystems.Rambler;
import ru.one.stream.client.searchsystems.SearchSystem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

@Component
public class SearchEnginesService implements SearchService {

    private InSiteMp3Sercher inSiteMp3Sercher = new InSiteMp3Sercher();
    private MusicTrackMapper musicTrackMapper = new MusicTrackMapper();
    private final List<SearchSystem> systems = new ArrayList<>() {{
        add(new GoogleSearch());
        add(new DuckDuckGoSearch());
        add(new Rambler());
    }};

    @SneakyThrows
    @Override
    public List<MusicTrackDto> findMusicTrack(String trackName) {
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
        return inSiteMp3Sercher.searchByLinks(results).stream()
                .map(musicTrackMapper::toMusicTrackDto).collect(Collectors.toList());
    }

}
