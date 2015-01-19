package com.ume.guavatest;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CollectionUtilsTest {

    private boolean test1Mark;

    @Test
    public void testFilter() {
        test1Mark = false;
        List<String> list = Lists.newArrayList("abc", "def", "hijk");
        Collection<String> rets = Collections2.filter(list, new Predicate<String>() {

            public boolean apply(String input) {
                test1Mark = true;
                return input.startsWith("a");
            }

        });
        assertFalse(test1Mark);
        assertTrue(rets.size() == 1 &&
            rets.iterator().next().equals("abc"));
        assertTrue(test1Mark);
    }

    private boolean test2Mark;

    @Test
    public void testTransform() {
        test2Mark = false;
        List<String> list = Lists.newArrayList("", "123", "hijk");
        Collection<Integer> rets = Collections2.transform(list,
            new Function<String, Integer>() {

                public Integer apply(String input) {
                    test2Mark = true;
                    if (input == null)
                        return 0;
                    return input.length();
                }
            });

        assertFalse(test2Mark);
        org.junit.Assert.assertArrayEquals(rets.toArray(new Integer[3]),
            new Integer[] { 0, 3, 4 });
        assertTrue(test2Mark);
    }

    @Test
    public void testPermutations() {
        List<String> list = Lists.newArrayList("", "123", "hijk");
        Collection<List<String>> per = Collections2.orderedPermutations(list);
        assertEquals(per.size(), 6);
        for (List<String> itemList : per) {
            System.out.println(itemList);
        }
    }

}
