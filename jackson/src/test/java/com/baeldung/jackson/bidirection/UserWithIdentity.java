package com.baeldung.jackson.bidirection;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserWithIdentity {
    private int id;
    public String name;
    private List<ItemWithIdentity> userItems;

    public UserWithIdentity() {
        super();
    }

    public UserWithIdentity(final int id, final String name) {
        this.id = id;
        this.name = name;
        userItems = new ArrayList<>();
    }

    public void addItem(final ItemWithIdentity item) {
        userItems.add(item);
    }
}
