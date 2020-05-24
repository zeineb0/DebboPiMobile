/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.gererentrepot;

import static com.codename1.io.Log.e;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
public class DetailsForm extends Form{
        public DetailsForm(Form previous, int id, Entrepot ent) {
        setTitle("Entrepot detail");
            
            Entrepot entrepot = EntrepotServices.getInstance().getDetailEntrepots(id);
        
        
      Container cx = new Container(new TableLayout(1,1));
       cx.setWidth(CENTER);
            Label l1= new Label(""+entrepot.getNum_fiscale());
            Label l2= new Label(""+entrepot.getAdresse_entrepot());
            Label l3= new Label(""+entrepot.getEntreprise());
            Button delete = new Button("Supprimer");
            Button modifier = new Button("Modifier");
            
        cx.add(l1);
        cx.add(l2);
        cx.add(l3);
        cx.add(delete);
        cx.add(modifier);
        this.add(cx);
        
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               Dialog.show("Alert", "Voulez vous le supprimer",new Command("Annulée"), new Command("Supprimer"));
try{
                 if( EntrepotServices.getInstance().DeleteEntrepot(id))
                 {  Dialog.show("Success","Entrepot est supprimé avec succes",new Command("OK"));
                                new ListEntrepot(previous).show();}
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Entrepot est supprimé avec succes", new Command("OK"));
                    }
            }
        });
       
       modifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
       new ModifierForm(previous, ent).show();
                System.out.println(ent.getId_entrepot());
                System.out.println(id);
            }
        }); 
                    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

        
        
        
        
        }  
}
