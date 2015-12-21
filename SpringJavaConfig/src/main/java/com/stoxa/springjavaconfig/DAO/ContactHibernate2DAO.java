/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springjavaconfig.DAO;


import com.stoxa.springjavaconfig.Model.Contact;
import java.util.Collection;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author ksu
 */
public class ContactHibernate2DAO extends HibernateDaoSupport implements ContactDAO {

    @Override
    @Transactional(readOnly=false)
    public void addContact(Contact contact) {
        getHibernateTemplate().save(contact);
    }

    @Override
    @Transactional(readOnly=false)
    public void updateContact(Contact contact) {
        getHibernateTemplate().update(contact);
    }

    @Override
    @Transactional(readOnly=false)
    public void deleteContact(Contact contact) {
        getHibernateTemplate().delete(contact);
    }

    @Override
    @Transactional(readOnly=true)
    public Contact getContact(String phone) {
        Contact result = (Contact) getHibernateTemplate().find("from Contact where phone=?", phone);
        return result;
    }

    @Override
    @Transactional(readOnly=true)
    public Contact getContact(int number) {
        return getHibernateTemplate().get(Contact.class, number);
    }

    @Override
    @Transactional(readOnly=true)
    public Collection<Contact> getAllContacts() {
        return (Collection<Contact>) getHibernateTemplate().find("from Contact");
    }

    @Override
    @Transactional(readOnly=false)
    public void clearAll() {
        getHibernateTemplate().clear();
    }
    
}
