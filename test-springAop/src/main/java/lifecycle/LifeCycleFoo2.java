package lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * User: Jiang Fuqiang
 * Date: 2014-12-16
 */
public class LifeCycleFoo2 implements InitializingBean, DisposableBean {

    private int id;

    public void specifiedInit() {
        System.out.println("specifiedInit" + id);
    }

    public void specifiedDestroy() {
        System.out.println("specifiedDestroy" + id);
    }


    @PostConstruct
    public void postConstruct() {
        System.out.println("postConstruct" + id);
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("preDestroy" + id);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy" + id);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet" + id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
