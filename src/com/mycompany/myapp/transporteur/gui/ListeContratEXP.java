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
import com.mycompany.myapp.entities.Contrat;
import com.mycompany.myapp.entities.Livraison;
import com.mycompany.myapp.transporteur.services.ContratService;
import com.mycompany.myapp.transporteur.services.LivraisonService;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Dell
 */
public class ListeContratEXP extends Form{
    
     Form current;
    
    public ListeContratEXP(Form previous)
    {
        current=this;
        this.setLayout(BoxLayout.y());
        
        setTitle("Liste des contrats bientot exp");
        
        ArrayList<Contrat> contrats = new ArrayList<>();
        
        contrats =ContratService.getInstance().getContratEXP();
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
            cmds[0] = new Command("Modifier"){
                @Override
                public void actionPerformed(ActionEvent evt) 
                {  
                    Contrat Contrat_temp=new Contrat();
                    
                    Container modifier = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                     Picker nv_date = new Picker();
                     modifier.add(nv_date);
                     
                     Command [] modCMDS=new Command[2];
                     
                     modCMDS[0]=new Command("Modifier")
                     {
                         @Override
                         public void actionPerformed(ActionEvent evt)
                         {
                            Contrat_temp.setFK_id_entrepot(contrat.getFK_id_entrepot());
                            Contrat_temp.setFK_id_transporteur(contrat.getFK_id_transporteur());
                            Contrat_temp.setDate_fin((Date) nv_date.getValue());
                             boolean test= ContratService.getInstance().modifierContrat(Contrat_temp);
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
                    
                    
                    Dialog.show("Modifier Contrat", modifier , modCMDS);
                    
                    ArrayList<Contrat> contrats = new ArrayList<>();
        
                        contrats =ContratService.getInstance().getContratEXP();
                        current.removeAll();
                        for(Contrat l : contrats )
                        {
                            addItem(l);
                        }
                        current.showBack();
                    
                    
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
