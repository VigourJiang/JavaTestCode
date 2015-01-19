package org.vigour.trickycode.concurrency;

import java.util.concurrent.ExecutorService;

/**
 * 本例子演示了，调用System.exit后，其他线程直接退出，finally语句也不会执行
 * @author vigour
 * 
 */
public class SystemExit {

    private static volatile boolean finished;

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    finished = true;
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        System.out.println("InterruptedException");
                    }
                }
                finally {
                    System.out.println("finally");
                }
            }

        });
        t.start();
        while (!finished) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
        System.exit(0);
    }
}
