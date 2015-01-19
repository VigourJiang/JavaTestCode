package org.vigour.trickycode;

import org.junit.Test;

public class TestIntern {
    private static String CONST_STRING =
        "http://blog.sina.com.cn/51javabbshttp://blog.sina.com.cn/51javabbshttp://blog.sina.com.cn/51javabbshttp://blog.sina.com.cn/51javabbshttp://blog.sina.com.cn/51javabbshttp://blog.sina.com.cn/51javabbshttp://blog.sina.com.cn/51javabbs";

    @Test
    public void testA() {
        System.out.println("不使用intern()方法的测试开始");

        System.gc();
        String temp[] = new String[20000];
        xx(temp);
        System.gc();
        memoryinfo();
        System.out.println("不使用intern()方法的测试结束");
    }

    /**
     * 这里是注释
     */
    @Test
    public void testB() {

        System.out.println("使用intern()方法的测试开始");
        System.gc();
        String temp[] = new String[20000];
        xx(temp);
        intern(temp);
        System.gc();
        memoryinfo();
        System.out.println("使用intern()方法的测试结束");
    }

    public static void memoryinfo() {
        long total = Runtime.getRuntime().totalMemory();
        long free = Runtime.getRuntime().freeMemory();
        System.out.println("内存使用情况: " + (total - free));
    }

    public static void xx(String temp[]) {
        for (int i = 0; i < 20000; i++) {
            temp[i] = new String(CONST_STRING + i %100);
        }
    }

    private static void intern(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].intern();
        }
    }

}
