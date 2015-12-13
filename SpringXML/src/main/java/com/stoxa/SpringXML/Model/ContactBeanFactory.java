/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.SpringXML.Model;

import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import java.util.Properties;
import org.springframework.beans.factory.FactoryBean;

/**
 *
 * @author ksu
 */
public class ContactBeanFactory implements FactoryBean<Contact>
       /** extends AbstractFactoryBean*/ {
    
    private static  int contactCount = 1;
    private Contact newContact = null;
    private FileInputStream fis;
    private Properties property = new Properties();

    @Override
    public Class getObjectType() {
        return Contact.class;
    }
    
    public boolean hasNextInstance () {
        if ((property.getProperty(contactCount + ".firstName"))!=null) {
            return true;
        }
        return false;
    }

    @Override
    public Contact getObject() throws Exception {
        String firstName;
        String lastName;
        String phone;
        String email;
        try {
            fis = new FileInputStream("src/main/resources/contacts.properties");
            property.load(fis);
            if (!hasNextInstance()) {
                throw new NullPointerException("There are no more contacts in file src/main/resources/contacts.properties, contactCount = " + contactCount);
            }
            newContact = new Contact();
            firstName = property.getProperty(contactCount + ".firstName");
            newContact.setFirstName(firstName);
            lastName = property.getProperty(contactCount + ".lastName");
            newContact.setLastName(lastName);
            phone = property.getProperty(contactCount + ".phone");
            newContact.setPhone(phone);
            email = property.getProperty(contactCount + ".email");
            newContact.setEmail(email);

        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        } finally {
            fis.close();
        }
        contactCount++;
        return newContact;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
}
