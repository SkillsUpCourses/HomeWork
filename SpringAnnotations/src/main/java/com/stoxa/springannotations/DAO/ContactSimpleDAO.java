/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springannotations.DAO;

import com.stoxa.springannotations.DAO.ContactDAO;
import com.stoxa.springannotations.Model.Contact;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ksu
 */

@Repository
public class ContactSimpleDAO implements ContactDAO{

    @Autowired
    private List<Contact> contacts;
    
    @Override
    public void addContact(Contact contact) {
        contacts.add(contact);
    }
 
    @Override
    public void updateContact(Contact contact) {
        Contact oldContact = getContact(contact.getPhone());
        if(oldContact != null) {
            oldContact.setFirstName(contact.getFirstName());
            oldContact.setLastName(contact.getLastName());
            oldContact.setPhone(contact.getPhone());
            oldContact.setEmail(contact.getEmail());
        }
    }
 
    @Override
    public void deleteContact(Contact contact) {
        for(Iterator<Contact> it = contacts.iterator(); it.hasNext();) {
            Contact cnt = it.next();
            if(cnt.getPhone().equals(contact.getPhone())) {
                it.remove();
            }
        }
    }
 
    @Override
    public Contact getContact(String phone) {
        for(Contact contact : contacts) {
            if(contact.getPhone().equals(phone)) {
                return contact;
            }
        }
        return null;
    }
    
    @Override
    public List<Contact> getAllContacts() {
        return contacts;
    }

    @Override
    public void clearAll() {
        contacts.clear();
        System.out.println("Все контакты удалены");
    }

    /**
     * @param contacts the contacts to set
     */
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
 
}

