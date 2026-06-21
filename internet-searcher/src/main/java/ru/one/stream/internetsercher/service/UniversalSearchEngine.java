package ru.one.stream.internetsercher.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.one.stream.internetsercher.models.MusicTrack;
import ru.one.stream.internetsercher.service.searchsystems.DuckDuckGoSearch;
import ru.one.stream.internetsercher.utils.TrackUtils;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UniversalSearchEngine {
    private final InSiteMp3Sercher inSiteMp3Sercher;
    private final DuckDuckGoSearch duckDuckGoSearch;

    public List<MusicTrack> search(String trackName) {
        Set<String> links = duckDuckGoSearch.searchLinks(trackName);
        System.out.println("by duck: " + links.size());
        return inSiteMp3Sercher.searchByLinks(links).stream()
                .map(link -> new MusicTrack(TrackUtils.constructNameFromLink(link), link))
                .toList();
    }


}

