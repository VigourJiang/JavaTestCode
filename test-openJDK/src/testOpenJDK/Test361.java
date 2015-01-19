package testOpenJDK;

/**
 * Created by vigour on 2014-8-1.
 */
public class Test361 {
    public static void main(String[] args) throws InterruptedException {
        byte[] alloc1, alloc2, alloc3, alloc4;
        System.gc();

        for(int i = 0; i < 10; i++) {
            alloc1 = new byte[2 * 1024 * 1024];
            alloc2 = new byte[2 * 1024 * 1024];
            alloc3 = new byte[2 * 1024 * 1024];
            alloc4 = new byte[4 * 1024 * 1024];
            Thread.sleep(1000);
        }
        System.out.println("finished");
    }
}
