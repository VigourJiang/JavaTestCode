package org.vigour.trickycode.jvm;

/**
 * User: Jiang Fuqiang
 * Date: 2015-1-31
 */
public class testGC {
    public static void main(String[] args) {
        testAllocation();
    }

    private static final int _1MB = 1024 * 1024;

    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[6 * _1MB];
    }
}
