/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springjavaconfig.DAO;

import com.stoxa.springjavaconfig.Model.Contact;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author ksu
 */
public class ContactSimpleDAO implements ContactDAO{

    private Map <String,Contact> contacts;
    
    @Override
    public void addContact(Contact contact) {
        contacts.put(contact.getPhone(), contact);
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
        contacts.remove(contact.getPhone(), contact);
    }
 
    @Override
    public Contact getContact(String phone) {
        return contacts.get(phone);
    }
    
    @Override
    public Collection <Contact> getAllContacts() {
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
    
    public Contact getContact(int number) {
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

