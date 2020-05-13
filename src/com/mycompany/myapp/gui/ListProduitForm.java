/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.mycompany.myapp.services.CategoriesService;
import com.mycompany.myapp.services.ProduitService;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class ListProduitForm extends Form{

    public ListProduitForm(Form previous) {
        setTitle("Liste des produits");
        
        SpanLabel sp = new SpanLabel();
        sp.setText(ProduitService.getInstance().getAllProduit().toString());
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    //public static void add(Container c){};
}