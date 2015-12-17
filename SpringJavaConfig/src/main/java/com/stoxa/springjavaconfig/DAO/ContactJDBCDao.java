/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springjavaconfig.DAO;


import com.stoxa.springjavaconfig.Model.Contact;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Properties;

/**
 *
 * @author ksu
 */
public class ContactJDBCDao implements ContactDAO {
    
private static final Logger LOGGER = LoggerFactory.getLogger(ContactJDBCDao.class);
private Connection connect;
private PreparedStatement statement;
private ResultSet result;
private final Properties property;
private final FileInputStream propertyFile;
private final String url;
private final String user;
private final String password;

ContactJDBCDao() throws FileNotFoundException, IOException {
    this.property = new Properties();
    this.propertyFile = new FileInputStream("src/main/resources/mydb.properties");
    property.load(propertyFile);
    this.url = property.getProperty("url");
    this.user = property.getProperty("user");
    this.password = property.getProperty("password"); 
}

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Unable to connect to the driver JDBC", e);
        }
    }
    
    public Connection getConnect() {
        try {
            this.connect = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            LOGGER.error("Unable to get connection", ex);
        }
        return connect;
    }
    
 
    @Override
    public void addContact(Contact contact) {
        try {
            connect = this.getConnect();
            statement = connect.prepareStatement(
                    "INSERT INTO `mydb`.`contacts` (`CONTACT_NAME`,"
                    + "`CONTACT_SURNAME`,`CONTACT_PHONE`, `CONTACT_EMAIL`)"
                    + "VALUES (?, ?, ?, ?);");
            statement.setString(1, contact.getFirstName());
            statement.setString(2, contact.getLastName());
            statement.setString(3, contact.getPhone());
            statement.setString(4, contact.getEmail());
            statement.execute();
        } catch (SQLException ex1) {
            LOGGER.error("Unable to insert to MySGL base contact " + contact, ex1);
        } finally {
            this.letOutResources();
        }
    }

    @Override
    public void updateContact(Contact contact) {
        try {
            connect = this.getConnect();
            statement = connect.prepareStatement("UPDATE `mydb`.`contacts`"
                    + "SET `CONTACT_NAME` = ?, `CONTACT_SURNAME` = ?, "
                    + "`CONTACT_PHONE` = ?, `CONTACT_EMAIL` = ? WHERE `CONTACT_PHONE` = ?;");
            statement.setString(1, contact.getFirstName());
            statement.setString(2, contact.getLastName());
            statement.setString(3, contact.getPhone());
            statement.setString(4, contact.getEmail());
            statement.setString(5, contact.getPhone());
            statement.execute();
        } catch (SQLException ex) {
            LOGGER.error("Unable to update to MySGL base contact " + contact, ex);
        } finally {
            this.letOutResources();
        }
    }

    @Override
    public void deleteContact(Contact contact) {
        try {
            connect = this.getConnect();
            statement = connect.prepareStatement("DELETE FROM `mydb`.`contacts` WHERE `contacts`.`CONTACT_PHONE` = ?;");
            statement.setString(1, contact.getPhone());
            statement.execute();
        } catch (SQLException ex) {
            LOGGER.error("Unable to delete contact " + contact + " from MySGL base", ex);
        } finally {
            this.letOutResources();
        }
    }

    @Override
    public Contact getContact(String phone) {
        Contact contact = null;
        try {
            connect = this.getConnect();
            statement = connect.prepareStatement("SELECT `contacts`.`CONTACT_NAME`, `contacts`.`CONTACT_SURNAME`,\n"
                    + "`contacts`.`CONTACT_PHONE`, `contacts`.`CONTACT_EMAIL` FROM `mydb`.`contacts` WHERE `contacts`.`CONTACT_PHONE` = ?;");
            statement.setString(1, phone);
            result = statement.executeQuery();
            contact = new Contact(result);
        } catch (SQLException ex) {
            LOGGER.error("Unable to select contact with phone number " + phone + " from MySGL base", ex);
        } finally {
            this.letOutResources();
        }
        if (contact != null) {
            return contact;
        }
        throw new NullPointerException("Contact with phone number " + phone + " is not found");
    }
    

    @Override
    public Contact getContact(int number) {
        Contact contact = null;
        try {
            connect = this.getConnect();
            statement = connect.prepareStatement("SELECT `contacts`.`CONTACT_NAME`, `contacts`.`CONTACT_SURNAME`,\n"
                    + "`contacts`.`CONTACT_PHONE`, `contacts`.`CONTACT_EMAIL` FROM `mydb`.`contacts` WHERE `contacts`.`ID` = ?;");
            statement.setInt(1, number);
            result = statement.executeQuery();
            contact = new Contact(result);
        } catch (SQLException ex) {
            LOGGER.error("Unable to select contact number " + number + " from MySGL base", ex);
        } finally {
            this.letOutResources();
        }
        if (contact != null) {
            return contact;
        }
        throw new NullPointerException("Contact number " + number + " is not found");
    }

    @Override
    public Collection<Contact> getAllContacts() {

        Collection<Contact> contacts = new ArrayList<Contact>();

        try {
            connect = this.getConnect();
            statement = connect.prepareStatement("SELECT `contacts`.`CONTACT_NAME`, `contacts`.`CONTACT_SURNAME`,\n"
                    + "`contacts`.`CONTACT_PHONE`, `contacts`.`CONTACT_EMAIL` FROM `mydb`.`contacts`;");
            result = statement.executeQuery();

            while (result.next()) {
                Contact contact = new Contact(result);
                contacts.add(contact);
            }
        } catch (SQLException ex) {
            LOGGER.error("Unable to select contacts from MySGL base", ex);
        } finally {
            this.letOutResources();
        }
        return contacts;
    }

    @Override
    public void clearAll() {
        try {
            connect = this.getConnect();
            statement = connect.prepareStatement("DELETE FROM `mydb`.`contacts`;");
            statement.execute();
        } catch (SQLException ex) {
            LOGGER.error("Unable to delete contacts from MySGL base", ex);
        } finally {
            this.letOutResources();
        }
    }
    
    
    private void closeStatement () {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex1) {
                LOGGER.error("Unable to close statement ", ex1);
            }
        }
    }
    
    private void closeResultSet () {
        if (result != null) {
            try {
                result.close();
            } catch (SQLException ex2) {
                LOGGER.error("Unable to close ResultSet ", ex2);
            }
        }
    }
    private void closeConnection () {
        if (connect==null) {
            return;
        } 
        try {
            connect.close();
        } catch (SQLException ex3) {
            LOGGER.error("Unable to close connection", ex3);
        }
    }
    
    public void letOutResources () {
        this.closeStatement();
        this.closeResultSet();
        this.closeConnection();
    }
}
