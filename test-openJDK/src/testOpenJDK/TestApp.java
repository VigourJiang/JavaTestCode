package testOpenJDK;

import java.util.HashMap;
import java.util.Map;

public class TestApp {
    public static Class c = TestApp.class;
    public static Class c2 = Test2.class;
    public static void main(String[] args) throws InterruptedException {
        Test t = new Test();
        t.fn();
    }
}

class Test {
    static Test2 t1 = new Test2();
    Test2 t2 = new Test2();
    Class classObj = Test.class;
    Test2[] test2Arr = new Test2[2];
    int[] test2Arr2 = new int[2];
    {
        test2Arr[0] = new Test2();
        test2Arr2[0] = 3;
    }
    public void fn() throws InterruptedException {
        Test2 t3 = new Test2();

        Thread.sleep(10000000);
        System.out.println(t3);
    }
}

class Test2 {
    public Map<String, Integer> test2Data = new HashMap<String, Integer>();
    {
        test2Data.put("AABB", 23);
    }
}
