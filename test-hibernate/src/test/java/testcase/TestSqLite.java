/**
 * 用来测试SqLite数据库
 */
package testcase;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

import java.io.File;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * Created by vigour on 2014-8-22.
 */
public class TestSqLite {

    @Test
    public void testMain(){
        long start = Calendar.getInstance().getTimeInMillis();
        test("F:\\trickyCode\\abc.db");
        long end = Calendar.getInstance().getTimeInMillis();
        System.out.println(end-start);
    }
    public void test(String filePath) {
        SessionFactory sessionFactory = new Configuration()
                .setProperty("hibernate.connection.url", "jdbc:sqlite:" + filePath)
                .configure("hibernate.sqlite.cfg.xml")
                .buildSessionFactory();
        sessionFactory.close();
        StatelessSession session = sessionFactory.openStatelessSession();
        session.beginTransaction();
       // SQLQuery q = session.createSQLQuery("select * from JOBS");
        //q.list();
        session.getTransaction().commit();
    }

}

