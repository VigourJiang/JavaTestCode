package org.vigour.trickycode.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Jiang Fuqiang<br>
 * Date: 2015-1-31<br>
 * From: 《深入理解Java虚拟机》P51
 */
public class HeapOOM {
    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> items = new ArrayList<>();
        while(true){
            items.add(new OOMObject());
        }
    }
}
