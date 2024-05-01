package ru.one.stream.client;

import ru.one.stream.client.searchsystems.DuckDuckGoSearch;
import ru.one.stream.client.searchsystems.GoogleSearch;
import ru.one.stream.client.searchsystems.SearchSystem;
import ru.one.stream.client.service.InSiteMp3Sercher;


public class TestSearchApplication {
    private final static String SONG_NAME = "Махамайя";

    public static void main(String[] args) {
        SearchSystem googleSearch = new GoogleSearch();
        SearchSystem duckDuckGoSearch = new DuckDuckGoSearch();
        var list = googleSearch.searchLinks(SONG_NAME);
        var list2 = duckDuckGoSearch.searchLinks(SONG_NAME);

        list.addAll(list2);
        InSiteMp3Sercher inSiteMp3Sercher = new InSiteMp3Sercher();
        var d = inSiteMp3Sercher.searchByLinks(list);
        d.forEach(System.out::println);
        System.out.println("Готово");
    }
}
