package com.baeldung.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class BeanA {

    @Autowired
    private BeanB b;

    public BeanA() {
        super();
    }

}
