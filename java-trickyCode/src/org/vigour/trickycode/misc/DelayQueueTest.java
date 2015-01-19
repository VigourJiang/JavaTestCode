package org.vigour.trickycode.misc;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {
    @Test
    public void testDelayQueue() {
        DelayQueue<Foo> delayQueue = new DelayQueue<Foo>();
        Foo foo1 = new Foo();
        foo1.value = 100;
        Foo foo2 = new Foo();
        foo2.value = 200;
        delayQueue.add(foo1);
        delayQueue.add(foo2);

        // Checks no element expired
        assertNull(delayQueue.poll());

        // 虽然foo2超期了，但是由于foo1是优先队列的根节点，而foo1没有超期，因此返回的是null
        foo2.value = -1;
        assertNull(delayQueue.poll());
        foo1.value = -1;
        assertSame(delayQueue.poll(), foo1);
        assertSame(delayQueue.poll(), foo2);
        assertNull(delayQueue.poll());
    }
}

class Foo implements Delayed {
    public int value;

    @Override
    public int compareTo(Delayed o) {
        if (!(o instanceof Foo)) {
            return -1;
        }
        int value2 = ((Foo) o).value;
        if (value < value2)
            return -1;
        if (value == value2)
            return 0;
        return 1;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return value;
    }
}
