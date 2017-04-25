package com.baeldung.longadder;


import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;

import static junit.framework.TestCase.assertEquals;

public class LongAdderTest {

    @Test
    public void givenMultipleThread_whenTheyWriteToSharedLongAdder_thenShouldCalculateSumForThem() throws InterruptedException {
        //given
        LongAdder counter = new LongAdder();
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        int numberOfThreads = 4;
        int numberOfIncrements = 100;

        //when
        Runnable incrementAction =
                () -> IntStream.range(0, numberOfIncrements).forEach((i) -> counter.increment());
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.execute(incrementAction);
        }

        //then
        executorService.awaitTermination(500, TimeUnit.MILLISECONDS);
        executorService.shutdown();
        assertEquals(counter.sum(), numberOfIncrements * numberOfThreads);
        assertEquals(counter.sum(), numberOfIncrements * numberOfThreads);
    }

    @Test
    public void givenMultipleThread_whenTheyWriteToSharedLongAdder_thenShouldCalculateSumForThemAndResetAdderAfterward() throws InterruptedException {
        //given
        LongAdder counter = new LongAdder();
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        int numberOfThreads = 4;
        int numberOfIncrements = 100;

        //when
        Runnable incrementAction =
                () -> IntStream.range(0, numberOfIncrements).forEach((i) -> counter.increment());
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.execute(incrementAction);
        }

        //then
        executorService.awaitTermination(500, TimeUnit.MILLISECONDS);
        executorService.shutdown();
        assertEquals(counter.sumThenReset(), numberOfIncrements * numberOfThreads);
        assertEquals(counter.sum(), 0);
    }

    @Test
    public void givenLongAccumulator_whenApplyActionOnItFromMultipleThrads_thenShouldProduceProperResult() throws InterruptedException {
        //given
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        LongBinaryOperator higherValueFinder =
                (currentValue, previousValue) -> currentValue > previousValue ? currentValue : previousValue;
        LongAccumulator accumulator = new LongAccumulator(higherValueFinder, 0L);
        int numberOfThreads = 4;
        int numberOfIncrements = 100;

        //when
        Runnable accumulateAction =
                () -> IntStream.rangeClosed(0, numberOfIncrements).forEach(accumulator::accumulate);
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.execute(accumulateAction);
        }


        //then
        executorService.awaitTermination(500, TimeUnit.MILLISECONDS);
        executorService.shutdown();
        assertEquals(accumulator.get(), 100);


    }

}
