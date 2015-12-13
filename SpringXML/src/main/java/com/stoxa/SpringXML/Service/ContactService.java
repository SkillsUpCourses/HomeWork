/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.SpringXML.Service;

import java.util.List;
import com.stoxa.SpringXML.Model.Contact;

/**
 *
 * @author ksu
 */
public interface ContactService {
    void addContact (Contact contact);
    void deleteContact (Contact contact);
    void updateContact(Contact contact);
    Contact getContact(String phone);
    List<Contact> getAllContacts();
    void clearAll();
}