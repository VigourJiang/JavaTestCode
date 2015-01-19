package fiona.apple;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: Jiang Fuqiang
 * Date: 2014-12-16
 */
public class Main {
    public static void main(String[] args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("fiona/apple/applicationContext.xml");
        CommandManager manager =  context.getBean("commandManager", CommandManager.class);
        manager.process(null);
        context.registerShutdownHook();
        context.close();
    }
}
