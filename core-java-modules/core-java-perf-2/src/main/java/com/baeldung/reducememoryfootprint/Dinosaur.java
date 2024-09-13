package com.baeldung.reducememoryfootprint;

public class Dinosaur {

    Integer id;
    Integer age;
    String feedingHabits;
    DinosaurType type;
    String habitat;
    Boolean isExtinct;
    Boolean isCarnivorous;

    public Dinosaur(Integer id, Integer age, String feedingHabits, DinosaurType type, String habitat, Boolean isExtinct, Boolean isCarnivorous) {
        this.id = id;
        this.age = age;
        this.feedingHabits = feedingHabits;
        this.type = type;
        this.habitat = habitat;
        this.isExtinct = isExtinct;
        this.isCarnivorous = isCarnivorous;
    }
}
