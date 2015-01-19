/**
 * 用来MySQL数据库的用例
 */
package testcase;

import mysql_hr.RegionsEntity;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by vigour on 2014-8-29.
 */
public class TestMySql {
    @Before
    public void prepare() {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring-config-mysql-test.xml");
        factory = (SessionFactory) ctx.getBean("sessionFactory");
        session = factory.openSession();
    }

    @After
    public void after() {
        if (session.isOpen()) {
            session.close();
        }
    }

    private SessionFactory factory;
    Session session;

    @Test
    public void testSave() {
        mysql_hr.RegionsEntity entity = new RegionsEntity();
        entity.setRegionName("XXX");
        session.save(entity); // save时会立即执行Insert语句
        session.beginTransaction();
        System.out.println("InTransaction");
        session.getTransaction().commit();
    }

    //@Test
    public void testPerssist() {
        mysql_hr.RegionsEntity entity = new RegionsEntity();
        entity.setRegionName("XXX");
        session.persist(entity);// persist时会立即执行Insert语句
        session.beginTransaction();
        System.out.println("InTransaction");
        session.getTransaction().commit();// 此时会执行Insert用户
    }

}
