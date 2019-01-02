package com.baeldung;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 测试：字符串-》字符数组
 * @see java.lang.String#toCharArray()
 */
public class StringToCharArrayUnitTest {

    @Test
    public void givenString_whenCallingStringToCharArray_shouldConvertToCharArray() {
        String givenString = "characters";

        char[] result = givenString.toCharArray();

        char[] expectedCharArray = { 'c', 'h', 'a', 'r', 'a', 'c', 't', 'e', 'r', 's' };

        assertArrayEquals(expectedCharArray, result);
    }

}
