package org.vigour.trickycode.misc;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.concurrent.DelayQueue;

public class UrlTest {

    @Test
    public void UrlTest1() throws MalformedURLException, InterruptedException {
        HashSet set = new HashSet();
        URL url = new URL("http://www.baidu.com");
        // never do this. URL.hashCode() will resolve IP address
        set.add(url);
    }
}
