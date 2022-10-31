package com.baeldung.compare;

import java.util.Comparator;

import com.google.common.collect.ComparisonChain;

public class ComparisonChainExample implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return ComparisonChain.start()
          .compare(o1.getName(), o2.getName())
          .compare(o1.getAge(), o2.getAge()).result();
    }
}
