/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springjavaconfig.DAO;

import com.stoxa.springjavaconfig.Model.Contact;
import java.util.Collection;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.stoxa.springjavaconfig.Model.BasicDBObjectAdapter;
import com.stoxa.springjavaconfig.Model.ContactInfoAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ksu
 */
public class ContactMongoDAO implements ContactDAO {

    private final MongoClient mongoClient;
    private final DB db;
    public final DBCollection table;
    private BasicDBObject searchQuery;

    public ContactMongoDAO() {
        mongoClient = new MongoClient(new ServerAddress("localhost", 27017));
        db = mongoClient.getDB("SkillsUpProgect");
        System.out.println("Connect to database successfully");
        table = db.getCollection("contacts");
    }

    @Override
    public void addContact(Contact contact) {
        BasicDBObjectAdapter adapter = new BasicDBObjectAdapter(contact);
        BasicDBObject document = adapter.adaptToBasicDBObject();
        table.save(document);
    }

    @Override
    public void updateContact(Contact contact) {
        BasicDBObjectAdapter newAdapter = new BasicDBObjectAdapter(contact);
        BasicDBObject newCont = newAdapter.adaptToBasicDBObject();
        searchQuery = new BasicDBObject();
        searchQuery.put("phoneNumber", contact.getPhone());
        BasicDBObject oldCont = (BasicDBObject) table.findOne(searchQuery);
        table.findAndModify(oldCont, newCont);
    }

    @Override
    public void deleteContact(Contact contact) {
        searchQuery = new BasicDBObject();
        searchQuery.put("phoneNumber", contact.getPhone());
        DBObject findOne = table.findOne(searchQuery);
        table.remove(findOne);
    }

    @Override
    public Contact getContact(String phone) {
        ContactInfoAdapter adapter;
        searchQuery = new BasicDBObject();
        searchQuery.put("phoneNumber", phone);
        DBObject findOne = table.findOne(searchQuery);
        adapter = new ContactInfoAdapter((BasicDBObject) findOne);
        return adapter.adaptToContactInfo();
    }

    @Override
    public Contact getContact(int number) {
        ContactInfoAdapter adapter;
        DBCursor cursor = table.find();
        int i = 0;
        if (cursor.hasNext()) {
            if (i == number) {
                adapter = new ContactInfoAdapter((BasicDBObject) cursor.curr());
                return adapter.adaptToContactInfo();
            }
            cursor.next();
            i++;
        }
        throw new NullPointerException("Contact number " + number + " not found");
    }

    @Override
    public Collection<Contact> getAllContacts() {
        ContactInfoAdapter adapter;
        Contact contact;
        Collection<Contact> contacts = new ArrayList<>();
        DBCursor cursor = table.find();
        List<DBObject> DBObjects = cursor.toArray();
        for (DBObject obj : DBObjects) {
            adapter = new ContactInfoAdapter((BasicDBObject) obj);
            contact = adapter.adaptToContactInfo();
            contacts.add(contact);
        }
        cursor.close();
        return contacts;
    }

    @Override
    public void clearAll() {
        table.drop();
    }

}
