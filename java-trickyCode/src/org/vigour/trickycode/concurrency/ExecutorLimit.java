package org.vigour.trickycode.concurrency;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

public class ExecutorLimit {
    public static void testLimit() {
        int processorCount = Runtime.getRuntime().availableProcessors();
        System.out.println(processorCount);
        ExecutorService service = Executors.newFixedThreadPool(processorCount);
        int counter = 0;
        try {
            while (counter++ < 10000) {
                try {
                    service.execute(new Runnable() {

                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                System.out.println("Interrupted");
                            }
                            System.out.println("here");
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                System.out.println("Interrupted 2");
                            }
                            System.out.println("here 2");
                        }
                    });
                } catch (RejectedExecutionException reject) {
                    System.out.println("Rejected");
                    break;
                }
            }
        } finally {
            service.shutdownNow();
            try {
                service.awaitTermination(1000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("main terminated, " + counter);
        }
    }

    public static void main(String[] args) {
        testLimit();
    }
}
