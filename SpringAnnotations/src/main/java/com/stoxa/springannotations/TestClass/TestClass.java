/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springannotations.TestClass;

import com.stoxa.springannotations.Model.Contact;
import com.stoxa.springannotations.Model.ContactBeanFactory;
import com.stoxa.springannotations.Service.ContactManager;
import com.stoxa.springannotations.Service.ContactService;
import java.util.List;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author ksu
 */
public class TestClass {
   public static void main(String[] args) throws Exception {
        ApplicationContext context =
        new ClassPathXmlApplicationContext("config2.xml");
        FactoryBean factory = context.getBean(ContactBeanFactory.class);
        
        Contact c1 = (Contact) factory.getObject();
        Contact c2 = (Contact) factory.getObject();
        Contact c3 = (Contact) factory.getObject();
        Contact c4 = (Contact) factory.getObject();
       // Contact c5 = (Contact) factory.getObject();
        
        ContactService contactService = (ContactService) context.getBean(ContactManager.class);
 
        System.out.println("ADD CONTACT ==============");
        contactService.addContact(c1);
        contactService.addContact(c2);
        contactService.addContact(c3);
        contactService.addContact(c4);
        //contactService.addContact(c5);
        List<Contact> result1 = contactService.getAllContacts();
        for(Contact c : result1) {
            System.out.println(c);
        }
        System.out.println("******************************************");
        
        System.out.println("UPDATE CONTACT ==============");
        Contact change = new Contact ("Алексей", "Соколов", "+380934567894", "sokolov@yandex.ru");
        contactService.updateContact(change);
        List<Contact> result2 = contactService.getAllContacts();
        for(Contact c : result2) {
            System.out.println(c);
        }
        System.out.println("******************************************");
        
        System.out.println("DELETE CONTACT ==============");
        contactService.deleteContact(c3);
        List<Contact> result3 = contactService.getAllContacts();
        for(Contact c : result3) {
            System.out.println(c);
        }
        System.out.println("******************************************");        
 
        System.out.println("GET CONTACT ==============");
        Contact contact = contactService.getContact("+380674560901");
        System.out.println(contact);
        
        System.out.println("******************************************");        
        
        System.out.println("CLEAR ALL CONTACTS ==============");
        contactService.clearAll();
        List<Contact> result4 = contactService.getAllContacts();
        for(Contact c : result4) {
            System.out.println(c);
        }
    }
   }

