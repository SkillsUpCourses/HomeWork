/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springjavaconfig.Servlets;

import com.stoxa.springjavaconfig.Model.Contact;
import com.stoxa.springjavaconfig.Service.Impl.ContactManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ksu
 */
public class GetAllContact extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text / html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        pw.println("<B> Список всех контактов</B>");
        pw.println("<table border = 1>");
        ContactManager manager = new ContactManager();
        Collection<Contact> contacts = manager.getAllContacts();
        for (Contact contact : contacts) {
            pw.println("<tr>");
            pw.println("<td>" + contact.getFirstName() + "</td>");
            pw.println("<td>" + contact.getLastName() + "</td>");
            pw.println("<td>" + contact.getPhone() + "</td>");
            pw.println("<td>" + contact.getEmail() + "</td>");
            pw.println("<td>" + contact.getHobbies() + "</td>");
        }
        pw.println("</table>");
    }
}
