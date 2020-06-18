/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.rh;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.mycompany.myapp.services.rh.RhService;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.table.TableLayout;
import com.mycompany.myapp.entities.conge;
import com.mycompany.myapp.gui.ModifiercongeForm;
import com.mycompany.myapp.utils.Statics;

/**
 *
 * @author ASUS X550V
 */
public class ListcongeForm extends Form {

Form current;
    public ListcongeForm(Form previous) {
        current = this;
        setTitle("Liste des conges");
        this.setLayout( new TableLayout(CENTER,2));
       
        for(conge c: RhService.getInstance().getAllconges()){
       Container cx = new Container(new TableLayout(1,1));
       cx.setWidth(CENTER);
            Label l1= new Label("Date sortie "+Statics.simpleDateFormat.format(c.getDatesortie()));
            Label l2= new Label("Date arrive"+Statics.simpleDateFormat.format(c.getDatearrive()));
            Label l3= new Label("type "+c.getId());
            Label l4= new Label("raison "+c.getRaison());
            Label l5= new Label("etat "+c.getEtat());
            Button bt =new Button("Modifier");
            bt.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
               new ModifiercongeForm(previous,c).show();
           }
       });
           
        cx.add(l1);
        cx.add(l2);
        cx.add(l3);
        cx.add(l4);
        cx.add(l5);
        cx.add(bt);
        this.add(cx);
          
          cx.getUnselectedStyle().setMargin(10, 10, 10, 10);
           cx.getUnselectedStyle().setBorder(Border.createLineBorder(2, 0x000000)); 
            cx.getUnselectedStyle().setPadding(10, 10, 10, 10);
             cx.getUnselectedStyle().setBgColor(0xffffff);
          
          this.refreshTheme(); 
       
 
    }       
        
    
}
}
