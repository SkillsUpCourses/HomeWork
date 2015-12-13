/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springannotations.Service;

import com.stoxa.springannotations.Service.ContactService;
import com.stoxa.springannotations.DAO.ContactDAO;
import com.stoxa.springannotations.Model.Contact;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

/**
 *
 * @author ksu
 */

@Service
@PropertySource("classpath:ContactBookMaximumSize.properties")
public class ContactManager implements ContactService {
    
    
    private int contactsNumber;
    @Autowired
    private ContactDAO dao;
    @Value("${maxSize}")
    private int maxContactBookSize;
    
    
    
    @PostConstruct
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
}
    
