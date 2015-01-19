package org.vigour.trickycode.misc;

import org.junit.Test;

/**
 * User: Jiang Fuqiang
 * Date: 2014-12-5
 */
public class TestVarArgs {

    @Test
    public void test() {
        String[] Strs = new String[]{"aa", "bbb"};
        Object[] tmp = (Object[]) Strs;

        TestTarget.func(new Object[]{"a", "b"});
        TestTarget.func(new String[]{"a", "b"}, new String[]{"a", "b"}, new String[]{"a", "b"});
        TestTarget.func("d", new String[]{"a", "b"}, new String[]{"a", "b"});
    }
}

class TestTarget {
    public static void func(Object... objects) {
        System.out.println(objects.length);
    }
}
