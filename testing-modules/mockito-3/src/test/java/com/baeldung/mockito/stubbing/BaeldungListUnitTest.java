package com.baeldung.mockito.stubbing;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class BaeldungListUnitTest {

    @Test
    void testThenReturn_ForMethodThatReturnValue() {
        BaeldungList myList = mock(BaeldungList.class);

        when(myList.get(anyInt()))
                .thenReturn("answer me");

        assertEquals("answer me", myList.get(1));
    }

    @Test
    void testThenReturn_ThenReturnChaining() {
        BaeldungList myList = mock(BaeldungList.class);
        when(myList.get(anyInt())).thenReturn("answer one").thenReturn("answer two");
        assertEquals("answer one", myList.get(1));
        assertEquals("answer two", myList.get(1));
    }

    @Test
    void testThenReturn_MultipleReturns() {
        BaeldungList myList = mock(BaeldungList.class);
        when(myList.get(anyInt())).thenReturn("answer one", "answer two");
        assertEquals("answer one", myList.get(1));
        assertEquals("answer two", myList.get(1));
    }

    @Test
    void testDoAnswer_ForVoidMethod() {
        BaeldungList myList = mock(BaeldungList.class);

        doAnswer(invocation -> {
            Object index = invocation.getArgument(0);
            Object element = invocation.getArgument(1);

            // verify the invocation is called with correct index and element parameters
            assertEquals(3, index);
            assertEquals("answer", element);
            // return null as this is a void method
            return null;
        }).when(myList)
                .add(any(Integer.class), any(String.class));

        myList.add(3, "answer");
    }

    @Test
    void testDoAnswer_ForMethodThatReturnValue() {
        BaeldungList myList = mock(BaeldungList.class);

        doAnswer(invocation -> {
            Object index = invocation.getArgument(0);
            // verify the invocation is called with index
            assertEquals(1, index);

            // return the value we want
            return "answer me";
        }).when(myList)
                .get(any(Integer.class));

        // validate the stubbed return value
        assertEquals("answer me", myList.get(1));
    }

    @Test
    void testDoAnswer_ConditionalAnswer() {
        BaeldungList myList = mock(BaeldungList.class);

        doAnswer(invocation -> {
            Integer index = invocation.getArgument(0);
            switch (index) {
                case 1: return "answer one";
                case 2: return "answer two";
                default: return "answer " + index;
            }
        }).when(myList)
                .get(anyInt());

        assertEquals("answer one", myList.get(1));
        assertEquals("answer two", myList.get(2));
        assertEquals("answer 3", myList.get(3));
    }
}
