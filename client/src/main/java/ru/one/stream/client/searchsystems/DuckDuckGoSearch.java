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

public class DuckDuckGoSearch implements SearchSystem {
    private final static String DDG_URL = "https://duckduckgo.com/?q=";
    private final static String download = "+скачать";
    private final static String otherSettings = "&hps=2";

    @SneakyThrows
    public Set<String> searchLinks(String name) {
        Set<String> linksList = new HashSet<>();
        String query = DDG_URL + Utils.toConvertedString(name) + download + otherSettings;
//        System.out.println("DDG: " + query);
        Document document = Jsoup.connect(query)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.duckduckgo.com")
                .get();
        Elements elementList = document.select("h2");
        for (Element element : elementList) {
            String link = toReadableLink(element.getElementsByAttribute("href").attr("href"));
            linksList.add(link);
        }
        return linksList;
    }

    private String toReadableLink(String link) {
        String readableLink = URLDecoder.decode(URLDecoder.decode(link, StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        readableLink = readableLink.split("uddg=")[1].split("&rut=")[0];
        return readableLink;
    }

}
