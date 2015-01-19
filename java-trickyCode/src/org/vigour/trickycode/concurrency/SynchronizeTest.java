package org.vigour.trickycode.concurrency;

public class SynchronizeTest {
    public static volatile boolean threadGo;
    public static volatile boolean inThread;

    public static void main(String[] args) throws InterruptedException {
        final Test t = new Test();
        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (t) {
                    SynchronizeTest.inThread = true;
                    while (!SynchronizeTest.threadGo) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {

                        }
                    }
                }
            }

        });
        thread1.start();
        while (!inThread) {
            Thread.sleep(1000);
        }
        // 以下语句会导致死锁
        t.foo();
        threadGo = true;
    }
}

class Test {
    public synchronized void foo() {
        System.out.println("foo");
    }
}
