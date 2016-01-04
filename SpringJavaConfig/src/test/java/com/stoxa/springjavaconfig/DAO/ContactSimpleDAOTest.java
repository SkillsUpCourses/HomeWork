/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springjavaconfig.DAO;

import com.stoxa.springjavaconfig.DAO.Impl.ContactJPADAO;
import com.stoxa.springjavaconfig.Entity.MappedContact;
import com.stoxa.springjavaconfig.Model.Contact;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import static org.mockito.Mockito.mock;

/**
 *
 * @author stoxa
 */
public class ContactSimpleDAOTest {
    
    Contact contact;
    Contact contact2;
    ContactJPADAO instance;
    
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
    
    @Ignore
    @Before
    public void setUp() {
        contact = new Contact("Оксана", "Рудниченко", "+380937405289", "dn100488rol@gmail.com");
        contact2 = new Contact("Тошик", "Синяев", "+380507464280", "dn091986saa@gmail.com");
        instance = new ContactJPADAO();
        instance.insertContact(new MappedContact(contact));
        instance.insertContact(new MappedContact(contact2));
        
    }
   

    /**
     * Test of addContact method, of class ContactSimpleDAO.
     */
    @Ignore
    @Test
    public void testAddContact() {
        System.out.println("addContact test");
        
        assertEquals(2, instance.selectAllContacts().size());
        System.out.println("addContact test is passed");
    }

    /**
     * Test of updateContact method, of class ContactSimpleDAO.
     */
    @Ignore
    @Test
    public void testUpdateContact() {
        System.out.println("updateContact test");
        Contact newContact = new Contact("Оксана", "Синяева", "+380937405289", "dn100488rol@gmail.com");
        instance.updateContact(new MappedContact (newContact));
        assertEquals(newContact.getLastName(), instance.selectContact("+380937405289").getLastName());
        System.out.println("updateContact test is passed");
    }

    /**
     * Test of deleteContact method, of class ContactSimpleDAO.
     */
    @Ignore
    @Test
    public void testDeleteContact() {
        System.out.println("deleteContact test");
        instance.deleteContact(new MappedContact(contact));
        assertEquals(1, instance.selectAllContacts().size());
        System.out.println("deleteContact test is passed");
    }

    /**
     * Test of getContact method, of class ContactSimpleDAO.
     */
    @Ignore
    @Test
    public void testGetContact_String() {
        System.out.println("getContact test");
        String phone = "+380937405289";
        Contact expResult = contact;
        Contact result = new Contact(instance.selectContact(phone));
        assertEquals(expResult, result);
        System.out.println("getContact test is passed");
    }

    /**
     * Test of getAllContacts method, of class ContactSimpleDAO.
     */
    @Ignore
    @Test
    public void testGetAllContacts() {
        System.out.println("getAllContacts test");
        Collection<MappedContact> result = instance.selectAllContacts();
        assertEquals(2, result.size());
        System.out.println("getAllContacts test is passed");
    }

    /**
     * Test of clearAll method, of class ContactSimpleDAO.
     */
    @Ignore
    @Test
    public void testClearAll() {
        System.out.println("clearAll");
        instance.clearAll();
        assertEquals(0, instance.selectAllContacts().size());
        System.out.println("clearAll test is passed");
    }


    /**
     * Test of getContact method, of class ContactSimpleDAO.
     */
    @Ignore
    @Test
    public void testGetContact_int() {
        System.out.println("getContact test");
        int number = 0;
        Contact expResult = contact;
        Contact result = new Contact(instance.selectContact(number));
        assertEquals(expResult, result);
        System.out.println("getContact test is passed");
    }
    
}
