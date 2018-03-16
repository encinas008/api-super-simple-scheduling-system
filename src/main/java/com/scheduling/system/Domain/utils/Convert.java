package com.scheduling.system.Domain.utils;

import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Arrays.copyOf;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

/**
 * Created by Rafael Encinas.
 */
public class Convert {
    public static Map<String, List<String>> StringToMap(String queryString) {
        return asList(queryString.split("&"))
                .stream()
                .map(s -> copyOf(s.split("="), 2))
                .collect(groupingBy(s -> s[0], mapping(s -> s[1], toList())));
    }
}
