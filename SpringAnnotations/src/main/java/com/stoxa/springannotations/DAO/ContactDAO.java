/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springannotations.DAO;

import com.stoxa.springannotations.Model.Contact;
import java.util.List;

/**
 *
 * @author ksu
 */
public interface ContactDAO {
    public void addContact(Contact contact);
    public void updateContact(Contact contact);
    public void deleteContact(Contact contact);
    public Contact getContact(String phone);
    public List<Contact> getAllContacts();
    void clearAll();
    
}
