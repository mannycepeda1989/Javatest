package com.baeldung.collections;

import com.google.common.collect.ImmutableList;
import org.apache.commons.collections4.ListUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CoreJavaCollectionsUnitTest {

    private static final Logger LOG = LoggerFactory.getLogger(CoreJavaCollectionsUnitTest.class);


    // tests -

    @Test
    public final void givenUsingTheJdk_whenArrayListIsSynchronized_thenCorrect() {
        final List<String> list = new ArrayList<>(Arrays.asList("one", "two", "three"));
        final List<String> synchronizedList = Collections.synchronizedList(list);
        LOG.debug("Synchronized List is: " + synchronizedList);
    }

    @Test(expected = UnsupportedOperationException.class)
    public final void givenUsingTheJdk_whenUnmodifiableListIsCreated_thenNotModifiable() {
        final List<String> list = new ArrayList<>(Arrays.asList("one", "two", "three"));
        final List<String> unmodifiableList = Collections.unmodifiableList(list);
        unmodifiableList.add("four");
    }

    @Test(expected = UnsupportedOperationException.class)
    public final void givenUsingTheJava9_whenUnmodifiableListIsCreated_thenNotModifiable() {
        final List<String> list = new ArrayList<>(Arrays.asList("one", "two", "three"));
        // final List<String> unmodifiableList = // Java 9 List.of code goes here
        // unmodifiableList.add("four");
    }

    @Test(expected = UnsupportedOperationException.class)
    public final void givenUsingGuava_whenUnmodifiableListIsCreated_thenNotModifiable() {
        final List<String> list = new ArrayList<>(Arrays.asList("one", "two", "three"));
        final List<String> unmodifiableList = ImmutableList.copyOf(list);
        unmodifiableList.add("four");
    }

    @Test(expected = UnsupportedOperationException.class)
    public final void givenUsingGuavaBuilder_whenUnmodifiableListIsCreated_thenNoLongerModifiable() {
        final List<String> list = new ArrayList<>(Arrays.asList("one", "two", "three"));
        final ImmutableList<String> unmodifiableList = ImmutableList.<String>builder().addAll(list).build();
        unmodifiableList.add("four");
    }

    @Test(expected = UnsupportedOperationException.class)
    public final void givenUsingCommonsCollections_whenUnmodifiableListIsCreated_thenNotModifiable() {
        final List<String> list = new ArrayList<>(Arrays.asList("one", "two", "three"));
        final List<String> unmodifiableList = ListUtils.unmodifiableList(list);
        unmodifiableList.add("four");
    }

}
