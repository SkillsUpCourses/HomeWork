/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springjavaconfig.Model;

import com.stoxa.springjavaconfig.Entity.MappedContact;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Collator;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;


/**
 *
 * @author ksu
 */

public class Contact implements Comparable {
    
  
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Set <Hobby> hobbies= new HashSet<Hobby>();
   
    public Contact() {
    }
 
    public Contact(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }
    

    
    public Contact(ResultSet result) throws SQLException {
        this.setFirstName(result.getString(1));
        this.setLastName(result.getString(2));
        this.setPhone(result.getString(3));
        this.setEmail(result.getString(4));
    }
    
    public Contact(MappedContact mappedContact) {
        this.setFirstName(mappedContact.getFirstName());
        this.setLastName(mappedContact.getLastName());
        this.setPhone(mappedContact.getPhone());
        this.setEmail(mappedContact.getEmail());
    }
 
    public String getFirstName() {
        return firstName;
    }
 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
    public String getLastName() {
        return lastName;
    }
 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
 
    public String getPhone() {
        return phone;
    }
 
    public void setPhone(String phone) {
        this.phone = phone;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Set <Hobby> getHobbies() {
        return hobbies;
    }
 
    public void setHobbies(Set <Hobby> hobbies) {
        this.hobbies = hobbies;
    }
 
    @Override
    public String toString() {
        return "Contact {firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone + ", email=" + email + "}";
    }

    @Override
    public int compareTo(Object o) {
        Collator c = Collator.getInstance(new Locale("ru"));
        c.setStrength(Collator.PRIMARY);
        return c.compare(this.toString(), o.toString());
    }
    
    @Override
    public boolean equals(Object obj) {
        return (this.toString().equalsIgnoreCase(obj.toString()))? true : false;  
    }
    

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
    
}