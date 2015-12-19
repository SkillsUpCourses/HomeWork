/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springjavaconfig.Config;

import com.stoxa.springjavaconfig.DAO.ContactDAO;
import com.stoxa.springjavaconfig.DAO.ContactSimpleDAO;
import com.stoxa.springjavaconfig.EventListener.ClearEvent;
import com.stoxa.springjavaconfig.EventListener.DeleteContactListener;
import com.stoxa.springjavaconfig.Model.Contact;
import com.stoxa.springjavaconfig.Factory.ContactBeanFactory;
import com.stoxa.springjavaconfig.Service.ContactManager;
import com.stoxa.springjavaconfig.Service.ContactService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author Ksu
 */
@Configuration
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

    @Value("${maxSize}")
    private int maxContactBookSize;

    @Bean
    public ContactDAO dao() throws Exception {
        final ContactSimpleDAO dao = new ContactSimpleDAO();
        Map<String,Contact> contacts = new HashMap<String, Contact>();
        dao.setContacts(contacts);
        return dao;
    }

    @Bean(initMethod = "init")
    public ContactService contactService() throws Exception {
        ContactManager contactService = new ContactManager();
        contactService.setDao(dao());
        contactService.setMaxContactBookSize(maxContactBookSize);
        return contactService;
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
    
    @Bean
    JdbcTemplate jdbcTemplate() throws IOException {
        return new JdbcTemplate(dataSource());
    }
}
