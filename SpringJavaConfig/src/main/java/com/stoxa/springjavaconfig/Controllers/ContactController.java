/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springjavaconfig.Controllers;

import com.stoxa.springjavaconfig.Model.Contact;
import com.stoxa.springjavaconfig.Service.ContactService;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ksu
 */
@RequestMapping(value = "/nazviazku")
@Controller
public class ContactController {
    
    @Autowired
    private ContactService contactService;
    
    @RequestMapping("/register")  
    public ModelAndView getRegisterForm(@ModelAttribute("user") Contact contact,  
            BindingResult result) {  

        System.out.println("Register Form");  
        return new ModelAndView("RegistredForm");  
    }  
    
    @RequestMapping(value = "/addcontact", method = RequestMethod.POST)
    @ResponseBody
    public String addContact(@RequestBody Contact contact) {
        contactService.addContact(contact);
        return "profile";
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getAllContacts() {
        Map<String, Object> model = new HashMap<String, Object>();  
        model.put("user", contactService.getAllContacts());  
        return new ModelAndView("allContacts", model);  
    }

    @RequestMapping(value = "/deletecontact/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void deleteContact(@PathVariable(value = "id") int id) {
        Contact contact = contactService.getContact(id);
        contactService.deleteContact(contact);
    }
    
    @RequestMapping(value = "/getcontact/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void getContact(@PathVariable(value = "id") int id) {
         contactService.getContact(id);
    }

}

