package com.ume.jdktest;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class TestConcurrentHashMap {
    private TreeMap<Integer, String> tm = new TreeMap<>();

    @Test
    public void testSortedMap() {
        System.out.println("_____________");
        SortedMap<Integer, String> smp = tm;
        for (Map.Entry<Integer, String> entry : smp.entrySet()) {
            System.out.println(entry);
        }
    }
    @Test
    public void testNavigableMap(){
        NavigableMap<Integer, String> nmp = tm;
        System.out.println("lowerEntry: " + nmp.lowerEntry(7));
        System.out.println("floorEntry: " + nmp.floorEntry(7));
        System.out.println("higherEntry: " + nmp.higherEntry(5));
        System.out.println("floorEntry: " +nmp.floorEntry(5));
    }

    @Before
    public void before() {
        tm.put(5, "value5");
        tm.put(7, "value7");
        tm.put(1, "value1");
        tm.put(9, "value9");
        tm.put(3, "value3");
    }
}
