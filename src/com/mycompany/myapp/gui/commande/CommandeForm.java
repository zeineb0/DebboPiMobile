/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.commande;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.service.commande.CommandeService;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author ghazi
 */
public class CommandeForm  extends Form{
    Form current;
    public CommandeForm() {
        current=this;
        setTitle("commande");
        setLayout(BoxLayout.y());
       ArrayList<Commande> commandes;
        add(new Label("Votre Commandes"));
        CommandeService cs= new CommandeService();
        commandes=cs.getAllCommande();
        for(int i=0;i<commandes.size();i++){
             add(new Label(""+commandes.get(i).getId_commande()));
        add(new Label(""+commandes.get(i).getTotal()));
            String date = Statics.simpleDateFormat.format(commandes.get(i).getDate_commande());
         add(new Label(date));
        }
       
        Commande commande;
        add(new Label("Votre Commandes"));
         commande=cs.getCommande(160);
            add(new Label(""+commande.getId_commande()));
        add(new Label(""+commande.getTotal()));
            String date = Statics.simpleDateFormat.format(commande.getDate_commande());
         add(new Label(date));
      
       
        
    }
    
}
