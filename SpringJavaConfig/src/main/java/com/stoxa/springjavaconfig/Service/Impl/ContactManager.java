/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springjavaconfig.Service.Impl;

import com.stoxa.springjavaconfig.DAO.ContactDAO;
import com.stoxa.springjavaconfig.EventListener.ClearEvent;
import com.stoxa.springjavaconfig.Model.Contact;
import com.stoxa.springjavaconfig.Entity.MappedContact;
import com.stoxa.springjavaconfig.Service.ContactService;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.PostConstruct;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ksu
 */

@Service ("contactManager")
public class ContactManager implements ContactService, ApplicationEventPublisherAware {

    @Autowired
    private ContactDAO dao;
    
    @Value("${maxSize}")
    private int maxContactBookSize;
    private int contactsNumber;
    private ApplicationEventPublisher publisher;
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ContactManager.class);

    public void setApplicationEventPublisher(ApplicationEventPublisher publisher){
        this.publisher = publisher;
    }
    

    @Override
    @Transactional
    public void addContact(Contact contact) {
        dao.insertContact(new MappedContact(contact));
    }
    
    @Override
    @Transactional
    public void updateContact(Contact contact) {
        dao.updateContact(new MappedContact(contact));
    }
 
    @Override
    @Transactional
    public void deleteContact(Contact contact) {
        publisher.publishEvent(new ClearEvent(this, contact));
        dao.deleteContact(new MappedContact(contact));
    }
 
    @Override
    @Transactional
    public Contact getContact(String phone) {
        return new Contact(dao.selectContact(phone));
    }
    
    @Override
    @Transactional
    public Contact getContact(int id) {
        return new Contact(dao.selectContact(id));
    }
    
     @Override
    @Transactional
    public Collection <Contact> getAllContacts() {
         Collection<MappedContact> mappedContacts = dao.selectAllContacts();
         Collection<Contact> contacts = new ArrayList<Contact>(mappedContacts.size());
        for (MappedContact mappedContact : mappedContacts) {
            Contact contact = null;
            contact = new Contact(mappedContact);
            contacts.add(contact);
        }
        return contacts;
    }

     @Override
    @Transactional
    public void clearAll() {
        dao.clearAll();
    }
    
    
    @Transactional
    public void clear() {
        Contact contact = new Contact (dao.selectContact(contactsNumber-1));
            publisher.publishEvent(new ClearEvent(this, contact));
            dao.selectAllContacts().remove(contact);
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
