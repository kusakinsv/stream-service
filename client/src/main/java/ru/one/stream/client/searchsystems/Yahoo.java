package ru.one.stream.client.searchsystems;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.one.stream.client.utils.Utils;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

//Пока не работает
public class Yahoo implements SearchSystem {
    public static final String YAHOO_SEARCH_URL = "https://www.google.com/search?q=";
    private final static String download = "+скачать";

    @SneakyThrows
    public Set<String> searchLinks(String name) {
        Set<String> linksList = new HashSet<>();
        String query = YAHOO_SEARCH_URL + Utils.toConvertedString(name) + download;
        System.out.println("Google:" + query);
        Document document = Jsoup.connect(query)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();

        Elements results = document.select("h3");
        for (Element element : results) {
            String link = element.parent().parent().parent().attr("href");
            link = toReadableLink(link);
            if (!link.isEmpty()) linksList.add(link);
        }
        return linksList;
    }

    private String toReadableLink(String link) {
        String readableLink = URLDecoder.decode(URLDecoder.decode(link, StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        if (!readableLink.isEmpty()) readableLink = readableLink.split("q=")[1].split("&sa=")[0];
        return readableLink;
    }


}