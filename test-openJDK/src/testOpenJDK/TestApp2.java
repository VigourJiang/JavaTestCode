package testOpenJDK;

import java.util.ArrayList;
import java.util.List;

class OOMObject {

    public byte[] placeholder = new byte[64 * 1024];
}

public class TestApp2 {

    public static void main(String[] args) throws InterruptedException {
        fillHeap(10);
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<OOMObject>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.out.println("Preparing...");
        System.gc();
    }
}
