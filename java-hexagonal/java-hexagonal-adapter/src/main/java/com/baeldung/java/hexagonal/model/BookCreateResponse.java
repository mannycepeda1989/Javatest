package com.baeldung.java.hexagonal.model;

public class BookCreateResponse {

    private String id;

    public String getId() {
        return id;
    }

    private String name;

    public String getName() {
        return name;
    }

    public BookCreateResponse setName(String name) {
        this.name = name;
        return this;
    }

    public BookCreateResponse setId(String id) {
        this.id = id;
        return this;
    }
}
