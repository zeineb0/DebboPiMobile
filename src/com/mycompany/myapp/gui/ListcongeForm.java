/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.mycompany.myapp.services.RhService;

/**
 *
 * @author ASUS X550V
 */
public class ListcongeForm extends Form {

    public ListcongeForm(Form previous) {
        setTitle("List conges");
        SpanLabel sp =new SpanLabel();
        sp.setText(RhService.getInstance().getAllconges().toString());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
        
    }
    
    
}
