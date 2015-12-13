/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.SpringXML.Service;

import java.util.List;
import com.stoxa.SpringXML.Model.Contact;
import com.stoxa.SpringXML.DAO.ContactDAO;

/**
 *
 * @author ksu
 */
public class ContactManager implements ContactService {

    private ContactDAO dao;
    private int maxContactBookSize;
    private int contactsNumber;
    
    public void init() {
        this.contactsNumber=dao.getAllContacts().size();
        if (contactsNumber>=maxContactBookSize) {
        clear();    
        }
    }
    

    @Override
    public void addContact(Contact contact) {
        dao.addContact(contact);
    }
    
    public void updateContact(Contact contact) {
        dao.updateContact(contact);
    }
 
    @Override
    public void deleteContact(Contact contact) {
        dao.deleteContact(contact);
    }
 
    public Contact getContact(String phone) {
        return dao.getContact(phone);
    }
    
    @Override
    public List<Contact> getAllContacts() {
        return dao.getAllContacts();
    }

    @Override
    public void clearAll() {
        dao.clearAll();
    }
    
    public void clear() {
        dao.getAllContacts().remove(contactsNumber-1);
    }

    /**
     * @return the dao
     */
    public ContactDAO dao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(ContactDAO dao) {
        this.dao = dao;
    }

    /**
     * @return the maxContactBookSize
     */
    public int getMaxContactBookSize() {
        return maxContactBookSize;
    }

    /**
     * @param maxContactBookSize the maxContactBookSize to set
     */
    public void setMaxContactBookSize(int maxContactBookSize) {
        this.maxContactBookSize = maxContactBookSize;
    }
}
