package com.baeldung.randominset;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class RandomInSetUtil {

    public static <T> T getByRandomClass(Set<T> set) {
        int randomIndex = new Random().nextInt(set.size());
        int i = 0;
        for (T element : set) {
            if (i == randomIndex) {
                return element;
            }
            i++;
        }
        // Something went wrong while picking a random element
        return null;
    }

    public static <T> T getByThreadLocalRandom(Set<T> set) {
        int randomIndex = ThreadLocalRandom.current().nextInt(set.size());
        int i = 0;
        for (T element : set) {
            if (i == randomIndex) {
                return element;
            }
            i++;
        }
        return null;
    }

    public static void main(String[] args) {
        Set<String> animals = new HashSet<>();
        animals.add("Lion");
        animals.add("Elephant");
        animals.add("Giraffe");

        String randomAnimal = getByThreadLocalRandom(animals);
        System.out.println("Randomly picked animal: " + randomAnimal);
    }

}
