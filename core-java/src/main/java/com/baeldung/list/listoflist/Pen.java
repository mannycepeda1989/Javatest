package com.baeldung.list.listoflist;

class Pen implements Stationery {

    private String name;

    public Pen(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}