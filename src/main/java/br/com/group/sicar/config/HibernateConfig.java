package br.com.group.sicar.config;


import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = {"br.com.group.sicar"})
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class HibernateConfig {

	@Value("${spring.hib.show_sql}")
	private String showSql;
	
	@Value("${spring.hib.format_sql}")
	private String formatSql;
	
	@Value("${spring.hbm2ddl.auto}")
	private String hbm2dll;
	
	@Value("${spring.hib.jboss.datasource}")
	private String jbossDataSource;
	
	@Value("${spring.jpa.properties.hibernate.dialect}")
	private String dialect;
	
	@Value("${spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults}")
	private String temp;
	
	@Value("${spring.hib.user}")
	private String user;
	
	@Value("${spring.hib.password}")
	private String password;
	
	@Value("${spring.hib.driver}")
	private String driver;
	
	
	@Bean
	public BasicDataSource getDataSource() {
		final BasicDataSource bds = new BasicDataSource();
		bds.setUrl(jbossDataSource);
		bds.setUsername(user);
		bds.setPassword(password);
		bds.setDriverClassName(driver);
		
		return bds;
	}
	/*
	@Bean
	public DataSource getDataSource() {
		final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
		dsLookup.setResourceRef(true);
		DataSource dataSource = dsLookup.getDataSource(jbossDataSource);//java:jboss/datasources/sirgcDS
		return dataSource;
	}
	 */

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(this.getDataSource());
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan("br.com.group.sicar");
		entityManagerFactoryBean.setJpaProperties(this.hibProperties());
		return entityManagerFactoryBean;
	}

	private Properties hibProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.temp.use_jdbc_metadata_defaults", temp);
		properties.put("hibernate.dialect", dialect);
		properties.put("hibernate.hbm2ddl.auto",hbm2dll);		
		properties.put("hibernate.show_sql", showSql);
		properties.put("hibernate.format_sql", formatSql);
		properties.put("hibernate.transaction.manager_lookup_class", "org.hibernate.transaction.JBossTransactionManagerLookup");
		return properties;
	}
}
