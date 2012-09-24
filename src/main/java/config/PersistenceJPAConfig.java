package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;

/**
 *
 * @author ihor
 */
@Configuration
@ComponentScan("small.business.*")
public class PersistenceJPAConfig {

    @Bean
    public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPersistenceXmlLocation("/META-INF/persistence.xml");
        return factoryBean;
    }

    /*
     * @Bean
     * public JpaVendorAdapter jpaVendorAdapter() {
     * EclipseLinkJpaVendorAdapter vendorAdapter = new
     * EclipseLinkJpaVendorAdapter();
     * vendorAdapter.setShowSql(false);
     * vendorAdapter.setGenerateDdl(false);
     * return vendorAdapter;
     * }
     * 
     * @Bean
     * // (destroyMethod = "close")
     * public DataSource dataSource() {
     * DriverManagerDataSource dataSource = new DriverManagerDataSource();
     * dataSource.setDriverClassName("org.sqlite.JDBC");
     * dataSource.setUrl("jdbc:sqlite:db.db");
     * dataSource.setUsername("");
     * dataSource.setPassword("");
     * return dataSource;
     * }
     * 
     * 
     * @Bean
     * public PlatformTransactionManager transactionManager() {
     * JpaTransactionManager transactionManager = new JpaTransactionManager();
     * transactionManager.setEntityManagerFactory(entityManagerFactory().getObject
     * ());
     * return transactionManager;
     * }
     */
}