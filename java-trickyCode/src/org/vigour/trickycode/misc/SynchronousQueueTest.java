package org.vigour.trickycode.misc;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueTest {
    SynchronousQueue<Object> sq;
    Object foo;

    @Before
    public void before() {
        sq = new SynchronousQueue<Object>();
        foo = new Object();
    }

    @Test
    public void test() throws InterruptedException {
        Thread sender = new Thread(new Sender(0));
        Thread receiver = new Thread(new Receiver(2000)); // NOCK

        sender.start();
        receiver.start();
        // sender要和receiver同步
        sender.join();
        assertFalse(receiver.isAlive());
    }

    @Test
    public void test2() throws InterruptedException {
        Thread sender = new Thread(new Sender(2000)); // NOCK
        Thread receiver = new Thread(new Receiver(0));

        sender.start();
        receiver.start();
        // sender要和receiver同步
        receiver.join();
        assertFalse(sender.isAlive());
    }

    private class RunBase {
        protected int sleepSeconds;

        public RunBase(int v) {
            this.sleepSeconds = v;
        }
    }

    private class Sender extends RunBase implements Runnable {
        public Sender(int v) {
            super(v);
        }

        @Override
        public void run() {
            try {
                Thread.sleep(sleepSeconds);
                sq.put(foo);
            } catch (InterruptedException e) {
            }
        }

    }

    private class Receiver extends RunBase implements Runnable {
        public Receiver(int v) {
            super(v);
        }

        @Override
        public void run() {
            try {
                Thread.sleep(sleepSeconds);
                sq.take();
            } catch (InterruptedException e) {
            }
        }

    }
}
