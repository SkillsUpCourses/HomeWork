/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springjavaconfig.DAO;


import com.stoxa.springjavaconfig.Model.Contact;
import com.stoxa.springjavaconfig.Model.ContactMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;  


/**
 *
 * @author ksu
 */
public class ContactJDBCTemplateDao implements ContactDAO {
    
private static final Logger LOGGER = LoggerFactory.getLogger(ContactJDBCTemplateDao.class);
private Connection connect;
private PreparedStatement statement;
private ResultSet result;
private String SQL;
private JdbcTemplate jdbcTemplate;
    

    
 
    @Override
    public void addContact(Contact contact) {
        SQL = "INSERT INTO `mydb`.`contacts` (`CONTACT_NAME`,"
                    + "`CONTACT_SURNAME`,`CONTACT_PHONE`, `CONTACT_EMAIL`)"
                    + "VALUES (?, ?, ?, ?);";
        
        jdbcTemplate.update( SQL, contact.getFirstName(), contact.getLastName(), contact.getPhone(), contact.getEmail());
        return;
    }

    @Override
    public void updateContact(Contact contact) {
        SQL = "UPDATE `mydb`.`contacts`"
                    + "SET `CONTACT_NAME` = ?, `CONTACT_SURNAME` = ?, "
                    + "`CONTACT_PHONE` = ?, `CONTACT_EMAIL` = ? WHERE `CONTACT_PHONE` = ?;";
            jdbcTemplate.update( SQL, contact.getFirstName(), contact.getLastName(), contact.getPhone(), contact.getEmail(),contact.getPhone());
            return;
    }

    @Override
    public void deleteContact(Contact contact) {
        SQL = "DELETE FROM `mydb`.`contacts` WHERE `contacts`.`CONTACT_PHONE` = ?;";
            jdbcTemplate.update( SQL, contact.getPhone());
            return;
    }

    @Override
    public Contact getContact(String phone) {
        SQL = "SELECT `contacts`.`CONTACT_NAME`, `contacts`.`CONTACT_SURNAME`,\n"
                    + "`contacts`.`CONTACT_PHONE`, `contacts`.`CONTACT_EMAIL` FROM `mydb`.`contacts` WHERE `contacts`.`CONTACT_PHONE` = ?;";
        Contact contact = jdbcTemplate.queryForObject ( SQL, new Object[]{phone}, new ContactMapper());
        return contact ;
    }
    

    @Override
    public Contact getContact(int number) {
        SQL = "SELECT `contacts`.`CONTACT_NAME`, `contacts`.`CONTACT_SURNAME`,\n"
                    + "`contacts`.`CONTACT_PHONE`, `contacts`.`CONTACT_EMAIL` FROM `mydb`.`contacts` WHERE `contacts`.`ID` = ?;";
        Contact contact = jdbcTemplate.queryForObject ( SQL, new Object[]{number}, new ContactMapper());
        return contact ;
    }

    @Override
    public Collection<Contact> getAllContacts() {
        SQL = "SELECT `contacts`.`CONTACT_NAME`, `contacts`.`CONTACT_SURNAME`,\n"
                    + "`contacts`.`CONTACT_PHONE`, `contacts`.`CONTACT_EMAIL` FROM `mydb`.`contacts`;";
            Collection <Contact> contacts = jdbcTemplate.query(SQL, 
                                new ContactMapper());
      return contacts;
    }

    @Override
    public void clearAll() {
        SQL = "DELETE FROM `mydb`.`contacts`;";
        jdbcTemplate.update( SQL);
        return;
    }
}