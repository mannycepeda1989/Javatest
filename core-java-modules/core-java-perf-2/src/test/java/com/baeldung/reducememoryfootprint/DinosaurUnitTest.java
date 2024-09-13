package com.baeldung.reducememoryfootprint;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.GraphLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

class DinosaurUnitTest {

    Logger LOGGER = LoggerFactory.getLogger(DinosaurUnitTest.class);

    @Test
    void givenAnUnOptimizedObjects_whenEstimatingSizeWithJol_thenLogMemoryFootPrint() {
        DinosaurType dinosaurType = new DinosaurType("Animalia", "Chordata", "Dinosauria", "Saurischia", "Eusaurischia", "Eoraptor", "E. lunensis");
        Dinosaur dinosaur = new Dinosaur(1, 10, "Carnivorous", dinosaurType, "Land", true, true);
        LOGGER.info(String.valueOf(GraphLayout.parseInstance(dinosaur)
            .totalSize()));
    }

    @Test
    void givenAnObjectsUsingPrimitiveType_whenEstimatingSizeWithJol_thenLogMemoryFootPrint() {
        DinosaurType dinosaurType = new DinosaurType("Animalia", "Chordata", "Dinosauria", "Saurischia", "Eusaurischia", "Eoraptor", "E. lunensis");
        DinosaurPrimitive dinosaur = new DinosaurPrimitive(1, (short) 10, "Carnivorous", dinosaurType, "Land", true, true);
        LOGGER.info(String.valueOf(GraphLayout.parseInstance(dinosaur)
            .totalSize()));
    }

    @Test
    void givenCombine_whenEstimatingSizeWithJol_thenLogMemoryFootPrint() {
        DinosaurNew dinosaurNew = new DinosaurNew(1, (short) 10, "Carnivorous", "Land", true, true, "Animalia", "Chordata", "Dinosauria", "Saurischia", "Eusaurischia", "Eoraptor", "E. lunensis");
        LOGGER.info(String.valueOf(GraphLayout.parseInstance(dinosaurNew)
            .totalSize()));
    }

    @Test
    void givenAnArrayOfThreeIntegers_whenEstimatingSizeWithJol_thenCorrect() {
        int[] arr = new int[3];
        assertEquals(32, GraphLayout.parseInstance(arr)
            .totalSize());
    }

    @Test
    void givenAStringOfEightCharacters_whenEstimatingSizeWithJol_thenCorrect() {
        String animal = "Dinosaur";
        assertEquals(48, GraphLayout.parseInstance(animal)
            .totalSize());
    }

    @Test
    void givenArrayListOfDinosaur_whenEstimatingSizeWithJol_thenLogMemoryFootPrint() {
        DinosaurNew dinosaurNew = new DinosaurNew(1, (short) 10, "Carnivorous", "Land", true, true, "Animalia", "Chordata", "Dinosauria", "Saurischia", "Eusaurischia", "Eoraptor", "E. lunensis");
        List<DinosaurNew> dinosaurs = new ArrayList<>();
        dinosaurs.add(dinosaurNew);
        LOGGER.info(String.valueOf(GraphLayout.parseInstance(dinosaurs)
            .totalSize()));
    }

    @Test
    void givenMutableListOfDinosaur_whenEstimatingSizeWithJol_thenLogMemoryFootPrint() {
        DinosaurNew dinosaurNew = new DinosaurNew(1, (short) 10, "Carnivorous", "Land", true, true, "Animalia", "Chordata", "Dinosauria", "Saurischia", "Eusaurischia", "Eoraptor", "E. lunensis");
        MutableList<DinosaurNew> dinosaurs = FastList.newListWith(dinosaurNew);
        dinosaurs.add(dinosaurNew);
        LOGGER.info(String.valueOf(GraphLayout.parseInstance(dinosaurs)
            .totalSize()));
    }

}