/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.gererentrepot;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.table.TableLayout;
import com.mycompany.myapp.services.gererentrepot.EntrepotServices;
import com.mycompany.myapp.entities.Entrepot;
/**
 *
 * @author asus
 */
public class ListEntrepot extends Form{
    Form current;
    public ListEntrepot(Form previous) {
        current = this;
        setTitle("Liste des Entrepots");
        this.setLayout( new TableLayout(CENTER,2));
       
        for(Entrepot e: EntrepotServices.getInstance().getAllEntrepots()){
       Container cx = new Container(new TableLayout(1,1));
       cx.setWidth(CENTER);
            Label l1= new Label("Num Fiscale: "+e.getNum_fiscale());
            Label l2= new Label("Adresse: "+e.getAdresse_entrepot());
            Label l3= new Label("Entreprise: "+e.getEntreprise());
            Button bt = new Button("Detail");
        cx.add(l1);
        cx.add(l2);
        cx.add(l3);
        cx.add(bt);
        this.add(cx);
          cx.getUnselectedStyle().setMargin(10, 10, 10, 10);
           cx.getUnselectedStyle().setBorder(Border.createLineBorder(2, 0x000000)); 
            cx.getUnselectedStyle().setPadding(10, 10, 10, 10);
             cx.getUnselectedStyle().setBgColor(0xffffff);
          
          this.refreshTheme(); 
       bt.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
       int id = e.getId_entrepot();
       new DetailsForm(previous, id, e).show();
        
               
            }
            }  );


    }  
        getToolbar().addMaterialCommandToOverflowMenu("Ajouter Entrepot",FontImage.MATERIAL_ADD,e-> new AddEntrepot(current).show());
        getToolbar().addMaterialCommandToOverflowMenu("Entrepot Loué",FontImage.MATERIAL_HIGHLIGHT,e-> new EntrepotLoueForm(previous).show());
        getToolbar().addMaterialCommandToOverflowMenu("Entrepot A  Louer",FontImage.MATERIAL_SHOP ,e-> new EntrepotALouerForm().show());
        getToolbar().addMaterialCommandToOverflowMenu("Mes Locations",FontImage.MATERIAL_SHOP ,e-> new LocationListe(previous).show());
        
           
          
                       


    }
    
}
