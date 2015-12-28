/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springjavaconfig.DAO.Impl;

import com.stoxa.springjavaconfig.DAO.ContactDAO;
import com.stoxa.springjavaconfig.Entity.MappedContact;
import com.stoxa.springjavaconfig.Model.Contact;
import java.util.Collection;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ksu
 */

@Repository
public class ContactJPADAO implements ContactDAO {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly=false)
    public void insertContact(MappedContact contact) {
       em.persist(contact); 
    }

    @Override
    @Transactional(readOnly=false)
    public void updateContact(MappedContact contact) {
        Contact old = em.find(Contact.class, contact.getID());
        old.setFirstName(contact.getFirstName());
        old.setLastName(contact.getLastName());
        old.setPhone(contact.getPhone());
        old.setEmail(contact.getEmail());
    }

    @Override
    @Transactional(readOnly=false)
    public void deleteContact(MappedContact contact) {
        em.remove(contact);
    }

    @Override
    @Transactional(readOnly=true)
    public MappedContact selectContact(String phone) {
         return em.find(MappedContact.class, phone);
    }

    @Override
    @Transactional(readOnly=true)
    public MappedContact selectContact(int number) {
        return em.find(MappedContact.class, number);
    }

    @Override
    @Transactional(readOnly=true)
    public Collection<MappedContact> selectAllContacts() {
        return em.createQuery("SELECT mappedContact FROM MappedContact mappedContact").getResultList();
    }

    @Override
    @Transactional(readOnly=false)
    public void clearAll() {
        em.clear();
    }
    
}
