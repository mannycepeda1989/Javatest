package com.baeldung.producerconsumer;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class Producer implements Runnable {
    private static final Logger log = Logger.getLogger(Producer.class.getCanonicalName());
    private final DataQueue dataQueue;
    private static final AtomicInteger idSequence = new AtomicInteger(0);
    private boolean runFlag = false;
    final ReentrantLock lock = new ReentrantLock();

    public Producer(DataQueue dataQueue) {
        this.dataQueue = dataQueue;
    }

    @Override
    public void run() {
        runFlag = true;
        produce();
    }

    public void produce() {
        while (runFlag) {

            try {
                lock.lock();
                while (dataQueue.isFull() && runFlag) {
                    try {
                        dataQueue.waitOnFull();
                    } catch (InterruptedException e) {
                        log.severe("Error while waiting to Produce messages.");
                        break;
                    }
                }

                if (!runFlag) {
                    break;
                }

                Message message = generateMessage();
                dataQueue.add(message);
                dataQueue.notifyAllForEmpty();

                log.info("Size of the queue is: " + dataQueue.getSize());

                //Sleeping on random time to make it realistic
                ThreadUtil.sleep((long) (Math.random() * 100));

            }
            finally{
                lock.unlock();
            }
        }

        log.info("Producer Stopped");
    }

    private Message generateMessage() {
        Message message = new Message(idSequence.incrementAndGet(), Math.random());
        log.info(String.format("[%s] Generated Message. Id: %d, Data: %f%n",
                Thread.currentThread().getName(), message.getId(), message.getData()));

        return message;
    }

    public void stop() {
        runFlag = false;
        dataQueue.notifyAllForFull();
    }
}
