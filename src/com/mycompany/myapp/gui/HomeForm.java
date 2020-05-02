/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class HomeForm extends Form{
    Form current;
    public HomeForm() {
        current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choisissez une option"));
        Button btnAddTask = new Button("Ajouter une categorie");
        Button btnListTasks = new Button("Afficher toutes les categories");
        
        btnAddTask.addActionListener(e-> new AddCategorieForm(current).show());
        btnListTasks.addActionListener(e-> new ListCategorieForm(current).show());
        addAll(btnAddTask,btnListTasks);
        
        
    }
    
}
