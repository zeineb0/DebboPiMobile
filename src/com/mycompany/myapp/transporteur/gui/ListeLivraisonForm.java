/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.transporteur.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Livraison;
import com.mycompany.myapp.transporteur.services.LivraisonService;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class ListeLivraisonForm extends Form{
    
    
    public ListeLivraisonForm(Form previous)
    {
        this.setLayout(BoxLayout.y());
        
        setTitle("Liste des livraisons livrées");
        
        ArrayList<Livraison> livraisons = new ArrayList<>();
        
        livraisons =LivraisonService.getInstance().getLivraisons();
        System.out.println(" livraison : "+livraisons);
        
        
        for(Livraison l : livraisons )
        {
            addItem(l);
        }
        
       /* SpanLabel sp = new SpanLabel();
        sp.setText(LivraisonService.getInstance().getLivraisons().toString());
        add(sp);*/
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    
    }
    
    
    
     public void addItem(Livraison liv) {
        ImageViewer img = null;
        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        try {
            img = new ImageViewer(Image.createImage(liv.getImg()));
        } catch (IOException ex) {

        }
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label adresse = new Label(liv.getAdresse_livraison());
        Label etat = new Label(liv.getEtat_livraison());
        Label date = new Label(Statics.simpleDateFormat.format(liv.getDate_livraison()));
        
       
        
        
        
        etat.addPointerPressedListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                Dialog.show("Livraison", "Adresse : " + adresse.getText() + " \n Etat : " + etat.getText()+ " \n Etat : " +date.getText(), "Ok", null);

            }
        });

        C2.add(adresse);
        C2.add(etat);
        C2.add(date);
        C1.add(img);
        C1.add(C2);
        C1.setLeadComponent(etat);
        this.add(C1);
        this.refreshTheme();

    }
    
    
    
    
}
