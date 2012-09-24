package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 *
 * @author root
 */
@Configuration
// @EnableLoadTimeWeaving
@EnableAspectJAutoProxy
// @EnableAsync
@Import({PersistenceJPAConfig.class})
// @EnableTransactionManagement
// @PropertySource("classpath:/com/acme/app.properties")
// @EnableSpringConfigured
public class ApplicationConfig {
    // @Value("${bean.name}") String beanName;

    // static Logger log = Logger.getLogger(ApplicationConfig.class.getName());
    @Bean
    public ApplicationContextProvider contextApplicationContextProvider() {
        return new ApplicationContextProvider() {
        };
    }
}