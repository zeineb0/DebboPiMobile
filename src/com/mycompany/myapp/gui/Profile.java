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
import com.mycompany.myapp.gui.commande.CommandeForm;
import com.mycompany.myapp.gui.gererentrepot.EntrepotALouerForm;
import com.mycompany.myapp.gui.gererentrepot.ListEntrepot;
import com.mycompany.myapp.gui.gererentrepot.LocationListe;
import com.mycompany.myapp.transporteur.gui.HomeContratFrom;
import com.mycompany.myapp.transporteur.gui.HomeTransporteurForm;
import com.mycompany.myapp.transporteur.gui.map;
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
          
         if(u.getRole().equals("Client")){
              getToolbar().addCommandToLeftSideMenu("commande",  null , (evt) -> {
          
           new CommandeForm(profile).show();
        
       });  
                   getToolbar().addCommandToLeftSideMenu("location",  null , (evt) -> {
          
           new EntrepotALouerForm(profile, u).show();
        
       });  
         } 
         else if(u.getRole().equals("Fournisseur")){
              getToolbar().addCommandToLeftSideMenu("Stock",  null , (evt) -> {
          
           new StockHomeForm(profile).show();
        
       });  
                   getToolbar().addCommandToLeftSideMenu("Contrat",  null , (evt) -> {
                       new HomeContratFrom(profile).show();
       
       });  
                   getToolbar().addCommandToLeftSideMenu("Entrepot",  null , (evt) -> {
                       new ListEntrepot(profile, u).show();
       
       });  
         } 
       else
              getToolbar().addCommandToLeftSideMenu("Livraison",  null , (evt) -> {
          
           new HomeTransporteurForm(profile).show();
        
       });  
                   getToolbar().addCommandToLeftSideMenu("Emplacement",  null , (evt) -> {
          
           new map(profile).show();
        
       });  
         } 
    }

