package config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public abstract class ApplicationContextProvider implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        // Wiring the ApplicationContext into a static method  
        AppContext.setApplicationContext(ctx);
    }
}
