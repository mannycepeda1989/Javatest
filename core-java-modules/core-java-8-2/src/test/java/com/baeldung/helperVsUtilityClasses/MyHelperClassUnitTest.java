package com.baeldung.helperVsUtilityClasses;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyHelperClassUnitTest {

    @Test
    void whenCreatingHelperObject_thenHelperObjectShouldBeCreated() {
        MyHelperClass myHelperClassObject = new MyHelperClass();
        int[] numberArray = {15, 23, 66, 3, 51, 79};

        assertNotNull(myHelperClassObject);

        assertTrue(myHelperClassObject.isInteger(myHelperClassObject.numberString));
        assertEquals( 79, MyHelperClass.getMaxNumber(numberArray));
        assertEquals(3, MyHelperClass.getMinNumber(numberArray));
    }
}