/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.transporteur.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Livraison;
import com.mycompany.myapp.transporteur.services.LivraisonService;


/**
 *
 * @author Dell
 */
public class ModifierLivraisonForm extends Form{
    Form current;
    
    public  ModifierLivraisonForm(int id_liv)
    {
        
        current=this;
        setTitle("Modifier Livraison");
        setLayout(BoxLayout.y());
        
        add(new Label("Modifier la date de la livraison"));
        Picker nv_date = new Picker();
        
        Livraison livraison_temp=new Livraison();
        livraison_temp.setId_livraison(id_liv);
        livraison_temp.setDate_livraison(nv_date.getDate());
        //livraison_temp.setFK_id_user(id_user);
        Button modifier = new Button("Modifier");
        
        modifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(Dialog.show("Modifier Livraison", "voulez vous la modifier", "Modifier", "Annuler"))
                {
                    boolean test= LivraisonService.getInstance().modifierLivraison(livraison_temp);
                    if(test)
                    {
                       // new ListeLivraisonForm(current).show();
                       
                        
                    }
                    else
                    {
                        System.out.println("probleme f seervice");
                    }
                }
                else
                {
                    System.out.println("Annuler la dialog ");
                    
                }
                
            }
        });
        System.out.println("****** hani houni");
        
        
        addAll(nv_date,modifier);
        
        
         
        
    }
    
    
}
