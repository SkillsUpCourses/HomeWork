/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springjavaconfig.Service;

import com.stoxa.springjavaconfig.DAO.ContactDAO;
import com.stoxa.springjavaconfig.DAO.ContactSimpleDAO;
import com.stoxa.springjavaconfig.Model.Contact;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;

/**
 *
 * @author ksu
 */
@RunWith(MockitoJUnitRunner.class)
public class ContactManagerTest {
    
    private ContactDAO dao;
    Contact contact1, contact2;
    ContactManager instance;
    
    public ContactManagerTest() {
        dao = mock(ContactSimpleDAO.class);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        contact1 = new Contact("Оксана", "Рудниченко", "+380937405289", "dn100488rol@gmail.com");
        contact2 = new Contact("Тошик", "Синяев", "+380507464280", "dn091986saa@gmail.com");
        instance = new ContactManager();
        instance.setDao(dao);
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of init method, of class ContactManager.
     */
    @Test
    public void testInit() {
        System.out.println("init test");
        ContactManager spyContactManager = spy(instance);
        ApplicationEventPublisher publisher = mock(ApplicationEventPublisher.class);
        spyContactManager.setApplicationEventPublisher(publisher);
        spyContactManager.setMaxContactBookSize(2);
        Map<String,Contact> contacts = new HashMap<>();
        contacts.put(contact1.getPhone(), contact1);
        contacts.put(contact2.getPhone(), contact2);
        when(dao.getAllContacts()).thenReturn(contacts.values());
        spyContactManager.init();
        verify(spyContactManager).clear();
    }

    /**
     * Test of addContact method, of class ContactManager.
     */
    @Test
    public void testAddContact() {
        System.out.println("addContact test");
        Contact contact = new Contact("Егорчик", "Синяев", "+380777777777", "egorchik@gmail.com");
        instance.addContact(contact);
        verify(dao).addContact(contact);
    }

    /**
     * Test of updateContact method, of class ContactManager.
     */
    @Test
    public void testUpdateContact() {
        System.out.println("updateContact test");
        Contact contact = new Contact("Оксана", "Синяева", "+380937405289", "dn100488rol@gmail.com");;
        instance.addContact(contact1);
        instance.updateContact(contact);
        verify(dao).updateContact(contact);
    }

    /**
     * Test of deleteContact method, of class ContactManager.
     */
    @Test
    public void testDeleteContact() {
        System.out.println("deleteContact test");
        ApplicationEventPublisher publisher = mock(ApplicationEventPublisher.class);
        instance.setApplicationEventPublisher(publisher);
        Contact contact = contact1;
        instance.addContact(contact);
        instance.deleteContact(contact);
        //verify(publisher).publishEvent(anyInt());
        verify(dao).deleteContact(contact);
    }

    /**
     * Test of getContact method, of class ContactManager.
     */
    @Test
    public void testGetContact() {
        System.out.println("getContact test");
        String phone = "+380937405289";
        instance.addContact(contact1);
        when(dao.getContact(phone)).thenReturn(contact1);
        Contact expResult = contact1;
        Contact result = instance.getContact(phone);
        verify(dao).getContact(phone);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllContacts method, of class ContactManager.
     */
    @Test
    public void testGetAllContacts() {
        System.out.println("getAllContacts test");
        instance.addContact(contact1);
        Collection <Contact> expResult = mock(Collection.class);
        when(dao.getAllContacts()).thenReturn(expResult);
        Collection <Contact> result = instance.getAllContacts();
        assertEquals(expResult, result);
    }

    /**
     * Test of clearAll method, of class ContactManager.
     */
    @Test
    public void testClearAll() {
        System.out.println("clearAll test");
        ApplicationEventPublisher publisher = mock(ApplicationEventPublisher.class);
        instance.setApplicationEventPublisher(publisher);
        instance.clearAll();
        verify(dao).clearAll();
    }

    /**
     * Test of clear method, of class ContactManager.
     */
    @Test
    public void testClear() {
        System.out.println("clear test");
        instance.addContact(contact1);
        ApplicationEventPublisher publisher = mock(ApplicationEventPublisher.class);
        instance.setApplicationEventPublisher(publisher);
        instance.clear();
        verify(dao).getAllContacts();
    }   
}
