/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springjavaconfig.EventListener;

import org.springframework.context.ApplicationListener;

/**
 *
 * @author stoxa
 */
public class DeleteContactListener implements ApplicationListener<ClearEvent> {

    @Override
    public void onApplicationEvent(ClearEvent event) {
        System.out.println("Контакт " + event.toString() + " был удалён");
    }
    
}
