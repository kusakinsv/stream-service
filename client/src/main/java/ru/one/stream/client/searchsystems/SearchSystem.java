package ru.one.stream.client.searchsystems;

import java.util.Set;

public interface SearchSystem {

    Set<String> searchLinks(String query);
}
