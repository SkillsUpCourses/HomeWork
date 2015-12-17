/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springjavaconfig.Service;

import com.stoxa.springjavaconfig.DAO.ContactDAO;
import com.stoxa.springjavaconfig.EventListener.ClearEvent;
import com.stoxa.springjavaconfig.Model.Contact;
import java.util.Collection;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 *
 * @author ksu
 */
public class ContactManager implements ContactService, ApplicationEventPublisherAware {

    private ContactDAO dao;
    private int maxContactBookSize;
    private int contactsNumber;
    private ApplicationEventPublisher publisher;

    public void setApplicationEventPublisher(ApplicationEventPublisher publisher){
        this.publisher = publisher;
    }
    
    public void init() {
        this.contactsNumber=dao.getAllContacts().size();
        if (contactsNumber>=maxContactBookSize) {
        clear();  
            System.err.println("Почищена книга контактов");
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
        publisher.publishEvent(new ClearEvent(this, contact));
        dao.deleteContact(contact);
    }
 
    public Contact getContact(String phone) {
        return dao.getContact(phone);
    }
    
    @Override
    public Collection <Contact> getAllContacts() {
        return dao.getAllContacts();
    }

    @Override
    public void clearAll() {
        dao.clearAll();
    }
    
    public void clear() {
        publisher.publishEvent(new ClearEvent(this, dao.getContact(contactsNumber-1)));
        dao.getAllContacts().remove(dao.getContact(contactsNumber-1));
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
