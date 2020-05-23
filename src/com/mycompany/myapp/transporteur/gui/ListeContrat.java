/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.transporteur.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Contrat;
import com.mycompany.myapp.entities.Livraison;
import com.mycompany.myapp.transporteur.services.ContratService;
import com.mycompany.myapp.transporteur.services.LivraisonService;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class ListeContrat extends Form{
     Form current;
    
    public ListeContrat(Form previous)
    {
        current=this;
        this.setLayout(BoxLayout.y());
        
        setTitle("Liste des contrats signés");
        
        ArrayList<Contrat> contrats = new ArrayList<>();
        
        contrats =ContratService.getInstance().getContrat();
        System.out.println(" contrat : "+contrats);
        
        
        for(Contrat c : contrats )
        {
            addItem(c);
        }
        
       /* SpanLabel sp = new SpanLabel();
        sp.setText(LivraisonService.getInstance().getLivraisons().toString());
        add(sp);*/
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
        
    }
    
    
    public void addItem(Contrat contrat) {
        ImageViewer img = null;
        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        try {
            img = new ImageViewer(Image.createImage(contrat.getImg()));
        } catch (IOException ex) {

        }
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label nomT = new Label(contrat.getNom_transporteur());
        Label prenomT = new Label(contrat.getPrenom_transporteur());
        Label entreprise = new Label(contrat.getEntreprise());
        
        Label date_deb = new Label(Statics.simpleDateFormat.format(contrat.getDate_deb()));
        Label date_fin = new Label(Statics.simpleDateFormat.format(contrat.getDate_fin()));
     
        Label salaire= new Label(String.valueOf(contrat.getSalaire()));
        
       
        
        
        C3.add(nomT);
        C3.add(prenomT);
        C2.add(C3);
        C2.add(entreprise);
        //C2.add(date);
        C1.add(img);
        C1.add(C2);
        C1.setLeadComponent(entreprise);
        this.add(C1);
        this.refreshTheme();
        
      
        
         entreprise.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
            String dialog = "Date debut : " + date_deb.getText() + " \n Date fin : " + date_fin.getText()+ " \n l'entreprise : " +entreprise.getText() +"\n Nom du transporteur : "+nomT.getText()+"\n Prenom du transporteur : "+prenomT.getText()+"\n Salaire : "+salaire.getText();
            
            
            Command [] cmds = new Command[2];
            cmds[0] = new Command("Supprimer"){
                @Override
                public void actionPerformed(ActionEvent evt) 
                {  /*
                   boolean test = LivraisonService.getInstance().supprimerLivraison(id_liv);
                    System.out.println(test);
                    if (test)
                    {
                        ArrayList<Livraison> livraisons = new ArrayList<>();
        
                        livraisons =LivraisonService.getInstance().getLivraisonsLivre();
                        current.removeAll();
                        for(Livraison l : livraisons )
                        {
                            addItem(l);
                        }
                        current.showBack();
                      
                    }
                    else
                        System.out.println("****");*/
                }
            };
            cmds[1] = new Command("Fermer"){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    //do Option3
                }
            };
            Dialog.show("Contrat", dialog, cmds);
        });

    }
    
}
