package com.baeldung.resourcebundle;

import java.util.ListResourceBundle;

public class ExampleResource_pl extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"keyA", "pl_valueA"},
                {"keyB", "pl_valueB"},
        };
    }

}
