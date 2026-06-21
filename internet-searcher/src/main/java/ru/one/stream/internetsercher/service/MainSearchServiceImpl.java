package ru.one.stream.internetsercher.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.one.stream.internetsercher.models.MusicTrackResult;
import ru.one.stream.internetsercher.service.freemusicstores.Mp3PartyNet;
import ru.one.stream.internetsercher.service.freemusicstores.Muzmo;
import ru.one.stream.internetsercher.service.searchsystems.DuckDuckGoSearch;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainSearchServiceImpl {
    private DuckDuckGoSearch duckGoSearch = new DuckDuckGoSearch();
    private final List<SearchEngine> musicResources = new ArrayList<>() {{
        add(new Mp3PartyNet());
        add(new Muzmo());
    }};

    @SneakyThrows
    public List<MusicTrackResult> search(String trackName) {
        Set<CompletableFuture<Collection<MusicTrackResult>>> featureList = new HashSet<>();
        for (SearchEngine system : musicResources) {
            CompletableFuture<Collection<MusicTrackResult>> feature = CompletableFuture.supplyAsync(()-> system.search(trackName));
            featureList.add(feature);
        }

        return CompletableFuture.allOf(featureList.toArray(CompletableFuture[]::new))
                .thenApply(f-> featureList.stream()
                        .map(CompletableFuture::join)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList())
                ).get();
    }

}
