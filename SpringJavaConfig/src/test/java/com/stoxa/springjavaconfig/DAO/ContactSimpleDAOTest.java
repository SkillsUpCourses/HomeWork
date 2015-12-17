/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springjavaconfig.DAO;

import com.stoxa.springjavaconfig.Model.Contact;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 *
 * @author stoxa
 */
public class ContactSimpleDAOTest {
    
    Contact contact;
    Contact contact2;
    ContactSimpleDAO instance;
    
    public ContactSimpleDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("The class ContactSimpleDAO is testing");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("The testing of class ContactSimpleDAO is finished");
    }
    
    @Before
    public void setUp() {
        contact = new Contact("Оксана", "Рудниченко", "+380937405289", "dn100488rol@gmail.com");
        contact2 = new Contact("Тошик", "Синяев", "+380507464280", "dn091986saa@gmail.com");
        instance = new ContactSimpleDAO();
        instance.setContacts(new HashMap<>());
        instance.addContact(contact);
        instance.addContact(contact2);
        
    }
   

    /**
     * Test of addContact method, of class ContactSimpleDAO.
     */
    @Test
    public void testAddContact() {
        System.out.println("addContact test");
        
        assertEquals(2, instance.getAllContacts().size());
        System.out.println("addContact test is passed");
    }

    /**
     * Test of updateContact method, of class ContactSimpleDAO.
     */
    @Test
    public void testUpdateContact() {
        System.out.println("updateContact test");
        Contact newContact = new Contact("Оксана", "Синяева", "+380937405289", "dn100488rol@gmail.com");
        instance.updateContact(newContact);
        assertEquals(newContact.getLastName(), instance.getContact("+380937405289").getLastName());
        System.out.println("updateContact test is passed");
    }

    /**
     * Test of deleteContact method, of class ContactSimpleDAO.
     */
    @Test
    public void testDeleteContact() {
        System.out.println("deleteContact test");
        instance.deleteContact(contact);
        assertEquals(1, instance.getAllContacts().size());
        System.out.println("deleteContact test is passed");
    }

    /**
     * Test of getContact method, of class ContactSimpleDAO.
     */
    @Test
    public void testGetContact_String() {
        System.out.println("getContact test");
        String phone = "+380937405289";
        Contact expResult = contact;
        Contact result = instance.getContact(phone);
        assertEquals(expResult, result);
        System.out.println("getContact test is passed");
    }

    /**
     * Test of getAllContacts method, of class ContactSimpleDAO.
     */
    @Test
    public void testGetAllContacts() {
        System.out.println("getAllContacts test");
        Collection<Contact> result = instance.getAllContacts();
        assertEquals(2, result.size());
        System.out.println("getAllContacts test is passed");
    }

    /**
     * Test of clearAll method, of class ContactSimpleDAO.
     */
    @Test
    public void testClearAll() {
        System.out.println("clearAll");
        instance.clearAll();
        assertEquals(0, instance.getAllContacts().size());
        System.out.println("clearAll test is passed");
    }

    /**
     * Test of setContacts method, of class ContactSimpleDAO.
     */
    @Test
    public void testSetContacts() {
        System.out.println("setContacts test");
        Map <String, Contact> contacts = new HashMap<>();
        contacts.put(contact.getPhone(), contact);
        contacts.put(contact2.getPhone(), contact);
        instance.setContacts(contacts);
        assertEquals(2, instance.getAllContacts().size());
        System.out.println("setContacts test is passed");
    }

    /**
     * Test of getContact method, of class ContactSimpleDAO.
     */
    @Test
    public void testGetContact_int() {
        System.out.println("getContact test");
        int number = 0;
        Contact expResult = contact;
        Contact result = instance.getContact(number);
        assertEquals(expResult, result);
        System.out.println("getContact test is passed");
    }
    
}
