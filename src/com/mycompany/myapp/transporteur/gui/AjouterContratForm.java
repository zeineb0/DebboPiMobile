/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.transporteur.gui;

import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Contrat;
import com.mycompany.myapp.entities.Entrepot;
import com.mycompany.myapp.entities.Livraison;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.transporteur.services.ContratService;
import com.mycompany.myapp.transporteur.services.LivraisonService;
import java.util.ArrayList;
import java.util.Date;
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
        for (Entrepot object : ent) {
            entrepot.addItem(object.getEntreprise());
        }
        
        user= new ComboBox<>();
        ArrayList<Utilisateur> usr = new ArrayList<>();
        usr.addAll(ContratService.getInstance().getAllTransporteur());
        for (Utilisateur object : usr) {
            user.addItem(object.getPrenom());
        }
        
        
        
        
        Button btnValider = new Button("Ajouter");
        
        
        
        
        addAll(dateDeb,dateFin,salaire,entrepot,user,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Contrat contrat = new Contrat();
                
                
                contrat.setDate_deb(dateDeb.getDate());
                contrat.setDate_fin(dateFin.getDate());
                contrat.setSalaire(((int) Integer.parseInt(salaire.getText())));
                Entrepot e = ent.get(entrepot.getSelectedIndex());
                contrat.setFK_id_entrepot(e.getId_entrepot());
                
                Utilisateur u = usr.get(user.getSelectedIndex());
                contrat.setFK_id_transporteur(u.getId_user());
                
                boolean Test = ContratService.getInstance().addContrat(contrat);
                System.out.println(Test);
                
                Message msg = new Message("hello Test mail");
                Message.sendMessage(new String[]{u.getEmail()}, "Contrat signé", msg);
                         
                new ListeContrat(previous).show();
                
                
            }
        });
    
    }
    
}
