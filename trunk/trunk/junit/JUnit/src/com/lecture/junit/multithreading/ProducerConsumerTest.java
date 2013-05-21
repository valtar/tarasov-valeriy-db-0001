package com.lecture.junit.multithreading;


import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;

import static junit.framework.TestCase.assertEquals;

public class ProducerConsumerTest {

    public interface IMessage {

    }

    public class MessageQueue {
        BlockingQueue<IMessage> queue = new ArrayBlockingQueue<>(2500);

        public void enqueue(IMessage msg) throws InterruptedException {
            queue.put(msg);
        }

        public IMessage dequeue() throws InterruptedException {
            return queue.take();
        }

    }

    public static double doSomething() {
        return Math.random();
    }

    @Test(timeout = 2000)
    public void testMessageQueueMultithreadTest() throws InterruptedException, ExecutionException {

        final int MESSAGE_COUNT = 25;
        final int BUSY_COUNT = 25;
        final int THREAD_COUNT = 10;
        final Random rand = new Random(42);
        final CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

        final MessageQueue msgQ = new MessageQueue();

        final Collection<Callable<Boolean>> prodTasks = new ArrayList<>();
        final Collection<Callable<IMessage>> consTasks = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            prodTasks.add(new Callable<Boolean>() {
                @Override
                public Boolean call() {
                    for (int i = 0; i < MESSAGE_COUNT; i++) {
                        IMessage msg = new IMessage() {
                        };
                        try {
                            msgQ.enqueue(msg);
//                            Thread.sleep(rand.nextInt(50));
                        } catch (InterruptedException e) {
                            break;
                            //ignore
                        }
                        busyLooping(BUSY_COUNT);
                    }

                    return true;
                }
            });

            consTasks.add(new Callable<IMessage>() {
                @Override
                public IMessage call() throws Exception {
                    IMessage msg = null;
                    for (int i = 0; i < MESSAGE_COUNT; i++) {
                        try {
                            msg = msgQ.dequeue();
                            Thread.sleep(rand.nextInt(50));
                        } catch (InterruptedException e) {
                            break;
                            //ignore
                        }
                        busyLooping(BUSY_COUNT);
                    }
                    return msg;
                }
            });
        }

        final ExecutorService prods = Executors.newFixedThreadPool(THREAD_COUNT);
        final ExecutorService cons = Executors.newFixedThreadPool(THREAD_COUNT);
        final long startTime = System.currentTimeMillis();
        prods.invokeAll(prodTasks);
//        latch.await();
//        Thread.sleep(1000);
        final List<Future<IMessage>> futures = cons.invokeAll(consTasks);
        assertEquals(THREAD_COUNT, futures.size());
        for (Future<IMessage> fut : futures) {
            System.out.println(fut.get());
        }
        System.out.println("Total execution time = "  + (System.currentTimeMillis() - startTime));
    }

    private void busyLooping(int BUSY_COUNT) {
        double accum = 0;
        for (int j = 0; j < BUSY_COUNT; j++) {
            accum += doSomething();
        }
        System.out.println(accum);
    }
}
