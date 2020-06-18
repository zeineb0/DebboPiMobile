/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.transporteur.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;

/**
 *
 * @author Dell
 */
public class HomeTransporteurForm extends Form{
   
    
    Form current;
    
    public HomeTransporteurForm()
    {
        current=this;
        setTitle("Transporteur Accueil");
        setLayout(BoxLayout.y());
        
        add(new Label(" Vous pouvez choisir consulter :"));
        //Button btnAddTask = new Button("Add Task");
        Button btnListLiv = new Button("La Liste des livraisons livrées");
        Button btnListLivNL = new Button("La Liste des livraisons non livrées");
        
        
      //  btnAddTask.addActionListener(e->new AddTaskForm(current).show());
        btnListLiv.addActionListener(e->new ListeLivraisonForm(current).show());
        btnListLivNL.addActionListener(e-> new ListeLivraisonNLForm(current).show());
       
       
        addAll(btnListLiv,btnListLivNL);
        
        
        
    }
    
    
    
    
}
