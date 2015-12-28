/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springjavaconfig.DAO.Impl;

import com.stoxa.springjavaconfig.DAO.ContactDAO;
import com.stoxa.springjavaconfig.Entity.MappedContact;
import com.stoxa.springjavaconfig.Model.Contact;
import com.stoxa.springjavaconfig.Model.ContactMapper;
import java.util.Collection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ksu
 */


@Repository
public class ContactJDBCTemplateDao implements ContactDAO {

    private String SQL;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertContact(MappedContact contact) {
        SQL = "INSERT INTO `mydb`.`contacts` (`CONTACT_NAME`,"
                + "`CONTACT_SURNAME`,`CONTACT_PHONE`, `CONTACT_EMAIL`)"
                + "VALUES (?, ?, ?, ?);";

        jdbcTemplate.update(SQL, contact.getFirstName(), contact.getLastName(), contact.getPhone(), contact.getEmail());
        return;
    }

    @Override
    public void updateContact(MappedContact contact) {
        SQL = "UPDATE `mydb`.`contacts`"
                + "SET `CONTACT_NAME` = ?, `CONTACT_SURNAME` = ?, "
                + "`CONTACT_PHONE` = ?, `CONTACT_EMAIL` = ? WHERE `CONTACT_PHONE` = ?;";
        jdbcTemplate.update(SQL, contact.getFirstName(), contact.getLastName(), contact.getPhone(), contact.getEmail(), contact.getPhone());
        return;
    }

    @Override
    public void deleteContact(MappedContact contact) {
        SQL = "DELETE FROM `mydb`.`contacts` WHERE `contacts`.`CONTACT_PHONE` = ?;";
        jdbcTemplate.update(SQL, contact.getPhone());
        return;
    }

    @Override
    public MappedContact selectContact(String phone) {
        SQL = "SELECT `contacts`.`CONTACT_NAME`, `contacts`.`CONTACT_SURNAME`,\n"
                + "`contacts`.`CONTACT_PHONE`, `contacts`.`CONTACT_EMAIL` FROM `mydb`.`contacts` WHERE `contacts`.`CONTACT_PHONE` = ?;";
        MappedContact contact = jdbcTemplate.queryForObject(SQL, new Object[]{phone}, new ContactMapper());
        return contact;
    }

    @Override
    public MappedContact selectContact(int number) {
        SQL = "SELECT `contacts`.`CONTACT_NAME`, `contacts`.`CONTACT_SURNAME`,\n"
                + "`contacts`.`CONTACT_PHONE`, `contacts`.`CONTACT_EMAIL` FROM `mydb`.`contacts` WHERE `contacts`.`ID` = ?;";
        MappedContact contact = jdbcTemplate.queryForObject(SQL, new Object[]{number}, new ContactMapper());
        return contact;
    }

    @Override
    public Collection<MappedContact> selectAllContacts() {
        SQL = "SELECT `contacts`.`CONTACT_NAME`, `contacts`.`CONTACT_SURNAME`,\n"
                + "`contacts`.`CONTACT_PHONE`, `contacts`.`CONTACT_EMAIL` FROM `mydb`.`contacts`;";
        Collection<MappedContact> contacts = jdbcTemplate.query(SQL,
                new ContactMapper());
        return contacts;
    }

    @Override
    public void clearAll() {
        SQL = "DELETE FROM `mydb`.`contacts`;";
        jdbcTemplate.update(SQL);
        return;
    }
}
