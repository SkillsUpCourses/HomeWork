/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springjavaconfig.Model;

import com.mongodb.BasicDBObject;


/**
 *
 * @author ksu
 */
public class BasicDBObjectAdapter {

    private Contact contact;

    public BasicDBObjectAdapter(Contact contact) {
        this.contact = contact;
    }

    public BasicDBObject adaptToBasicDBObject() {
        BasicDBObject document = new BasicDBObject();
        document.put("name", contact.getFirstName());
        document.put("surname", contact.getLastName());
        document.put("phoneNumber", contact.getPhone()); 
        document.put("email", contact.getEmail()); 
        return document;
    }
}
