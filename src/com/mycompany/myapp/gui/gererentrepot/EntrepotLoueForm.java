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
import com.mycompany.myapp.entities.User;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.table.TableLayout;
import com.mycompany.myapp.entities.Entrepot;
import com.mycompany.myapp.services.gererentrepot.EntrepotServices;

/**
 *
 * @author asus
 */
public class EntrepotLoueForm extends Form {
      Form current;
    public EntrepotLoueForm(Form previous,User u) {
        current = this;
        setTitle("Liste des entrepots loués");
        this.setLayout( new TableLayout(CENTER,2));
       
        for(Entrepot e: EntrepotServices.getInstance().getLoueEntrepots()){
       Container cx = new Container(new TableLayout(1,1));
       cx.setWidth(CENTER);
            Label l1= new Label("Numero fiscale "+e.getNum_fiscale());
            Label l2= new Label("adresse entrepot"+e.getAdresse_entrepot());
            Label l3= new Label("Entreprise "+e.getEntreprise());
            Label l4= new Label("prix "+e.getPrix_location());
            Label l5= new Label("quantite max "+e.getQuantite_max());
           
        cx.add(l1);
        cx.add(l2);
        cx.add(l3);
        cx.add(l4);
        cx.add(l5);
        this.add(cx);
          
          cx.getUnselectedStyle().setMargin(10, 10, 10, 10);
           cx.getUnselectedStyle().setBorder(Border.createLineBorder(2, 0x000000)); 
            cx.getUnselectedStyle().setPadding(10, 10, 10, 10);
             cx.getUnselectedStyle().setBgColor(0xffffff);
          
          this.refreshTheme(); 
       
 
    }       
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new ListEntrepot(previous, u).showBack());
getToolbar().addMaterialCommandToOverflowMenu("Ajouter Entrepot",FontImage.MATERIAL_ADD,e-> new AddEntrepot(current).show());
    getToolbar().addMaterialCommandToOverflowMenu("Entrepot A  Louer",FontImage.MATERIAL_SHOP ,e-> new EntrepotALouerForm(previous,u).show());
    getToolbar().addMaterialCommandToOverflowMenu("Liste des entrepots",FontImage.MATERIAL_SHOP ,e-> new ListEntrepot(previous, u).show());
 getToolbar().addMaterialCommandToOverflowMenu("Mes Locations",FontImage.MATERIAL_SHOP ,e-> new LocationListe(previous, u).show());

    }
    
}
