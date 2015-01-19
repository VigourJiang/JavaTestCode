import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class Temp {
    @Test
    public void test() {

        String hex2 = String.format("%d", 0x7fff_ffff_ffff_ffffL);
        long value = Long.parseLong(hex2);
        if(value == 0xefff_ffff_ffff_ffffL){
            System.out.println("SS");
        }

        String hex = Integer.toHexString(3);

        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(0, 23);
        list.add(1, 24);
        list.add(2, 25);
        list.add(3, 26);
        System.out.println(Arrays.toString(list.toArray()));


        String text = String.format("%02d", 213);
        System.out.println(text);
    }
}
