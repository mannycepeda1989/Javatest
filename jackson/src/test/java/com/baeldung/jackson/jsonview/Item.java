package com.baeldung.jackson.jsonview;

import com.fasterxml.jackson.annotation.JsonView;

public class Item {
    @JsonView(Views.Public.class)
    private int id;

    @JsonView(Views.Public.class)
    private String itemName;

    @JsonView(Views.Internal.class)
    private String ownerName;

    public Item() {
        super();
    }

    public Item(final int id, final String itemName, final String ownerName) {
        this.id = id;
        this.itemName = itemName;
        this.ownerName = ownerName;
    }

    public int getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public String getOwnerName() {
        return ownerName;
    }
}
