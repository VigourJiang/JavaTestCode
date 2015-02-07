package lang;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * User: Jiang Fuqiang<br>
 * Date: 2015-1-19
 */
public class TestCompare {
    public static void main(String[] args) {
        Date d1 = Calendar.getInstance().getTime();
        Date d2 = Calendar.getInstance().getTime();
        d2 = DateUtils.addDays(d2, 1);
        int ret = new CompareToBuilder().append(d1, d2).toComparison();
        System.out.println(ret == -1);

        ret = new CompareToBuilder().append(d2, d1).toComparison();
        System.out.println(ret == 1);

        Date d3 = new Date(d2.getTime());
        ret = new CompareToBuilder().append(d3, d3).toComparison();
        System.out.println(ret == 0);

        ret = new CompareToBuilder().append(null, d2).toComparison();
        System.out.println(ret == -1);

        ret = new CompareToBuilder().append(d1, null).toComparison();
        System.out.println(ret == 1);

        ret = new CompareToBuilder().append((Object) null, (Object) null).toComparison();
        System.out.println(ret == 0);
    }
}
