/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.mycompany.myapp.services.CategoriesService;
import com.mycompany.myapp.services.MvtService;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class ListMvtForm extends Form{

    public ListMvtForm(Form previous) {
        setTitle("Liste des mvts");
        
        SpanLabel sp = new SpanLabel();
        sp.setText(MvtService.getInstance().getAllMvt().toString());
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
}
