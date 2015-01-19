package lifecycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: Jiang Fuqiang
 * Date: 2014-12-16
 */

public class LifeCycleMain {
    public static void main(String[] args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("lifecycle/applicationContext.xml");
        LifeCycleFoo life = context.getBean("lifeCycleFoo", LifeCycleFoo.class);
        life.test();
//        LifeCycleFoo2 life2 = context.getBean("lifeCycleFoo2", LifeCycleFoo2.class);
        //LifeCycleFoo3 life3 = context.getBean("lifeCycleFoo3", LifeCycleFoo3.class);
    }
}
