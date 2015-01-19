package lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * User: Jiang Fuqiang
 * Date: 2014-12-16
 */
public class LifeCycleFoo{

    public void test(){
        System.out.println(context.getClass().getName());
    }

    @Autowired
    private ApplicationContext context;

/*    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        System.out.println(context.getClass().getName());
    }
*/
}
