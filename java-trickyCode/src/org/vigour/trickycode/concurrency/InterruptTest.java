package org.vigour.trickycode.concurrency;

import java.util.concurrent.locks.ReentrantLock;

public class InterruptTest {
    static volatile boolean set = false;
    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                while(!set){

                }

                try {
                    Thread.sleep(1000);
                    System.out.println("Thread 2 not interrupted");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    System.out.println("Thread 2 interrupted");
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread t = new Thread(runnable);
        t.start();
        Thread.sleep(1000);
        t.interrupt();
        set = true;
        // Thread.currentThread().interrupt();
        try {
            t.join();
            System.out.println("MainThread not interrupted");
            t.join();
            System.out.println("MainThread not interrupted2");
        } catch (InterruptedException e) {
            System.out.println("MainThread interrupted");
        }

    }
}
