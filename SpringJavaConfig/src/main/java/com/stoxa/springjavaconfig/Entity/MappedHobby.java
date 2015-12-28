/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springjavaconfig.Entity;

import com.stoxa.springjavaconfig.Model.*;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author ksu
 */

@Entity
@Table(name="hobbies")
public class MappedHobby {
    
    @Id  
    @Column(name = "HOBBY_ID")  
    @GeneratedValue  
    private long hobbyID;
    
    @Column(name="HOBBY_NAME", length=100, nullable=false)
    private String hobbyName;
    
    @Column(name="HOBBY_TYPE", length=100, nullable=false)
    private HobbyType hobbyType;
    
    @ManyToMany(mappedBy = "hobbies")  
    private Set <MappedContact> addictedContacts = new HashSet<MappedContact>();
    
    public String getHobbyName() {
        return hobbyName;
    }
    
    public void setHobbyName (String hobbyName){
        this.hobbyName = hobbyName;
    }
     public HobbyType getHobbyType() {
        return hobbyType;
    }
    
    public void setHobbyType (HobbyType hobbyType){
        this.hobbyType = hobbyType;
    }
}
