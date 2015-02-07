package org.vigour.java8;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * User: Jiang Fuqiang<br>
 * Date: 2015-2-7<br>
 * Java8中支持通过反射获取参数的名字。
 * 但是源代码编译的时候需要用增加一个参数：javac -parameters
 */
public class TestParameterName {
    public void foo(String name) {


    }

    public static void main(String[] args) {
        Class<TestParameterName> clz = TestParameterName.class;
        for (Method m : clz.getDeclaredMethods()) {
            System.out.print(m.getName());
            for (Parameter parameter : m.getParameters()) {
                System.out.print(" " + parameter.getName());
            }
            System.out.println();
        }
    }
}
