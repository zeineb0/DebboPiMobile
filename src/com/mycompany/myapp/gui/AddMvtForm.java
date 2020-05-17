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
        setLayout(BoxLayout.yCenter());
        
         Picker d = new Picker();
         
        TextField tfQte = new TextField("","Quantite");
        
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
                if ((tfQte.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                        MouvementStock m = new MouvementStock();
                        m.setDateMouv(d.getDate());
                        m.setNatureDuStock(String.valueOf(cmbES.getSelectedItem()));
                        Produit pr = p.get(cmbP.getSelectedIndex());
                        m.setP(pr);
                        Entrepot e = ent.get(cmbE.getSelectedIndex());
                        m.setE(e);
                        //System.out.println(ProduitService.getInstance().addProduit(p));
                         if( MvtService.getInstance().addMvt(m))
                            Dialog.show("Success","MVT ajouté",new Command("OK"));
                       else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    
                }
                
                
            }
        });
        
        addAll(cmbES,d,tfQte,cmbP,cmbE,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
     
     
     }
}
