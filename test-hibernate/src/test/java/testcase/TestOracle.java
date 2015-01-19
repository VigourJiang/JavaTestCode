package testcase;


import hr.CountriesEntity;
import hr.EmployeesEntity;
import hr.RegionsEntity;
import org.hibernate.*;
import org.hibernate.classic.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import hr.RegionsEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by vigour on 2014-8-5.
 */
public class TestOracle {
    @Before
    public void prepare() {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring-config-oracle-test.xml");
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


    //@Test
    public void testDelete() {
        Transaction trans = session.beginTransaction();
        try {
            List<Object[]> list = (List<Object[]>)
                    session.createQuery("FROM hr.EmployeesEntity").list();
            System.out.println(list.size());
            session.delete(list.get(0));
            session.evict(list.get(0));
            list.remove(0);
            //session.evict(list.get(1));
            //list.remove(1);
            System.gc(); // 此时，被删除的Entity仍然会被Session引用

        } finally {
            trans.rollback();
        }
    }


    //@Test
    public void testLoad() {
        session.beginTransaction();

        RegionsEntity entity = (RegionsEntity) session.load(RegionsEntity.class,
                Long.valueOf(100301L));
        System.out.println("Before getRegionid()");
        entity.getRegionId(); // no sql executed
        System.out.println("After getRegionid()");

        System.out.println("Before getRegionName()");
        try {
            entity.getRegionName(); // 执行SQL，没有找到记录就抛出异常
        } catch (ObjectNotFoundException exception) {
            System.out.println("Excepted exception");
        }
        System.out.println("After getRegionName()");

        session.getTransaction().commit();
    }



    //  @Test
    public void testGet() {
        session.beginTransaction();

        // session.get会立即执行sql。找不到就返回null
        RegionsEntity entity = (RegionsEntity) session.get(RegionsEntity.class,
                Long.valueOf(100301L));
        System.out.println("Before getRegionid()");
        assertNull(entity);
        System.out.println("After getRegionid()");
        session.getTransaction().commit();
    }

    //@Test
    public void testUpdate() {
        session.beginTransaction();
        RegionsEntity entity = new RegionsEntity();
        entity.setRegionName("DDS");
        entity.setRegionId(1234L); // 之前不存在这个ID
        session.update(entity);
        session.getTransaction().commit();
    }


    //@Test
    public void testUpdate2() {
        session.beginTransaction();

        // session.get会立即执行sql。找不到就返回null
        RegionsEntity entity = (RegionsEntity) session.get(RegionsEntity.class,
                Long.valueOf(1L));
        // 把已经存在的对象，重新Update一次，不起效果
        session.update(entity);

        RegionsEntity entity2 = new RegionsEntity();
        entity2.setRegionId(1L);
        try {
            // ID如果重复，但是不是同一个Java对象，会导致抛出异常
            session.update(entity2);
        }catch(NonUniqueObjectException e){
            System.out.println("Expected Exception");
        }

        session.getTransaction().commit();
    }

    //@Test
    public void testSearchSameObj() {
        session.beginTransaction();
        RegionsEntity entity = (RegionsEntity) session
                .createQuery("from hr.RegionsEntity e where e.regionId = 1")
                .uniqueResult();
        RegionsEntity entity2 = (RegionsEntity) session
                .createQuery("from hr.RegionsEntity e where e.regionId = 1")
                .uniqueResult();

        assertTrue(entity == entity2);

        session.getTransaction().rollback();
    }


    //@Test
    public void testSearchSameObj2() {
        session.beginTransaction();
        RegionsEntity entity = (RegionsEntity) session
                .createQuery("from hr.RegionsEntity e where e.regionId = 1")
                .uniqueResult();

        Session session2 = factory.openSession();
        RegionsEntity entity2 = (RegionsEntity) session2
                .createQuery("from hr.RegionsEntity e where e.regionId = 1")
                .uniqueResult();

        assertTrue(entity != entity2);

        session.getTransaction().rollback();
        session2.close();
    }

    //@Test
    public void testPersist_Save_1() {
        RegionsEntity entity = new RegionsEntity();
        entity.setRegionName("First");

        session.persist(entity);

        System.out.println("Before Transaction");
        session.beginTransaction();
        session.getTransaction().commit();
        System.out.println("After Transaction");
    }

    //@Test
    public void testPersist_Save_2() {
        RegionsEntity entity = new RegionsEntity();
        entity.setRegionName("First");

        session.save(entity);

        System.out.println("Before Transaction");
        session.beginTransaction();
        session.getTransaction().commit();
        System.out.println("After Transaction");
    }

    //     @Test
    public void testSqlQuery() {
        Transaction trans = session.beginTransaction();
        try {
            List<Object[]> list = (List<Object[]>)
                    session.createSQLQuery("SELECT * FROM REGIONS").list();
            session.delete(list.get(0));
            for (Object[] item : list) {
                System.out.println(item[0] + " " + item[1]);
            }
        } finally {
            trans.commit();
        }
    }

    //    @Test
    public void testSqlQuer2() {
        Transaction trans = session.beginTransaction();
        try {
            session.setFlushMode(FlushMode.AUTO);
            List<RegionsEntity> list = (List<RegionsEntity>)
                    session.createSQLQuery("SELECT * FROM REGIONS")
                            .addEntity(RegionsEntity.class).list();
            for (RegionsEntity item : list) {
                System.out.println(item.getRegionName());
            }
            session.clear();
        } finally {
            trans.commit();
        }
    }

    //    @Test
    public void testSqlQuer3() {
        Transaction trans = session.beginTransaction();
        try {
            List list =
                    session.createSQLQuery(
                            "SELECT country.COUNTRY_ID, country.country_name, country.REGION_ID, region.region_id, region.region_name " +
                                    "FROM REGIONS region, COUNTRIES country " +
                                    "WHERE region.REGION_ID = country.REGION_ID")
                            .addEntity("country", CountriesEntity.class)
//                            .addEntity("region", RegionsEntity.class)
                            .addJoin("region", "country.region")
                            .list();
            for (Object item : list) {
                Object[] arr = (Object[]) item;
                System.out.println(arr[0].getClass());
                System.out.println(((CountriesEntity) arr[0]).getRegion().getRegionName());

                System.out.println(arr[1].getClass());
//                System.out.println(arr[2].getClass());
            }
        } finally {
            trans.commit();
        }
    }

    //@Test
    public void testSqlQuer4() {
        Transaction trans = session.beginTransaction();
        Transaction trans2 = session.getTransaction();
        System.out.println(trans == trans2);
        try {
            List list =
                    session.createSQLQuery(
                            "select {e.*}, {m.*} from EMPLOYEEs e, employees m " +
                                    "where e.manager_id = m.employee_id")
                            .addEntity("e", EmployeesEntity.class)
                            .addEntity("m", EmployeesEntity.class)
                            .list();
            for (Object item : list) {
                Object[] arr = (Object[]) item;
                EmployeesEntity first = (EmployeesEntity) arr[0];
                EmployeesEntity second = (EmployeesEntity) arr[1];
                System.out.println(first.getEmployeeId());
                System.out.println(second == null ? "null" : second.getEmployeeId());
            }
        } finally {
            trans.commit();
        }
    }

    //@Test
    public void testHqlQuery() {
        Transaction trans = session.beginTransaction();
        try {
            List<EmployeesEntity> list = (List<EmployeesEntity>)
                    session.createQuery(
                            "from hr.EmployeesEntity"
                    )
                            .list();
            for (EmployeesEntity item : list) {
                if (item.getManager() == null) {
                    System.out.println("Top!");
                    System.out.println(item.getEmployeeId());
                } else {
                    System.out.println(item.getManager());
                }
            }
        } finally {
            trans.commit();
        }

    }

    //@Test
    public void testSave() {
        RegionsEntity region = new RegionsEntity();

        Transaction trans = session.beginTransaction();
        try {
            region.setRegionName("DKKK");
            session.save(region);
        } finally {
            trans.commit();
        }
        session.close();

        System.out.println(region.getRegionId());
        Session session2 = factory.openSession();
        Transaction trans2 = session2.beginTransaction();
        try {
            region.setRegionName("DKKK");
            session2.save(region);
        } finally {
            trans2.commit();
        }
        System.out.println(region.getRegionId());
    }

    // @Test
    public void testGetCurrentSession() {
        Session session1 = factory.getCurrentSession();
        session1.beginTransaction().commit();
        assertFalse(session1.isOpen());

        Session session2 = factory.getCurrentSession();
        assertTrue(session1 != session2);
        session2.beginTransaction().commit();
    }
}