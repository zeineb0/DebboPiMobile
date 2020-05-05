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
public class StockHomeForm extends Form{
    Form current;
    public StockHomeForm() {
        current=this;
        setTitle("Gestion de stock");
        setLayout(BoxLayout.y());
        
        add(new Label("Choisissez une option"));
        Button btnAddTask = new Button("Ajouter un produit");
        Button btnListTasks = new Button("Ajouter une catégorie");
        
        btnAddTask.addActionListener(e-> new CatégorieHomeForm(current).show());
        btnListTasks.addActionListener(e-> new ProduitHomeForm(current).show());
        addAll(btnAddTask,btnListTasks);
        
    }
    
}
