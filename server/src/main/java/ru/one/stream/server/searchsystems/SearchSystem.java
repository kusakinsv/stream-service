package ru.one.stream.server.searchsystems;

import java.util.Set;

public interface SearchSystem {

    Set<String> searchLinks(String query);
}
