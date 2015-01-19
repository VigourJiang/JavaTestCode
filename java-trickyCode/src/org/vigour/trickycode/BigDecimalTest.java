package org.vigour.trickycode;

import java.math.BigDecimal;

/**
 * Created by vigour on 2014-9-26.
 */
public class BigDecimalTest {
    public static void main(String[] args) {
        BigDecimal bd = new BigDecimal("0.35");
        bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
        System.out.println(bd.doubleValue());
        bd = new BigDecimal("125");
        System.out.println(bd.scale());
        bd = bd.setScale(-1, BigDecimal.ROUND_HALF_UP);
        System.out.println(bd.doubleValue());

    }
}
