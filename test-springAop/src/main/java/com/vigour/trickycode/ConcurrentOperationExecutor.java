package com.vigour.trickycode;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.dao.PessimisticLockingFailureException;

/**
 * Created by vigour on 2014-11-26.
 */
@Aspect
public class ConcurrentOperationExecutor implements Ordered {
    private static final int DEFAULT_MAX_RETRIES = 2;
    private int maxRetries = DEFAULT_MAX_RETRIES;
    private int order = 1;

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Around("com.vigour.trickycode.SystemArchitecture.businessService(x)")
    public Object doConcurrentOperation(ProceedingJoinPoint pjp, int x) throws Throwable {
        int numAttempts = 0;
        PessimisticLockingFailureException lockFailureException;
        do {
            System.out.println("BEFORE" + x);
            numAttempts++;
            try {
                return pjp.proceed();
            } catch (PessimisticLockingFailureException ex) {
                System.out.println("Caught exception");
                lockFailureException = ex;
            }
        }
        while (numAttempts <= this.maxRetries);
        System.out.println("Throw Exception in Executor");
        throw lockFailureException;
    }
}