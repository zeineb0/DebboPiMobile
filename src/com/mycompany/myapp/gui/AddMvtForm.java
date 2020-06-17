
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Entrepot;
import com.mycompany.myapp.entities.MouvementStock;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.CategoriesService;
import com.mycompany.myapp.services.EntrepotService;
import com.mycompany.myapp.services.MvtService;
import com.mycompany.myapp.services.ProduitService;
import java.util.ArrayList;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class AddMvtForm extends Form{
                    private ComboBox cmbE,cmbP,cmbES;

     public AddMvtForm(Form previous) {
      setTitle("Ajouter une nouveau mouvement");
        setLayout(BoxLayout.y());
        
         Picker d = new Picker();
         
        TextField tfQte = new TextField("","Quantite");
                Label N = new Label("Nature du mouvement :");
                Label nom1 = new Label("Date : ");
                Label nom2 = new Label("Produit :");
                Label nom3 = new Label("Quantité ajoutée/diminuée");
                Label nom4 = new Label("Entrepot :");

        cmbES=new ComboBox("Entrée","Sortie");
          cmbP = new ComboBox<>();
        ArrayList<Produit> p = new ArrayList<>();
        p.addAll(ProduitService.getInstance().getAllProduit());
        for (Produit object : p) {
            cmbP.addItem(object.toString());
        }
          cmbE = new ComboBox<>();
      ArrayList<Entrepot> ent = new ArrayList<>();
        ent.addAll(EntrepotService.getInstance().getAllEntrepot());
         System.out.println(EntrepotService.getInstance().getAllEntrepot());

        for (Entrepot object : ent) {
            cmbE.addItem(object.toString());
        }
       // TextField tfStatus= new TextField("", "Status: 0 - 1");
        Button btnValider = new Button("Ajouter mouvement");
  
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfQte.getText().length()==0) )
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    if (cmbES.getSelectedItem().toString().equals("Entrée")){
                                                System.out.println("entrée");
                     MouvementStock m = new MouvementStock();
                        m.setDateMouv(d.getDate());
                        m.setNatureDuStock(String.valueOf(cmbES.getSelectedItem()));
                        Produit pr = p.get(cmbP.getSelectedIndex());
                       System.out.println(pr.getQuantite());
                        m.setP(pr);
                        m.setQuantite(Integer.valueOf(tfQte.getText()));
                        Entrepot e = ent.get(cmbE.getSelectedIndex());
                        m.setE(e);
                        System.out.println(m);
                        //System.out.println(ProduitService.getInstance().addProduit(p));
                         if( MvtService.getInstance().addMvt(m))
                            Dialog.show("Success","MVT ajouté",new Command("OK"));
                     else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                                                              new ListMvtForm(previous);
    
                    }
                    if (cmbES.getSelectedItem().toString().equals("Sortie")){
                        System.out.println("sortie");

                        Produit pr = p.get(cmbP.getSelectedIndex());
                        int prQ= (pr.getQuantite());
                        System.out.println(prQ);
                        int prE= (Integer.valueOf(tfQte.getText()));
                        System.out.println(prE);

                        if (prQ >=prE ){
                            MouvementStock m = new MouvementStock();
                            m.setDateMouv(d.getDate());
                            m.setNatureDuStock(String.valueOf(cmbES.getSelectedItem()));
                            System.out.println(pr.getQuantite());
                            m.setP(pr);
                                                m.setQuantite(Integer.valueOf(tfQte.getText()));

                        Entrepot e = ent.get(cmbE.getSelectedIndex());
                        m.setE(e);
                            System.out.println(m);
                        //System.out.println(ProduitService.getInstance().addProduit(p));
                         if( MvtService.getInstance().addMvt(m))
                            Dialog.show("Success","Mouvement ajouté",new Command("OK"));                    
                                                                                         new ListMvtForm(previous);

                        }
                     else
                            Dialog.show("ERREUR", "Quantitée intexistante", new Command("Veuillez réessayer"));
                                                                 new ListMvtForm(previous);

                    }

                                                                                       new ListMvtForm(previous);

                }
                                                                 new ListMvtForm(previous);

                
            }

        });
                                                                                     new ListMvtForm(previous);

        addAll(N,cmbES,nom1,d,nom3,tfQte,nom2,cmbP,nom4,cmbE,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
     
     
     }
}
