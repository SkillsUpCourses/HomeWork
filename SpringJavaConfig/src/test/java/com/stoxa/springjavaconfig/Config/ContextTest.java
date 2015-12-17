/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springjavaconfig.Config;

import com.stoxa.springjavaconfig.Factory.ContactBeanFactory;
import com.stoxa.springjavaconfig.Model.Contact;
import java.lang.reflect.Field;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author ksu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class ContextTest {
    
    @Autowired
    ContactBeanFactory factory;
    
    
    @Rule
    public final ExpectedException thrown = ExpectedException.none(); 
    
    
    @Test
    public void springContextTest() throws Exception {
        System.out.println("Spring context test");
        thrown.expect(NullPointerException.class);
        Field field = factory.getClass().getDeclaredField("contactCount");
        field.setAccessible(true);
        field.setInt(factory, 11);
        Contact result = factory.getObject();
        System.out.println("Spring context test is passed");
    }
    
}
