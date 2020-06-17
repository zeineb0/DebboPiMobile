/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class CatégorieHomeForm extends Form{

   Form current;
    public CatégorieHomeForm(Form previous) {
        current=this;
        setTitle("Catégorie");
        setLayout(BoxLayout.y());
        
        add(new Label("Choisissez une option"));
        Button btnAddTask = new Button("Ajouter une nouvelle catégorie");
        Button btnListTasks = new Button("Afficher la liste des catégorie");
        
        btnAddTask.addActionListener(e-> new AddCategorieForm(current).show());
        btnListTasks.addActionListener(e-> new ListCategorieForm(current).show());
        addAll(btnAddTask,btnListTasks);
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

    }
 
    
    
}
