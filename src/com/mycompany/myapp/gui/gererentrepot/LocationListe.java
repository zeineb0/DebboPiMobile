/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.gererentrepot;

import com.codename1.messaging.Message;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.Border;
import com.mycompany.myapp.entities.Location;
import com.mycompany.myapp.services.gererentrepot.LocationServices;
import com.codename1.ui.FontImage;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.gererentrepot.EntrepotServices;
import com.mycompany.myapp.utils.Statics;
import org.joda.time.DateTime;
import org.joda.time.Days;
import com.mycompany.myapp.entities.User;


/**
 *
 * @author asus
 */
public class LocationListe extends Form{
    
    Form current;
    public LocationListe(Form previous, User u) {
      current = this;
        setTitle("Liste des Location");
        this.setLayout( new TableLayout(CENTER,1));
       
        for(Location l: LocationServices.getInstance().getAllLocations()){
       Container cx = new Container(new TableLayout(1,1));
       cx.setWidth(CENTER);
       String dat = Statics.simpleDateFormat.format(l.getDate_deb_location());
            Label l1= new Label("date deb Location: "+dat);
       String dat1 = Statics.simpleDateFormat.format(l.getDate_fin_location());

            Label l2= new Label("Date fin location: "+dat1);
            Label l3= new Label("Prix: "+l.getPrix_location());
            Button bt = new Button("Detail");
        cx.add(l1);
        cx.add(l2);
        cx.add(l3);
        cx.add(bt);
        this.add(cx);
        bt.addActionListener((evt) -> {
            String dialog = "date debut : " + dat + " \n date fin : " + dat1+ " \n prix location : " +l.getPrix_location() +"\n quantite" +l.getFK_id_entrepot().getQuantite_max()+"\n num fiscale" +l.getFK_id_entrepot().getNum_fiscale()+"\n entreprise" +l.getFK_id_entrepot().getEntreprise()+"\n Proprietaire: " +l.getFK_id_entrepot().getFk_id_fournisseur().getPrenom()+" "+l.getFK_id_entrepot().getQuantite_max()+"\n Proprietaire: " +l.getFK_id_entrepot().getFk_id_fournisseur().getNom() ;
             Command cmd1= new Command("Supprimer"){ @Override
                public void actionPerformed(ActionEvent evt) {
                         try{
                 if(LocationServices.getInstance().DeleteLocation(l.getId_location()))
                 {  Dialog.show("Success","la location est supprimé avec succes",new Command("OK"));
                                new ListEntrepot(current, u).show();}
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Entrepot est supprimé avec succes", new Command("OK"));
                    }
            }           
                  
                    };
             
                     
                 
              
             
                //   Dialog.show("hi", calculer, calculPrix , TOP, encN);
                             Command cmd2= new Command("Annuler");

            Dialog.show("Location", dialog, cmd1 , cmd2);
                   
                
                 
             
                   }); 
        
          cx.getUnselectedStyle().setMargin(10, 10, 10, 10);
           cx.getUnselectedStyle().setBorder(Border.createLineBorder(2, 0x000000)); 
            cx.getUnselectedStyle().setPadding(10, 10, 10, 10);
             cx.getUnselectedStyle().setBgColor(0xffffff);
          
          this.refreshTheme(); 
     

        }    
         if(u.getRole().equals("Client")){
    getToolbar().addMaterialCommandToOverflowMenu("Entrepot A Louer",FontImage.MATERIAL_SHOP ,e-> new EntrepotALouerForm(previous, u));

            }
            else{
        
        
        
    getToolbar().addMaterialCommandToOverflowMenu("Ajouter Entrepot",FontImage.MATERIAL_ADD,e-> new AddEntrepot(current).show());
    getToolbar().addMaterialCommandToOverflowMenu("Entrepot Loué",FontImage.MATERIAL_HIGHLIGHT,e-> new EntrepotLoueForm(previous, u).show());
 getToolbar().addMaterialCommandToOverflowMenu("Entrepot A  Louer",FontImage.MATERIAL_SHOP ,e-> new EntrepotALouerForm(previous,u).show());
    getToolbar().addMaterialCommandToOverflowMenu("Liste des entrepots",FontImage.MATERIAL_SHOP ,e-> new ListEntrepot(previous, u).show());
    }
    }
}
