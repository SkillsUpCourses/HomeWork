/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springjavaconfig.DAO.Impl;

import com.stoxa.springjavaconfig.DAO.ContactDAO;
import com.stoxa.springjavaconfig.Entity.MappedContact;
import com.stoxa.springjavaconfig.Hibernate.HibernateUtil;
import com.stoxa.springjavaconfig.Model.Contact;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ksu
 */


@Repository
public class ContactHibernateDAO implements ContactDAO {

    @Override
    public void insertContact(MappedContact contact) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(contact);
        session.getTransaction().commit();
    }

    @Override
    public void updateContact(MappedContact contact) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "UPDATE Contact set firstName = :name, lastName = :surname, phone = :phone, email = :email  "
                + "WHERE phone = :phone";
        Query query = session.createQuery(hql);
        query.setParameter("name", contact.getFirstName());
        query.setParameter("surname", contact.getLastName());
        query.setParameter("phone", contact.getPhone());
        query.setParameter("email", contact.getEmail());
        int result = query.executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void deleteContact(MappedContact contact) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(contact);
        session.flush();
        session.getTransaction().commit();
    }

    @Override
    public MappedContact selectContact(String phone) {
        MappedContact result;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Contact where phone=:phone");
        query.setParameter("phone", phone);
        result = (MappedContact) query.uniqueResult();
        session.getTransaction().commit();
        return result;
    }

    @Override
    public List<MappedContact> selectAllContacts() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<MappedContact> result = session.createQuery("from Contact order by firstName").list();
        session.getTransaction().commit();
        return result;
    }

    @Override
    public void clearAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery("DELETE FROM `mydb`.`contacts`;");
        int result = query.executeUpdate();
        session.flush();
        session.getTransaction().commit();
    }

    @Override
    public MappedContact selectContact(int number) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Contact where id=:id");
        query.setInteger("id", number);
        MappedContact result = (MappedContact) query.uniqueResult();
        session.getTransaction().commit();
        return result;
    }
}
