/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.gererentrepot;

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
import com.mycompany.myapp.services.gererentrepot.EntrepotServices;
import com.mycompany.myapp.entities.User;

/**
 *
 * @author asus
 */
public class ModifierForm  extends Form{
        public ModifierForm(Form previous, Entrepot ent, User u) {
    
setTitle("Modifier l'entrepot");
        setLayout(BoxLayout.y());
        
        TextField entrep= new TextField("", "Entreprise");
        entrep.setText(ent.getEntreprise());
        int id = ent.getId_entrepot();
            System.err.println(id);
        ComboBox<String> etat=new ComboBox<>();
        etat.addItem("Loué");
        etat.addItem("A Louer");
        etat.addItem("Libre");
        etat.setSelectCommandText(ent.getEtat());
        TextField prix= new TextField("", "prix de location");
        float pr=ent.getPrix_location();
        
prix.setText(String.valueOf(pr));
        Button btnValider = new Button("Modifier");
        
        btnValider.addActionListener(new ActionListener() {
          

            @Override
            public void actionPerformed(ActionEvent evt) {
  if ((entrep.getText().length()==0)||(etat.getSelectedItem().length()==0))
                    Dialog.show("Alerte", "Veuillez remplir tous les champs", new Command("OK"));
                else
                {
                    try {
                        
                       Entrepot a = new Entrepot(etat.getSelectedItem().toString(), entrep.getText(),Float.parseFloat(prix.getText()));
                        if( EntrepotServices.getInstance().modifierEntrepot(id, a))
                            Dialog.show("Modification","Veuillez confirmer la modification",new Command("OK"));
                        else
                            Dialog.show("Modification", "Vous avez annuler la modification", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                            }
        });
        
        addAll(entrep,etat,prix,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->new DetailsForm(previous, id, ent, u).showBack());
            }         
 
    
}
