/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.User;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class Profile extends Form {
   
    Form profile;
    public Profile (User u){
   
        profile = this;
        setTitle("Profile");
        setLayout(BoxLayout.y());
         Style loginStyle = getAllStyles();
        loginStyle.setBgColor(0xDCDCDC);
        
        //***********************  LOGO
        
        ImageViewer  Logo = null ;
                try {
          Logo = new ImageViewer(Image.createImage("/logo.png").scaled(500, 500));
        } catch (IOException ex) {
        }
        
        
        
        
        //************************** TOOLBAR ******************************
        
                        getToolbar().addCommandToOverflowMenu("LogOut",
                null, ev -> {
                    new Login().show();
                });
                
                  getToolbar().addCommandToOverflowMenu("Edit Profile",
                null, ev -> {
                    new EditProfile(profile,u).show();
                });     
        //******************** LABELS
         Label nomL = new Label("Nom :  "+u.getNom());    
         
         nomL.getAllStyles().setFgColor(0x000000);
         Label prenomL = new Label("Prénom :  "+u.getPrenom()); 
         prenomL.getAllStyles().setFgColor(0x000000);
         Label usernameL = new Label("Username :  "+u.getUsername()); 
         usernameL.getAllStyles().setFgColor(0x000000);
         Label emailL = new Label("Email :  "+u.getEmail());  
         emailL.getAllStyles().setFgColor(0x000000);
         Label cinL = new Label("CIN :  "+u.getCin());
         cinL.getAllStyles().setFgColor(0x000000);
         Label telL = new Label("Numéro de Téléphone :  "+u.getTel());
         telL.getAllStyles().setFgColor(0x000000);
         
         addAll(Logo,usernameL,nomL,prenomL,emailL,cinL,telL);
                  
                  
                  
    }
}
