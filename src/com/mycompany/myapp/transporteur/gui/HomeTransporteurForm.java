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

/**
 *
 * @author Dell
 */
public class HomeTransporteurForm extends Form{
   
    
    Form current;
    
    public HomeTransporteurForm()
    {
        current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("choose an option"));
        Button btnAddTask = new Button("Add Task");
        Button btnListLiv = new Button("List Livraison");
        
      //  btnAddTask.addActionListener(e->new AddTaskForm(current).show());
        btnListLiv.addActionListener(e->new ListeLivraisonForm(current).show());
        addAll(btnAddTask,btnListLiv);
        
        
        
    }
    
    
    
    
}
