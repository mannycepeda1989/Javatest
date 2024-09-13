package com.baeldung.reducememoryfootprint;

public class DinosaurNew {

    int id;
    short age;
    String feedingHabits;
    String habitat;
    boolean isExtinct;
    boolean isCarnivorous;
    String kingdom;
    String phylum;
    String clazz;
    String order;
    String family;
    String genus;
    String species;

    public DinosaurNew(int id, short age, String feedingHabits, String habitat, boolean isExtinct, boolean isCarnivorous, String kingdom, String phylum, String clazz, String order, String family, String genus, String species) {
        this.id = id;
        this.age = age;
        this.feedingHabits = feedingHabits;
        this.habitat = habitat;
        this.isExtinct = isExtinct;
        this.isCarnivorous = isCarnivorous;
        this.kingdom = kingdom;
        this.phylum = phylum;
        this.clazz = clazz;
        this.order = order;
        this.family = family;
        this.genus = genus;
        this.species = species;
    }
}
