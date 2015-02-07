package lang;

import org.apache.commons.lang.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * User: Jiang Fuqiang
 * Date: 2015-1-19
 */
public class TestDateUtils {
    public static void main(String[] args) {
        Date d = Calendar.getInstance().getTime();
        d = DateUtils.truncate(d, Calendar.DAY_OF_MONTH);
        System.out.println(d);
    }
}
