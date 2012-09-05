package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableSpringConfigured;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * 
 * @author root
 */
@Configuration
// @EnableLoadTimeWeaving
@EnableAspectJAutoProxy
// @EnableAsync
@ImportResource("database-config.xml")
// @EnableTransactionManagement
@ComponentScan("small.business.*")
// @PropertySource("classpath:/com/acme/app.properties")
@Import({ PersistenceJPAConfig.class })
@EnableSpringConfigured
public class ApplicationConfig {
	// @Value("${bean.name}") String beanName;

	// static Logger log = Logger.getLogger(ApplicationConfig.class.getName());
	@Bean
	public ApplicationContextProvider contextApplicationContextProvider() {
		return new ApplicationContextProvider() {
		};
	}

	/*@Bean(destroyMethod = "close")
	public EntityManagerFactory entityManagerFactory() {
		return Persistence.createEntityManagerFactory("db.dbPU");
	}*/
	/*
	 * @Bean
	 * 
	 * @Lazy
	 * public PlatformTransactionManager transactionManager() {
	 * JpaTransactionManager transactionManager = new JpaTransactionManager();
	 * transactionManager.setEntityManagerFactory(entityManagerFactory());
	 * return transactionManager;
	 * }
	 * 
	 * @Bean
	 * 
	 * @Lazy
	 * public PersistenceAnnotationBeanPostProcessor
	 * persistenceAnnotationBeanPostProcessor() {
	 * return new PersistenceAnnotationBeanPostProcessor();
	 * }
	 */
}