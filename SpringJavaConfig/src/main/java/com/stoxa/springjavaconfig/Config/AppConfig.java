/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springjavaconfig.Config;

import com.stoxa.springjavaconfig.DAO.ContactDAO;
import com.stoxa.springjavaconfig.DAO.Impl.ContactHibernate2DAO;
import com.stoxa.springjavaconfig.DAO.Impl.ContactHibernateDAO;
import com.stoxa.springjavaconfig.DAO.Impl.ContactSimpleDAO;
import com.stoxa.springjavaconfig.EventListener.ClearEvent;
import com.stoxa.springjavaconfig.EventListener.DeleteContactListener;
import com.stoxa.springjavaconfig.Model.Contact;
import com.stoxa.springjavaconfig.Factory.ContactBeanFactory;
import com.stoxa.springjavaconfig.Service.Impl.ContactManager;
import com.stoxa.springjavaconfig.Service.ContactService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Ksu
 */
@Configuration
@ComponentScan({"com.stoxa.springjavaconfig.DAO", "com.stoxa.springjavaconfig.Service"})
@EnableTransactionManagement
@PropertySource("classpath:ContactBookMaximumSize.properties")
@PropertySource("classpath:contacts.properties")
public class AppConfig {

    
    @Bean
    public ContactBeanFactory contactBeanFactory() {
        return new ContactBeanFactory();
    }
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {
	return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public ApplicationEventPublisherAware applicationEventPublisherAware() {
        return new ContactManager();
    }

    @Bean
    ApplicationListener<ClearEvent> applicationListener() {
        return new DeleteContactListener();
    }
    
    @Bean
    DataSource dataSource() throws FileNotFoundException, IOException {
        DriverManagerDataSource dataSource =  new DriverManagerDataSource();
        /**Properties property = new Properties();
        FileInputStream propertyFile = new FileInputStream("src/main/resources/mydb.properties");
        property.load(propertyFile);*/
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mydb");
        dataSource.setUsername("Ksu");
        dataSource.setPassword("KurochkaRyaba13");
       // dataSource.setConnectionProperties(property);
        return dataSource;
    }
    
   /** @Bean
    JdbcTemplate jdbcTemplate() throws IOException {
        return new JdbcTemplate(dataSource());
    }**/
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws IOException {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.stoxa.springjavaconfig.Entity");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(additionalProperties());
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
    
     private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");
        return properties;
    }


}
