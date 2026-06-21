package ru.one.stream.internetsercher.service.freemusicstores;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.one.stream.internetsercher.models.MusicTrackResult;
import ru.one.stream.internetsercher.utils.Utils;

import java.util.HashSet;
import java.util.Set;

/**
 * mp3party.net
 */
public class Mp3PartyNet implements MusicResource {
    public static final String URL = "https://mp3party.net/search?q=";

    @SneakyThrows
    @Override
    public Set<MusicTrackResult> search(String trackName) {
        String query = URL + Utils.toConvertedStringWithSpace(trackName);
        Document document = Jsoup.connect(query)
                .followRedirects(true)
                .maxBodySize(0)
                .timeout(10000)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/142.0.0.0 Safari/537.36")
                .referrer("http://www.google.com")
                .get();

        Set<MusicTrackResult> tracks = new HashSet<>();
        Elements elements = document.body().getElementsByAttribute("data-js-url");
        for (Element elem : elements.asList()) {
            String url = elem.attr("data-js-url");
            String artist = elem.attr("data-js-artist-name");
            String name = elem.attr("data-js-song-title");
            String finalName = name + " - " + artist;
            if (!url.isEmpty()) {
                MusicTrackResult musicTrackDto = new MusicTrackResult(finalName, url);
                tracks.add(musicTrackDto);
            }
        }
        return tracks;
    }
}
