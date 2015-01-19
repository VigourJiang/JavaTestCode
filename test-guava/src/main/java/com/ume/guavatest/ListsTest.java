package com.ume.guavatest;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

public class ListsTest {
    @Test
    @Ignore
    public void testTransform() {
        List<String> list = Lists.newArrayList("1d3", "p56", "abc", "@DADS");
        List<String> ret = Lists.transform(list, new Function<String, String>() {

            public String apply(String input) {
                return input.toUpperCase();
            }
        });
        System.out.println(ret);
    }

    @Test
    public void testReverse() {
        List<String> list = Lists.newArrayList("1d3", "p56", "abc", "@DADS");
        System.out.println(list);
        System.out.println(Lists.reverse(list));
    }

}
