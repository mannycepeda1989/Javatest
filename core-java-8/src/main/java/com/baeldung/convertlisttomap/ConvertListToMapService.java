package com.baeldung.convertlisttomap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.MapUtils;
import com.google.common.collect.Maps;

public class ConvertListToMapService {

    public Map<Integer, Animal> convertListBeforeJava8(List<Animal> list) {
        Map<Integer, Animal> map = new HashMap<Integer, Animal>();
        for (Animal animal : list) {
            map.put(animal.getId(), animal);
        }
        return map;
    }

    public Map<Integer, Animal> convertListAfterJava8(List<Animal> list) {
        Map<Integer, Animal> map = list.stream().collect(Collectors.toMap(Animal::getId, animal -> animal));
        return map;
    }

    public Map<Integer, Animal> convertListWithGuava(List<Animal> list) {

        Map<Integer, Animal> map = Maps.uniqueIndex(list, Animal::getId);
        return map;
    }

    public Map<Integer, Animal> convertListWithApacheCommons1(List<Animal> list) {

        Map<Integer, Animal> map = new HashMap<Integer, Animal>();

        IterableUtils.forEach(list, animal -> {
            map.put(animal.getId(), animal);
        });

        return map;
    }

    public Map<Integer, Animal> convertListWithApacheCommons2(List<Animal> list) {

        Map<Integer, Animal> map = new HashMap<Integer, Animal>();

        MapUtils.populateMap(map, list, Animal::getId);

        return map;
    }
}
