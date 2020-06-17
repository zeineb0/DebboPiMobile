/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.transporteur.gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Entrepot;
import com.mycompany.myapp.entities.Livraison;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.transporteur.services.ContratService;
import com.mycompany.myapp.transporteur.services.LivraisonService;
import java.util.ArrayList;
import javafx.scene.control.DatePicker;

/**
 *
 * @author Dell
 */
public class AjouterContratForm extends Form{
    
     Form current;
     ComboBox entrepot;
     ComboBox user;
     
    public AjouterContratForm(Form previous)
    {
        
        current=this;
        this.setLayout(BoxLayout.y());
        
        setTitle("Ajouter Contrat");
        
        Picker dateDeb = new Picker();
        Picker dateFin = new Picker();
        TextField salaire = new TextField();
        entrepot= new ComboBox<>();
        
        ArrayList<Entrepot> ent = new ArrayList<>();
        ent.addAll(ContratService.getInstance().getAllEntrepot());
         System.out.println(ContratService.getInstance().getAllEntrepot());
         entrepot.addItem("Choisir un entrepot");
        for (Entrepot object : ent) {
            entrepot.addItem(object.getEntreprise());
        }
        
        user= new ComboBox<>();
        /*ArrayList<Utilisateur> usr = new ArrayList<>();
        usr.addAll(ContratService.getInstance().getAllEntrepot());
         System.out.println(ContratService.getInstance().getAllEntrepot());
         entrepot.addItem("Choisir un entrepot");
        for (Utilisateur object : usr) {
            entrepot.addItem(object.getUsername());
        }*/
        
        
        
        
        Button btnValider = new Button("Ajouter");
        
        
        addAll(dateDeb,dateFin,salaire,entrepot,user,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    
    }
    
}
