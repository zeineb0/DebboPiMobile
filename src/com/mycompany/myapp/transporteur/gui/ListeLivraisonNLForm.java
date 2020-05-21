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
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Livraison;
import com.mycompany.myapp.transporteur.services.LivraisonService;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Dell
 */
public class ListeLivraisonNLForm extends Form{
    Form current;
    
    
    public ListeLivraisonNLForm(Form previous)
    {
        current=this;
        this.setLayout(BoxLayout.y());
        
        setTitle("Liste des livraisons non livrées");
        
        ArrayList<Livraison> livraisons = new ArrayList<>();
        
        livraisons =LivraisonService.getInstance().getLivraisonsNLivre();
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
        Label tel = new Label(liv.getTel());
        String id_liv=String.valueOf(liv.getId_livraison());
        
       
        
        

        C2.add(adresse);
        C2.add(etat);
        //C2.add(date);
        C1.add(img);
        C1.add(C2);
        C1.setLeadComponent(etat);
        this.add(C1);
        this.refreshTheme();
        
      
        
         etat.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
            String dialog = "Adresse : " + adresse.getText() + " \n Etat : " + etat.getText()+ " \n Date : " +date.getText() +"\n Telephone du Client : "+tel.getText();
            
            
            Command [] cmds = new Command[3];
            cmds[0] = new Command("Modifier"){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    
                    Livraison livraison_temp=new Livraison();
                    
                    Container modifier = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                     Picker nv_date = new Picker();
                     modifier.add(nv_date);
                     
                     Command [] modCMDS=new Command[2];
                     
                     modCMDS[0]=new Command("Modifier")
                     {
                         @Override
                         public void actionPerformed(ActionEvent evt)
                         {
                            livraison_temp.setId_livraison(liv.getId_livraison());
                            livraison_temp.setDate_livraison((Date) nv_date.getValue());
                             boolean test= LivraisonService.getInstance().modifierLivraison(livraison_temp);
                        if(test)
                        {
                            
                            System.out.println(" c bon mchet ");
                       
                        
                        }
                        else
                        {
                        System.out.println("probleme f seervice");
                        }
                         }
                     };
                     
                    modCMDS[1] = new Command("Fermer"){
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                       
                        }
                    };
                    
                    
                    Dialog.show("Modifier Livraison", modifier , modCMDS);
                    
                    ArrayList<Livraison> livraisons = new ArrayList<>();
        
                        livraisons =LivraisonService.getInstance().getLivraisonsNLivre();
                        current.removeAll();
                        for(Livraison l : livraisons )
                        {
                            addItem(l);
                        }
                        current.showBack();
                    
                   // new ModifierLivraisonForm(liv.getId_livraison()).show();
           
                }
            };
            cmds[1] = new Command("Supprimer"){
                @Override
                public void actionPerformed(ActionEvent evt) {
                   boolean test = LivraisonService.getInstance().supprimerLivraison(id_liv);
                    System.out.println(test);
                    if (test)
                    {
                        ArrayList<Livraison> livraisons = new ArrayList<>();
        
                        livraisons =LivraisonService.getInstance().getLivraisonsNLivre();
                        current.removeAll();
                        for(Livraison l : livraisons )
                        {
                            addItem(l);
                        }
                        current.showBack();
                      
                    }
                    else
                        System.out.println("****");
                }
            };
            cmds[2] = new Command("Fermer"){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    //do Option3
                }
            };
            Dialog.show("Livraison", dialog, cmds);
        });

    
}
     
}
