package com.baeldung.reducememoryfootprint;

public class DinosaurPrimitive {

    int id;
    short age;
    String feedingHabits;
    DinosaurType type;
    String habitat;
    boolean isExtinct;
    boolean isCarnivorous;

    public DinosaurPrimitive(int id, short age, String feedingHabits, DinosaurType type, String habitat, boolean isExtinct, boolean isCarnivorous) {
        this.id = id;
        this.age = age;
        this.feedingHabits = feedingHabits;
        this.type = type;
        this.habitat = habitat;
        this.isExtinct = isExtinct;
        this.isCarnivorous = isCarnivorous;
    }
}
