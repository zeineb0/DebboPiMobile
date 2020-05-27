/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.gererentrepot;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.table.TableLayout;
import com.mycompany.myapp.entities.Entrepot;
import com.mycompany.myapp.services.gererentrepot.EntrepotServices;

/**
 *
 * @author asus
 */
public class EntrepotLoueForm extends Form {
      Form current;
    public EntrepotLoueForm(Form previous) {
        current = this;
        setTitle("List tasks");
        this.setLayout( new TableLayout(CENTER,3));
       
        for(Entrepot e: EntrepotServices.getInstance().getLoueEntrepots()){
       Container cx = new Container(new TableLayout(1,1));
       cx.setWidth(CENTER);
            Label l1= new Label(""+e.getNum_fiscale());
            Label l2= new Label(""+e.getAdresse_entrepot());
            Label l3= new Label(""+e.getEntreprise());
        cx.add(l1);
        cx.add(l2);
        cx.add(l3);
        this.add(cx);
       
 
    }       
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

    }
    
}
