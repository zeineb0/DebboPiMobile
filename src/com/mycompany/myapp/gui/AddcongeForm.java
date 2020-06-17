/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Employe;
import com.mycompany.myapp.entities.conge;
import com.mycompany.myapp.services.RhService;
import java.io.IOException;
import java.util.Date;




/**
 *
 * @author ASUS X550V
 */
public class AddcongeForm extends Form{
    String ch="Conge";
    public AddcongeForm(Form previous){
        setTitle("Ajouter une conge");
        setLayout(BoxLayout.y());      

        Container c1=new Container(BoxLayout.x());
//        Picker datePicker = new Picker();
//        datePicker.setType(Display.PICKER_TYPE_DATE);                
//        TextField cdatearr =new TextField("","DateArrive");
//        Container c2=new Container(BoxLayout.x());
//        TextField cdatesortie =new TextField("","DateSortie");
//        Container c3=new Container(BoxLayout.x());
        Picker cdatearr =new Picker();
        Container c2=new Container(BoxLayout.x());
        Picker cdatesortie =new Picker();
        Container c3=new Container(BoxLayout.x());
        TextField craison =new TextField("","craison");      
        Container c4=new Container(BoxLayout.x());
        TextField cetat =new TextField("","En Attente d'approbation");
        Container c5=new Container(BoxLayout.x());
        TextField cemp =new TextField("","cemp");
        ComboBox<String> cb=new ComboBox<>();
        cb.addItem("Demande de conge");
        cb.addItem("Demande de Sortie");
        cb.addActionListener((evt) -> {
            if (cb.getSelectedItem().toString().equals("Demande de Sortie")){            
                 ch="Sortie";
                }

            
            });
        Button btnValider = new Button("Ajouter un conge");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 Date  d1=cdatearr.getDate();
                 Date  d2=cdatesortie.getDate();
                 long diff = (d2.getTime()-d1.getTime());
                 long d=diff /(1000*60*60*24);
                 if(d<=0){
                      Dialog.show("Erreur","fill date fields correctly",new Command("ok"));
                 }
                  if (cb.getSelectedItem().toString().equals("Demande de Sortie")){
                      if (d1.getTime()!=d2.getTime()){
                          Dialog.show("Erreur","Veuillez demander un sortie dans le mm jours",new Command("ok"));
                      }
                  }
                try {
                conge c = new conge(cdatearr.getDate(),cdatesortie.getDate(),ch,cetat.getText(),craison.getText(),new Employe(Integer.parseInt(cemp.getText())));
                conge test=new RhService().addconge(c,d);
                test.toString();
                if (test.getFK_id_emp().getNbcong()<14){
                    Dialog.show("Succes","ConnectionSucc",new Command("ok"));
                }
                else if (test.getFK_id_emp().getNbcong()>=14){
                    Dialog.show("Refusé","Désole Vous avez déja consommée votre congé annuel(14 jours)",new Command("ok"));
                    System.out.println(test.getFK_id_emp().getNom());
                }
                else{
                    Dialog.show("Error","Erreur ajout survenue",new Command("ok"));
                }
                }
                catch(Exception e){
                    Dialog.show("Error","Erreur ajout",new Command("ok"));
                }
                }

      
        });
        c1.add(new Label("Date Arrive :"));
        c1.add(cdatearr);
        c2.add(new Label("Date Sortie :"));
        c2.add(cdatesortie);
        c3.add(new Label("Raison :"));
        c3.add(craison);
        c4.add(new Label("Etat :"));
        c4.add(cetat);
        c5.add(new Label("identifiant employe:"));
        c5.add(cemp);
        addAll(c1,c2,cb,c4,c5,c3,btnValider);
    }
    
    
}
