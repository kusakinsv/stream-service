package ru.one.stream.internetsercher.service.freemusicstores;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.one.stream.internetsercher.models.MusicTrackResult;
import ru.one.stream.internetsercher.utils.Utils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * su.muzmo.cc/
 * rux.muzmo.cc/
 */
public class Muzmo implements MusicResource {
    public static final String URL = "https://su.muzmo.cc";
    public static final String IP = "http://5.79.82.86";//не работает
    public static final String QUERY_URL = URL + "/search?q=";
    public static final String QUERY_IP = IP + "/search?q=";

    @SneakyThrows
    @Override
    public Set<MusicTrackResult> search(String trackName) {
        String query = QUERY_URL + Utils.toConvertedStringWithPlus(trackName);
        Document document = Jsoup.connect(query)
                .followRedirects(true)
                .maxBodySize(0)
                .timeout(10000)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/142.0.0.0 Safari/537.36")
                .referrer("http://www.google.com")
                .get();

        Set<MusicTrackResult> tracks = new HashSet<>();
        Elements elements = document.body().getElementsByAttribute("data-file");
        for (Element elem : elements.asList()) {
            String partUrl = elem.attr("data-file");
            String url = URL + partUrl;
            String finalName = elem.attr("data-title");
            try {
                String finalUrl = getFinalUrl(url);
                System.out.println("finalUrl: " + finalUrl);
            } catch (Exception e) {
                System.out.println("Пшибка при получении url " + url);
            }
            MusicTrackResult musicTrackDto = new MusicTrackResult(finalName, url);
            tracks.add(musicTrackDto);
        }

        return tracks;
    }

    private String getFinalUrl(String url) throws IOException {
        Document document = Jsoup.connect(url)
                .followRedirects(true)
                .maxBodySize(0)
                .timeout(10000)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/142.0.0.0 Safari/537.36")
                .referrer("http://www.google.com")
                .get();
        return document.data().toString();
    }
}
