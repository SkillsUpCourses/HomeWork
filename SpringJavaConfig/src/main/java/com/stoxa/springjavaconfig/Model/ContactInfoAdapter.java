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
public class ContactInfoAdapter {
    
    private final BasicDBObject dbObject;;
    
    public ContactInfoAdapter (BasicDBObject dbObject) {
        this.dbObject=dbObject;
    }
    
    public Contact adaptToContactInfo () {
        String name = (String) dbObject.get("name");
        String surname = (String) dbObject.get("surname");
        String phoneNumber = (String) dbObject.get("phoneNumber");
        String email = (String) dbObject.get("email");
        return new Contact(name,surname, phoneNumber, email);
    }
}
