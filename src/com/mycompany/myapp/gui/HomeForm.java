/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class HomeForm extends Form {
    Form current;
    public HomeForm(){
        current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        Button btnList = new Button("List Employées");
        btnList.addActionListener( e -> new ListcongeForm(current).show());
        add(btnList);
        
    }
     
}
