/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springjavaconfig.Model;

import com.stoxa.springjavaconfig.Entity.MappedContact;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author ksu
 */
public class ContactMapper implements RowMapper<MappedContact>  {

    @Override
    public MappedContact mapRow (ResultSet rs, int rowNum) throws SQLException {
      MappedContact contact = new MappedContact();
      contact.setFirstName(rs.getString("name"));
      contact.setLastName(rs.getString("surname"));
      contact.setPhone(rs.getString("phone"));
      contact.setEmail(rs.getString("email"));
      return contact;
    }
    
}
