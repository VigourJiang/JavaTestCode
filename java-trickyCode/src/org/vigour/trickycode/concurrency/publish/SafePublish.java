package org.vigour.trickycode.concurrency.publish;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 根据《Java Concurrency in Practice》 3.5章节的描述而来。<br>
 * To publish an object safely, both the reference to the object and the
 * object's state must be made visible to other threads at the same time. A
 * properly constructed object can be safely published by:
 * <ol>
 * <li>Initializing an object reference from a static initializer;
 * <li>Storing a reference to it into a volatile field or AtomicReference;
 * <li>Storing a reference to it into a final field of a properly constructed
 * object;
 * <li>Storing a reference to it into a field that is properly guarded by a
 * lock.
 * </ol>
 * @author vigour
 * 
 */
public final class SafePublish {
    private SafePublish() {
    }

    // Initializing an object reference from a static initializer;
    private static PublishedObject publishedObj = new PublishedObject();
    private static PublishedObject publishedObj2;
    static {
        publishedObj2 = new PublishedObject();
    }

    // Storing a reference to it into a volatile field or AtomicReference;
    private static volatile PublishedObject publishedObj3;
    private static AtomicReference<PublishedObject> publishedObj4;

    public static void foo() {
        publishedObj3 = new PublishedObject();
        publishedObj4 =
            new AtomicReference<PublishedObject>(new PublishedObject());

        // Storing a reference to it into a final field 
        // of a properly constructed object
        PublishWrapper wrapper = new PublishWrapper();
        wrapper.getValue();
    }
}
