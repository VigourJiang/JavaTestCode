package com.ume.jdktest;

import org.junit.Test;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class TestTreeMap {

    private HashMap<Integer, String> map2 = new HashMap<>(300, 0.75f);
    
    private ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>(
        300, 0.75f, 2);

    @Test
    public void before() {
        map.putIfAbsent(11, "11 Value 1");
        System.out.println(map);
        map.putIfAbsent(11, "11 Value 2");
        System.out.println(map);

        map.remove(11, "XX");
        System.out.println(map.get(11));
        map.remove(11, "11 Value 1");
        System.out.println(map.get(11));

        System.out.println("__________");

        map.put(8, "Initial Value");
        System.out.println(map.get(8));
        map.replace(8, "Replaced Value");
        System.out.println(map.get(8));

        map.replace(8, "XXXX", "Failure Value");
        System.out.println(map.get(8));
        map.replace(8, "Replaced Value", "Success Value"); // 
        System.out.println(map.get(8));
    }
}
