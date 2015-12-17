/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springjavaconfig.Model;

import com.stoxa.springjavaconfig.Factory.ContactBeanFactory;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.NullPointerException;

/**
 *
 * @author stoxa
 */
public class ContactBeanFactoryTest {
    
    ContactBeanFactory instance;

    public ContactBeanFactoryTest() {
    }
    
    @Before
    public void setUp() {
        instance = new ContactBeanFactory();
    }

    /**
     * Test of getObjectType method, of class ContactBeanFactory.
     */
    @Test
    public void testGetObjectType() {
        System.out.println("getObjectType test");
        Class expResult = Contact.class;
        Class result = instance.getObjectType();
        assertEquals(expResult, result);
        System.out.println("getObjectType test is passed");
    }

    /**
     * Test of hasNextInstance method, of class ContactBeanFactory.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testHasNextInstance() throws Exception {
        System.out.println("hasNextInstance test");
        boolean expResult = false;
        boolean result = instance.hasNextInstance();
        assertEquals(expResult, result);
        System.out.println("testHasNextInstance test is passed");
    }

    /**
     * Test of getObject method, of class ContactBeanFactory.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetObject1() throws Exception {
        System.out.println("getObject test");
        Field field = instance.getClass().getDeclaredField("contactCount");
        field.setAccessible(true);
        field.setInt(instance, 1);
        Contact expResult = new Contact("Андрей", "Соколов", "+380934567894", "sokolov@yandex.ru");
        Contact result = instance.getObject();
        assertEquals(expResult.toString(), result.toString());
        System.out.println("getObject test is passed");
    }
    
    @Test(expected=NullPointerException.class)
       public void testGetObject2() throws Exception {
        System.out.println("getObject test");
        Field field = instance.getClass().getDeclaredField("contactCount");
        field.setAccessible(true);
        field.setInt(instance, 11);
        Contact expResult = new Contact("Андрей", "Соколов", "+380934567894", "sokolov@yandex.ru");
        Contact result = instance.getObject();
        assertEquals(expResult.toString(), result.toString());
        System.out.println("getObject test is passed");
    }

    /**
     * Test of isSingleton method, of class ContactBeanFactory.
     */
    @Test
    public void testIsSingleton() {
        System.out.println("isSingleton test");
        boolean expResult = false;
        boolean result = instance.isSingleton();
        assertEquals(expResult, result);
        System.out.println("testIsSingleton test is passed");
    }

}
