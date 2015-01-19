package com.ume.jdktest;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class TestLinkedHashMap {

    private static final int CAP = 10_000_000;
    private HashMap<Integer, String> hashMap = new HashMap<>(CAP, 0.75f);
    private LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>(
        CAP, 0.75f);

    private void test(Map<Integer, String> map) {

        String[] keys = new String[map.size()];
        long start = Calendar.getInstance().getTimeInMillis();
        int i = 0;
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            keys[i++] = entry.getValue();
        }
        long end = Calendar.getInstance().getTimeInMillis();
        System.out.println("Time: " + (end - start));
        System.out.println(Arrays.toString(keys));
    }

    @Test
    public void testHashMap() {
        System.gc();
        System.out.println("HashMap Test");
        test(hashMap);
        System.gc();
        System.out.println("LinkedHashMap Test");
        test(linkedHashMap);
    }

    @Before
    public void before() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            int key = r.nextInt();
            hashMap.put(key, Integer.toString(i));
            linkedHashMap.put(key, Integer.toString(i));
        }
    }
}
