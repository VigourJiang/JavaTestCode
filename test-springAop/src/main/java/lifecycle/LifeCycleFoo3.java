package lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.SmartLifecycle;

/**
 * User: Jiang Fuqiang
 * Date: 2014-12-16
 */
public class LifeCycleFoo3 implements SmartLifecycle{

    private boolean started = false;

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        started = false;
        System.out.println("#### stop with callback");
        callback.run();
    }

    @Override
    public void start() {
        started = true;
        System.out.println("#### start");
    }

    @Override
    public void stop() {
        started = false;
        System.out.println("#### stop");
    }

    @Override
    public boolean isRunning() {
        return started;
    }

    @Override
    public int getPhase() {
        return -1;
    }
}
