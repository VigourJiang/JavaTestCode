package foo;

import beanutils.Person;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public class SimpleTest {
    @Test
    public void test1() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        MyConverter c = new MyConverter();
        ConvertUtils.register(c, Date.class);

        Person p = new Person();
        BeanUtils.setProperty(p, "name", "zhangxx");
        BeanUtils.setProperty(p, "birthday", "invalid");

                System.out.println(p);
    }
}
class MyConverter implements Converter {

    public Object convert(Class type, Object value) {
        System.out.println(value);
        return new Date();
    }
}
