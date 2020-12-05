package com.baeldung.features;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class NestedClassesUnitTest {

    @Test
    public void giveNestedClass_whenCallingMainClassPrivateMethod_thenNoExceptionIsThrown() {
        MainClass.NestedClass nestedInstance = new MainClass.NestedClass();
        assertThat(nestedInstance.nestedPublicMethod()).isTrue();
    }

    @Test
    public void giveNestedClass_whenCheckingNestmate_thenMainClassIsReturned() {
        assertThat(MainClass.NestedClass.class.isNestmateOf(MainClass.class)).isTrue();
    }

    @Test
    public void giveNestedClass_whenCheckingNestMembers_thenNestMembersAreReturned() {
        Set<String> nestedMembers = Arrays.stream(MainClass.NestedClass.class.getNestMembers())
                .map(Class::getName)
                .collect(Collectors.toSet());
        assertThat(nestedMembers).contains(MainClass.class.getName(), MainClass.NestedClass.class.getName());
    }

}
