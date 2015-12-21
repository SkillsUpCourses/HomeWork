/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springjavaconfig.DAO;

import com.stoxa.springjavaconfig.Hibernate.HibernateUtil;
import com.stoxa.springjavaconfig.Model.Contact;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author ksu
 */
public class ContactHibernateDAO implements ContactDAO {

    @Override
    public void addContact(Contact contact) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(contact);
        session.getTransaction().commit();
    }

    @Override
    public void updateContact(Contact contact) {
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
    public void deleteContact(Contact contact) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(contact);
        session.flush();
        session.getTransaction().commit();
    }

    @Override
    public Contact getContact(String phone) {
        Contact result;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Contact where phone=:phone");
        query.setParameter("phone", phone);
        result = (Contact) query.uniqueResult();
        session.getTransaction().commit();
        return result;
    }

    @Override
    public List<Contact> getAllContacts() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Contact> result = session.createQuery("from Contact order by firstName").list();
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
    public Contact getContact(int number) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Contact where id=:id");
        query.setInteger("id", number);
        Contact result = (Contact) query.uniqueResult();
        session.getTransaction().commit();
        return result;
    }
}
