package com.vigour.trickycode;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.PessimisticLockingFailureException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DefaultListableBeanFactory
                factory = (DefaultListableBeanFactory)context.getBeanFactory();
        for(String name: factory.getBeanDefinitionNames()){
            BeanDefinition definition =  factory.getBeanDefinition(name);
            System.out.println(name + "\t\t" + definition.getClass().getName());
        }

        MyService service = (MyService) context.getBean("myService");
        try {
            service.save(3);
        }
        catch(PessimisticLockingFailureException e){
            System.out.println("Exception caught in main");
        }
    }
}
