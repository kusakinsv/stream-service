package ru.one.stream.internetsercher.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.one.stream.internetsercher.models.MusicTrack;
import ru.one.stream.internetsercher.service.freemusicstores.Mp3PartyNet;
import ru.one.stream.internetsercher.service.freemusicstores.Muzmo;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainSearchServiceImpl {
    private final List<SearchEngine> musicResources = new ArrayList<>() {{
        add(new Mp3PartyNet());
        add(new Muzmo());
    }};

    @SneakyThrows
    public List<MusicTrack> search(String trackName) {
        Set<CompletableFuture<Collection<MusicTrack>>> featureList = new HashSet<>();
        for (SearchEngine system : musicResources) {
            CompletableFuture<Collection<MusicTrack>> feature = CompletableFuture.supplyAsync(()-> system.search(trackName));
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
