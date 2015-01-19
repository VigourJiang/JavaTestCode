package org.vigour.trickycode;

/**
 * Created by vigour on 2014-11-8.
 */
public class StringTest {
    public static void main(String[] args){
        String str = "𤭢";
        System.out.println(str.length()); // 输出为2
        System.out.println(Integer.toHexString(str.codePointAt(0)));
        System.out.println(Integer.toHexString(str.codePointAt(1)));
        System.out.println(Character.isSupplementaryCodePoint(str.codePointAt(0)));
        System.out.println(Character.isSupplementaryCodePoint(str.codePointAt(1)));
        System.out.println(Integer.toHexString(0xFFFF & str.charAt(0)));
        System.out.println(Integer.toHexString(0xFFFF & str.charAt(1)));

        System.out.println(str.getBytes().length);
    }
}
