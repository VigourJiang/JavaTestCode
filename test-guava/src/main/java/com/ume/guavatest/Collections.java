package com.ume.guavatest;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.LinkedHashMultiset;
import com.google.common.collect.MutableClassToInstanceMap;
import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.Table;
import com.google.common.collect.TreeRangeMap;
import com.google.common.collect.TreeRangeSet;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Hello world!
 * 
 */
public class Collections
{
    @Test
    @Ignore
    public void testMultiSet()
    {
        HashMultiset<String> multiSet = HashMultiset.<String> create();
        multiSet.add("a");
        multiSet.add("d");
        multiSet.add("2", 3);
        multiSet.add("0");
        for (String str : multiSet) {
            System.out.println(str);
        }
        multiSet.add(null);

        System.out.println(multiSet.count(null));

        LinkedHashMultiset<String> ts = null;
        ts.add(null, 3);
    }

    @Test
    public void testMultiMap()
    {
        HashMultimap<String, String> multiMap = HashMultimap.<String, String> create();
        multiMap.put("A", "A0");
        multiMap.put("A", "A1");
        System.out.println(multiMap);

        String[] strArray = new String[100];
        System.out.println(strArray.getClass().getComponentType());
    }

    @Test
    @Ignore
    public void testRangeMap() {
        TreeRangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(1, 10), "foo");
        System.out.println(rangeMap);

        rangeMap.put(Range.open(3, 6), "bar");
        System.out.println(rangeMap);

        rangeMap.put(Range.open(10, 20), "foo");
        System.out.println(rangeMap);

        rangeMap.remove(Range.closed(5, 11));
        System.out.println(rangeMap);
    }

    @Test
    @Ignore
    public void testRangeSet() {
        RangeSet<Integer> rangeSet = TreeRangeSet.create();

        rangeSet.add(Range.closed(1, 10));
        System.out.println(rangeSet);

        rangeSet.add(Range.closedOpen(11, 15));
        System.out.println(rangeSet);

        rangeSet.add(Range.closedOpen(15, 20));
        System.out.println(rangeSet);

        rangeSet.add(Range.openClosed(0, 0));
        System.out.println(rangeSet);

        rangeSet.remove(Range.open(5, 10));
        System.out.println(rangeSet);
    }

    @Test
    @Ignore
    public void testClassToInstanceMap() {
        ClassToInstanceMap<Number> numberDefaults = MutableClassToInstanceMap.create();
        numberDefaults.putInstance(Integer.class, Integer.valueOf(0));
        numberDefaults.putInstance(Float.class, 3.5F);
    }

    @Test
    @Ignore
    public void testTable() {
        Table<Integer, Integer, String> weightedGraph = HashBasedTable.create();
        weightedGraph.put(2, 3, "AB");
        weightedGraph.put(2, 8, "43EF");
        weightedGraph.put(5, 8, "58TC");

        System.out.println(weightedGraph.row(2));
        System.out.println(weightedGraph.column(8));
    }

    @Test
    @Ignore
    public void testBiMap() {
        HashBiMap<String, Integer> userId = HashBiMap.create();
        userId.put("Admin", 1);
        userId.put("Guest", 103);
        System.out.println(userId.inverse().get(103));
        System.out.println(userId.inverse().get(1));
        System.out.println(userId.inverse().get(3000));
    }
}
