/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springjavaconfig.DAO;

import com.stoxa.springjavaconfig.Model.Contact;
import java.util.Collection;

/**
 *
 * @author ksu
 */
public interface ContactDAO {
    public void addContact(Contact contact);
    public void updateContact(Contact contact);
    public void deleteContact(Contact contact);
    public Contact getContact(String phone);
    public Contact getContact(int number);
    public Collection<Contact> getAllContacts();
    void clearAll();
    
}
