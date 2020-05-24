/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.gererentrepot;

import static com.codename1.io.Log.e;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Entrepot;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.gererentrepot.EntrepotServices;

/**
 *
 * @author asus
 */
public class AddEntrepot extends Form{    
    public AddEntrepot(Form previous) {
        setTitle("Ajouter un nouveau entrepot");
        setLayout(BoxLayout.y());
        
        TextField num = new TextField("","Numero fiscale");
        TextField quant= new TextField("", "Quantite max");
        TextField entrep= new TextField("", "Entreprise");
        TextField adresse= new TextField("", "adresse");
        
        
        ComboBox<String> etat=new ComboBox<>();
        etat.addItem("Loué");
        etat.addItem("A Louer");
        etat.addItem("Libre");
        TextField prix= new TextField("", "prix de location");

        Button btnValider = new Button("Add task");
        
        btnValider.addActionListener(new ActionListener() {
          

            @Override
            public void actionPerformed(ActionEvent evt) {
  if ((num.getText().length()==0)||(quant.getText().length()==0)||(entrep.getText().length()==0)||(adresse.getText().length()==0)||(etat.getSelectedItem().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        User user=new User(5);
                        
                       Entrepot e = new Entrepot(adresse.getText(),Integer.parseInt(num.getText()),Integer.parseInt(quant.getText()),etat.getSelectedItem().toString(), entrep.getText(),Float.parseFloat(prix.getText()),user);
                        if( EntrepotServices.getInstance().addEntrepot(e))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                            }
        });
        
        addAll(num,quant,entrep,adresse,etat,prix,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
            }         
 
    
}


