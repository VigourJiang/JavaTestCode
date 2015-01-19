package org.vigour.trickycode;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * asdfasd
 *
 * @author vigour
 */
public final class SimpleTest {

    @Test
    public void test1() {
        Long l = Long.valueOf(1L);
        Long l2 = Long.valueOf(1L);
        Integer i = Integer.valueOf(1);
        // System.out.println(l == i);
        assertFalse(l.equals(i));
        assertTrue(l.equals(l2));
    }

    @Test
    public void test2_1() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("KeyA", "ValueA");
        map.put("KeyB", "ValueB");
        Iterator<Map.Entry<String, String>> iters =
                map.entrySet().iterator();
        Map.Entry<String, String> first = iters.next();
        Map.Entry<String, String> second = iters.next();
        assertFalse(first == second);
    }

    @Test
    public void test2_2() {
        IdentityHashMap<String, String> idMap =
                new IdentityHashMap<String, String>();
        idMap.put("KeyC", "ValueC");
        idMap.put("KeyD", "ValueD");
        Set<Map.Entry<String, String>> entries = idMap.entrySet();
        Iterator<Map.Entry<String, String>> iters = entries.iterator();
        Map.Entry<String, String> first = iters.next();
        Map.Entry<String, String> second = iters.next();

        String jreVersion = System.getProperty("java.specification.version");
        jreVersion = jreVersion.split("\\.")[1];
        int version = Integer.parseInt(jreVersion);
        if (version >= 7) {
            assertTrue(first != second);
        } else {
            assertTrue(first == second);
        }

        HashSet<Map.Entry<String, String>> l =
                new HashSet<Map.Entry<String, String>>();
        l.addAll(entries);
        for (Map.Entry<String, String> entry : l) {
            System.out.println(entry);
        }
    }

    @Test
    public void test3() {
        int r = -1 * Integer.MIN_VALUE;
        assertTrue(r == Integer.MIN_VALUE);

        int abs = Math.abs(Integer.MIN_VALUE);
        assertTrue(abs == Integer.MIN_VALUE);
        assertTrue(abs < 0);

        long r3 = -1L * Integer.MIN_VALUE;
        assertTrue(r3 != Integer.MAX_VALUE + 1);
        assertTrue(r3 == Integer.MAX_VALUE + 1L);
    }

    @Test
    public void test4() {
        List<String> list = new LinkedList<String>();
        list.add("DS");
        try {
            String[] arr2 = (String[]) list.toArray();
            assertTrue(false);
        } catch (ClassCastException expected) {
            // nothing to do
        }

        List<String> list2 = Arrays.asList("SD", "DSD");
        try {
            String[] arr2 = (String[]) list2.toArray();
        } catch (Exception expected) {
            assertTrue(false);
        }
    }

    @Test
    public void test6() {
        final byte b = (byte) 0xf2;
        final int x = 0xc3;
        assertTrue(0xfffffff2 == (b | (x << 8)));
        assertTrue(0xC3F2 == ((0xff & b) | (x << 8)));
    }

    @Test
    public void test7() {
        int x = 0b0101;
        assertTrue(x << 40 == x << (40 % 32));
        assertTrue(x << 32 == x);

        long t = 0b0101;
        assertTrue(t << 83 == x << (83 % 64));
        assertTrue(t << 64 == t);
    }

    @Test
    public void test8() {
        int mark = 3;
        int x = 1;
        Float f = 32.3F;
        Float d = mark == 4 ? f : x;
        System.out.println(d);

        // compilation error
        // Integer i = mark == 4 ? f : x;
        Integer i = (int) (mark == 4 ? f : x);
    }

    @Test
    public void test9() {
        BigDecimal bd = new BigDecimal(0.1d);
        // System.out.println(bd);
        BigDecimal bd2 = new BigDecimal("0.1");
        //System.out.println(bd2);
        assertFalse(bd.equals(bd2));
    }

    @Test
    public void test11() {
        String x = null;
        assertFalse(x instanceof String);
        assertFalse(x instanceof Object);
    }

    @Test
    public void test12() {
        String s = "  adsfasdf   ";
        s.trim();
        assertTrue(s.charAt(0) == ' ');
        s = s.trim();
        assertTrue(s.charAt(0) == 'a');
    }

    @Test
    public void test13() {
        Integer x = Integer.valueOf(1023);
        Integer y = Integer.valueOf(1023);
        assertTrue(x.equals(y));
        assertFalse(x == y);

        Integer x2 = Integer.valueOf(102);
        Integer y2 = Integer.valueOf(102);
        assertTrue(x2.equals(y2));
        assertTrue(x2 == y2);
    }

    public void test14() {
        // use jvm param:-ea
        assert false;
    }

    @Test
    public void test15() {
        List<Integer> list = new LinkedList<Integer>();
        Set<Integer> set = new HashSet<Integer>();
        list.add(1);
        list.add(10);
        list.add(100);
        set.add(1);
        set.add(10);
        set.add(100);
        list.remove(new Integer(100));
        set.remove(new Integer(100));
        assertTrue(list.size() == 2);
        assertTrue(set.size() == 2);
    }

    @Test
    public void test21() {
        String[] strArray = new String[]{"abc", "def", "hijk"};
        int hashCode = strArray.hashCode();
        int hashCode2 = Arrays.hashCode(strArray);

        strArray[0] = "123";

        assertTrue(hashCode == strArray.hashCode());
        assertFalse(hashCode2 == Arrays.hashCode(strArray));
    }

    @Test
    public void test22() {
        String[] strArray = new String[]{"abc", "def", "hijk"};
        String[] strArray2 = new String[]{"abc", "def", "hijk"};
        assertFalse(strArray.equals(strArray2));
        assertTrue(Arrays.equals(strArray, strArray2));
    }

    @Test
    public void test25() {
        assertTrue(-5 / 2 == -2);
        assertTrue(-5 / -2 == 2);
        assertTrue(5 / -2 == -2);
        assertTrue(-5 % 2 == -1);
        assertTrue(-5 % -2 == -1);
        assertTrue(5 % -2 == 1);
    }

    @Test
    public void test26() {
        try {
            throw new Exception("My Exception");
        } catch (Exception e) {
            throw e;
        } finally {
            return; // return not recommended here
        }
    }

    @Test
    public void test27() {
        assertTrue(Float.compare(Float.NaN, 3f) != 0);
        assertTrue(Float.compare(3f, Float.NaN) != 0);
        assertTrue(Float.compare(Float.NaN, Float.NaN) == 0);
        assertTrue(Float.NaN != Float.NaN);
        assertTrue(Float.NaN != 3f);
    }

    @Test
    public void test28() {
        Object[] objArray = new Long[1];
        try {
            objArray[0] = "exception";
            assertTrue(false);
        } catch (ArrayStoreException e) {

        }

        List strList = new ArrayList<String>();
        strList.add(Long.valueOf(1L));
        strList.add("dsdf");
        strList.add(Boolean.FALSE);
    }

    @Test
    public void test29() {
        int x = 0xFFF2;
        byte b = (byte) x;
        Assert.assertFalse(0xF2 == b);
        Assert.assertTrue(0xF2 == (0xFF & (int) b));
    }

    /**
     * ads
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
