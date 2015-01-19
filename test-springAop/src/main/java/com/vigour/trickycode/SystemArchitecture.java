package com.vigour.trickycode;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.BeanFactory;

/**
 * Created by vigour on 2014-11-26.
 */
public class SystemArchitecture {
    @Pointcut(value="execution(* com.vigour.trickycode.MyService.save(..)) && args(x)")
    public void businessService(int x){
    }
}
