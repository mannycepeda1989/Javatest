package com.baeldung.threadsafety.tests;

import com.baeldung.threadsafety.mathutils.MathUtils;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MathUtilsTest {
    
    @Test
    public void whenCalledFactorialMethod_thenCorrect() {
        assertThat(MathUtils.factorial(2)).isEqualTo(2);
    }
}
