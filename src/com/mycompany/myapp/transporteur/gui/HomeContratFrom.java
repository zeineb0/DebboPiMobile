/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.transporteur.gui;


import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;




/**
 *
 * @author Dell
 */
public class HomeContratFrom extends Form{
    
    
     Form current;
     public HomeContratFrom()
     {
        current=this;
        setTitle("Gestion des contrat");
        setLayout(BoxLayout.y());
        
        add(new Label(" Vous pouvez choisir l'une de ces options :"));
        Button btnAddContrat = new Button("Ajouter Contrat");
        Button btnListContrat = new Button("La Liste des Contrats");
        Button btnListContratEXP = new Button("La Liste des Contrats bientôt expirer");
        Button sendMail= new Button("send mail");
        
        sendMail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Message msg = new Message("hello Test mail");
                Message.sendMessage(new String[]{"medfarouk.benakacha@gmail.com"}, "Mail", msg);
            }
        });
        
        
        btnAddContrat.addActionListener(e->new AjouterContratForm(current).show());
        btnListContrat.addActionListener(e->new ListeContrat(current).show());
        btnListContratEXP.addActionListener(e-> new ListeContratEXP(current).show());
        addAll(btnAddContrat,btnListContrat,btnListContratEXP,sendMail);
     }
    
    
}
