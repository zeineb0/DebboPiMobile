/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.conge;
import com.mycompany.myapp.services.RhService;
import java.util.Date;
import java.io.IOException;

/**
 *
 * @author ASUS X550V
 */
public class ModifiercongeForm extends Form {
    public ModifiercongeForm(Form previous,conge c){
        setTitle("Modifier conge");
        setLayout(BoxLayout.y());
        ComboBox<String> cb=new ComboBox<>();
        cb.addItem("Demande de conge");
        cb.addItem("Demande de Sortie");
        cb.setSelectCommandText(c.getType());
        TextField raison = new TextField("","Raison");
        raison.setText(c.getRaison());
        Picker cdatearr =new Picker();
        cdatearr.setDate(c.getDatearrive());
        Picker cdatesortie =new Picker();
        cdatesortie.setDate(c.getDatesortie());
        Button btnvalider= new Button("Modifier");
        int id= c.getId();
        System.out.println(id);
        btnvalider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    conge cc = new conge(cdatesortie.getDate(),cdatearr.getDate(),cb.getSelectedItem().toString(),raison.getText());
                    if (RhService.getInstance().modifierconge(cc,id)){
                        new ListcongeForm(previous).show();
                    }
                    
                }
                catch(Exception e) {
                   
                }
            }
        });
        addAll(cb,raison,cdatearr,cdatesortie,btnvalider);
    }
}
