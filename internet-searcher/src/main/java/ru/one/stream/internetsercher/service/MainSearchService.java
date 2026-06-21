package ru.one.stream.internetsercher.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.one.stream.internetsercher.models.MusicTrack;
import ru.one.stream.internetsercher.models.ValidationResult;
import ru.one.stream.internetsercher.service.freemusicstores.Mp3PartyNet;
import ru.one.stream.internetsercher.service.freemusicstores.Muzmo;
import ru.one.stream.internetsercher.utils.VirtualExecutorService;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainSearchService {
    public static final String proxyServerPart = "/api/v1/proxy?url=";

    private final VirtualExecutorService virtualExecutorService;
    private final ValidateAudioService validateAudioService;
    private final List<SearchEngine> musicResources = new ArrayList<>() {{
        add(new Muzmo());
        add(new Mp3PartyNet());
    }};

    @Value("${stream-service.proxy-server.url}")
    private String proxyServerUrl;

    @SneakyThrows
    public List<MusicTrack> search(String trackName) {
        Set<CompletableFuture<Collection<MusicTrack>>> featureList = new HashSet<>();
        for (SearchEngine system : musicResources) {
            CompletableFuture<Collection<MusicTrack>> feature = CompletableFuture.supplyAsync(() -> system.search(trackName), virtualExecutorService);
            featureList.add(feature);
        }

        List<MusicTrack> musicTracks = CompletableFuture.allOf(featureList.toArray(CompletableFuture[]::new))
                .thenApply(f -> featureList.stream()
                        .map(CompletableFuture::join)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList())
                ).get();

        featureList.clear();

        Set<CompletableFuture<MusicTrack>> validFeatureList = new HashSet<>();
        for (MusicTrack musicTrack : musicTracks) {
            CompletableFuture<MusicTrack> feature = CompletableFuture.supplyAsync(() -> {
                ValidationResult validationResult = validateAudioService.validateUrl(musicTrack.getUrl());
                if (validationResult.isValid()) {
                    if (validationResult.isNeedProxy()) {
                        musicTrack.setUrl(createProxyUrl(validationResult.getFinalUrl()));
                    } else {
                        musicTrack.setUrl(validationResult.getFinalUrl());
                    }
                    return musicTrack;
                } else {
                    throw new RuntimeException("track is invalid");
                }
            }, virtualExecutorService).exceptionally(throwable -> {
                return null;
            });
            validFeatureList.add(feature);
        }

        var result = CompletableFuture.allOf(featureList.toArray(CompletableFuture[]::new))
                .thenApply(f -> validFeatureList.stream().map(CompletableFuture::join)
                        .toList()).get().stream().filter(Objects::nonNull).toList();
        return result;
    }

    private String createProxyUrl(String url) {
        return proxyServerUrl + proxyServerPart + url;
    }

}
