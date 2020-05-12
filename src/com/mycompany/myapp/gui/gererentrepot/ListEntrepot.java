/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.gererentrepot;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.mycompany.myapp.services.gererentrepot.EntrepotServices;

/**
 *
 * @author asus
 */
public class ListEntrepot extends Form{

    public ListEntrepot(Form previous) {
        setTitle("List tasks");
        
        SpanLabel sp = new SpanLabel();
        sp.setText(EntrepotServices.getInstance().getAllEntrepots().toString());
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
}
