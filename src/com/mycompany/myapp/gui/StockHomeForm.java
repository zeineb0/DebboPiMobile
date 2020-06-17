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
    public StockHomeForm(Form previous) {
        current=this;
        setTitle("Gestion de stock");
        setLayout(BoxLayout.y());
        
        add(new Label("Choisissez une option"));
        Button btnp = new Button("Ajouter un produit");
        Button btnc = new Button("Ajouter une catégorie");
        Button btnm = new Button("Ajouter un mouvement");
        
        btnc.addActionListener(e-> new CatégorieHomeForm(current).show());
        btnp.addActionListener(e-> new ProduitHomeForm(current).show());
        btnm.addActionListener(e-> new MvtHomeForm(current).show());
        addAll(btnc,btnp,btnm);
        
    }
    
}
