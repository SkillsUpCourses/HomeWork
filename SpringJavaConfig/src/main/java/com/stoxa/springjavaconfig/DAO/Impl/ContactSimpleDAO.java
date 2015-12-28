/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springjavaconfig.DAO.Impl;

import com.stoxa.springjavaconfig.DAO.ContactDAO1;
import com.stoxa.springjavaconfig.Model.Contact;
import java.util.Collection;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ksu
 */

@Repository
public class ContactSimpleDAO implements ContactDAO1{

    private Map <String,Contact> contacts;
    
    @Override
    public void insertContact(Contact contact) {
        contacts.put(contact.getPhone(), contact);
    }
 
    @Override
    public void updateContact(Contact contact) {
        Contact oldContact = selectContact(contact.getPhone());
        if(oldContact != null) {
            oldContact.setFirstName(contact.getFirstName());
            oldContact.setLastName(contact.getLastName());
            oldContact.setPhone(contact.getPhone());
            oldContact.setEmail(contact.getEmail());
        }
    }
 
    @Override
    public void deleteContact(Contact contact) {
        contacts.remove(contact.getPhone(), contact);
    }
 
    @Override
    public Contact selectContact(String phone) {
        return contacts.get(phone);
    }
    
    @Override
    public Collection <Contact> selectAllContacts() {
        return contacts.values();
    }

    @Override
    public void clearAll() {
        contacts.clear();
        System.out.println("Все контакты удалены");
    }

    /**
     * @param contacts the contacts to set
     */
    public void setContacts(Map<String,Contact> contacts) {
        this.contacts = contacts;
    }
    
    public Contact selectContact(int number) {
        int i=0;
        for (Map.Entry<String, Contact> entry : contacts.entrySet()){
            if(i==number) {
                return entry.getValue();
            }
            i++;
        }
        throw new NullPointerException();  
    } 
}

