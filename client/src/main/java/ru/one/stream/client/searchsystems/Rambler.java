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
public class Rambler implements SearchSystem {
    private final static String DDG_URL = "https://nova.rambler.ru/search?query=";
    private final static String download = "+скачать";
    private final static String otherSettings = "&p=";
    private final static int PAGES = 5;

    @SneakyThrows
    public Set<String> searchLinks(String name) {
        Set<String> linksList = new HashSet<>();
        for (int i = 0; i < PAGES; i++) {
            String query = DDG_URL + Utils.toConvertedString(name) + download + otherSettings + (i + 1);
            Document document = Jsoup.connect(query)
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.duckduckgo.com")
                    .get();
            Elements elementList = document.select("h2");
            for (Element element : elementList) {
                String link = element.getElementsByAttribute("href").attr("href");
                linksList.add(link);
            }
        }
        return linksList;
    }

    private String toReadableLink(String link) {
        String readableLink = URLDecoder.decode(URLDecoder.decode(link, StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        if (!readableLink.isEmpty()) readableLink = readableLink.split("q=")[1].split("&sa=")[0];
        return readableLink;
    }

}

