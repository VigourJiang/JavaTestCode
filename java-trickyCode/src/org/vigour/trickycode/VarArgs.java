package org.vigour.trickycode;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class VarArgs {

    private int sumUp(int... values) {
        int ret = 0;
        for (int i = 0; i < values.length; i++) {
            ret += values[i];
        }
        return ret;
    }

    @Test
    public void test() {
        int sum = sumUp(1, 2, 3, 4);
        assertTrue(sum == 10);

        int sum2 = sumUp(new int[] { 1, 2, 3, 4 });
        assertTrue(sum2 == 10);

        int sum3 = sumUp();
        assertTrue(sum3 == 0);

        int sum4 = sumUp(new int[0]);
        assertTrue(sum4 == 0);
    }

    private int count(int x, Object... args) {
        return args.length;
    }

    @Test
    public void test2() {
        assertTrue(count(1, "1", "2", "3") == 3);
        assertTrue(count(1, new Object[] { "1", "2", "3" }) == 3);

        assertTrue(count(1,
            new Object[] { "1", "2", "3" },
            new Object[] { "1", "2", "3" }) == 2);

        assertTrue(count(1,
            32,
            88,
            new Object[] { "1", "2", "3" },
            new Object[] { "1", "2", "3" }) == 4);
    }
}
