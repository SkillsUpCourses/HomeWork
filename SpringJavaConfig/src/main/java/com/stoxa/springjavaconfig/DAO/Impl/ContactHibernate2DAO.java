/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springjavaconfig.DAO.Impl;


import com.stoxa.springjavaconfig.DAO.ContactDAO;
import com.stoxa.springjavaconfig.Entity.MappedContact;
import java.util.Collection;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author ksu
 */


@Repository
public class ContactHibernate2DAO extends HibernateDaoSupport implements ContactDAO {

    @Override
    @Transactional(readOnly=false)
    public void insertContact(MappedContact contact) {
        getHibernateTemplate().save(contact);
    }

    @Override
    @Transactional(readOnly=false)
    public void updateContact(MappedContact contact) {
        getHibernateTemplate().update(contact);
    }

    @Override
    @Transactional(readOnly=false)
    public void deleteContact(MappedContact contact) {
        getHibernateTemplate().delete(contact);
    }

    @Override
    @Transactional(readOnly=true)
    public MappedContact selectContact(String phone) {
        MappedContact result = (MappedContact) getHibernateTemplate().find("from Contact where phone=?", phone);
        return result;
    }

    @Override
    @Transactional(readOnly=true)
    public MappedContact selectContact(int number) {
        return getHibernateTemplate().get(MappedContact.class, number);
    }

    @Override
    @Transactional(readOnly=true)
    public Collection<MappedContact> selectAllContacts() {
        return (Collection<MappedContact>) getHibernateTemplate().find("from Contact");
    }

    @Override
    @Transactional(readOnly=false)
    public void clearAll() {
        getHibernateTemplate().clear();
    }
    
}
