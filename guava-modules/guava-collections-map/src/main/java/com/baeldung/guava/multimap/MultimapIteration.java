package com.baeldung.guava.multimap;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class MultimapIteration {

    static void iterateUsingEntries(Multimap<String, String> multiMap) {
        multiMap.entries()
          .forEach(entry -> System.out.println(entry.getKey() + " => " + entry.getValue()));
    }

    static void iterateUsingAsMap(Multimap<String, String> multiMap) {
        multiMap.asMap()
          .entrySet()
          .forEach(entry -> System.out.println(entry.getKey() + " => " + entry.getValue()));
    }

    static void iterateUsingKeySet(Multimap<String, String> multiMap) {
        multiMap.keySet()
          .forEach(System.out::println);
    }

    static void iterateUsingKeys(Multimap<String, String> multiMap) {
        multiMap.keys()
          .forEach(System.out::println);
    }

    static void iterateUsingValues(Multimap<String, String> multiMap) {
        multiMap.values()
          .forEach(System.out::println);
    }

    public static void main(String[] args) {

        Multimap<String, String> multiMap = ArrayListMultimap.create();
        multiMap.put("key1", "value1");
        multiMap.put("key2", "value2");
        multiMap.put("key3", "value3");

        iterateUsingEntries(multiMap);
        iterateUsingAsMap(multiMap);
        iterateUsingKeys(multiMap);
        iterateUsingKeySet(multiMap);
        iterateUsingValues(multiMap);

    }

}