package config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * 
 * @author ihor
 */
@Configuration
public class PersistenceJPAConfig {

	/*
	 * @Bean
	 * public JpaVendorAdapter jpaVendorAdapter() {
	 * EclipseLinkJpaVendorAdapter vendorAdapter = new
	 * EclipseLinkJpaVendorAdapter();
	 * vendorAdapter.setShowSql(false);
	 * vendorAdapter.setGenerateDdl(true);
	 * //vendorAdapter.setDatabase(Database.H2);
	 * return vendorAdapter;
	 * }
	 */
	/*@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setPackagesToScan(new String[] { "small.business.dao.*" });

		JpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter() {
			{
				// JPA properties ...
			}
		};

		factoryBean.setPersistenceUnitName("db.dbPU");
		factoryBean.setPersistenceXmlLocation("META-INF/persistence.xml");
		factoryBean.setJpaVendorAdapter(vendorAdapter);
		// factoryBean.setJpaVendorAdapter(this.jpaVendorAdapter());
		// factoryBean.setJpaProperties(additionlProperties());

		return factoryBean;
	}*/

	@Bean
	// (destroyMethod = "close")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.sqlite.JDBC");// org.sqlite.JDBC.class.getName()
		dataSource.setUrl("jdbc:sqlite:db.db");
		dataSource.setUsername("");
		dataSource.setPassword("");
		return dataSource;
	}

	/*@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

	@Bean
	public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
		return new PersistenceAnnotationBeanPostProcessor();
	}*/
}