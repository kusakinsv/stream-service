package ru.one.stream.internetsercher.service.searchsystems;

import java.util.Set;

public interface SearchSystem {

    Set<String> searchLinks(String query);
}
