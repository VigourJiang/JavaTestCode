package com.vigour.trickycode;

import org.springframework.dao.PessimisticLockingFailureException;

/**
 * Created by vigour on 2014-11-26.
 */
public class MyService {
    public void save(int x){
        System.out.println("Throw Exception : " + x);
        throw new PessimisticLockingFailureException("");
    }
}
