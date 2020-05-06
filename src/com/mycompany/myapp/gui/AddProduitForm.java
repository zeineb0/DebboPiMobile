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
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.CategoriesService;
import com.mycompany.myapp.services.ProduitService;
import java.util.ArrayList;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class AddProduitForm extends Form{
    
                private ComboBox cmb;

     public AddProduitForm(Form previous) {
        setTitle("Ajouter une nouveau Produit");
        setLayout(BoxLayout.yCenter());
        
        TextField tfName = new TextField("","Libelle");
        TextField tfMarque = new TextField("","Marque");
        TextField tfRef = new TextField("","Reference");
        TextField tfPrix = new TextField("","Prix");
        TextField tfQte = new TextField("","Quantite");

          cmb = new ComboBox<>();
        ArrayList<Categorie> anim = new ArrayList<>();
        anim.addAll(CategoriesService.getInstance().getAllCategorie());

        for (Categorie object : anim) {
            cmb.addItem(object);
        }
       // TextField tfStatus= new TextField("", "Status: 0 - 1");
        Button btnValider = new Button("Ajouter produit");
        cmb.addActionListener((evt) -> {
            Categorie a = anim.get(cmb.getSelectedIndex());
            System.out.println(a);
            System.out.println(cmb.getSelectedItem());
        });
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                        Produit p = new Produit();
                        p.setLibelle(tfName.getText());
                        p.setMarque(tfMarque.getText());
                        p.setReference(Integer.valueOf(tfRef.getText()));
                        p.setQuantite(Integer.valueOf(tfQte.getText()));
                        p.setPrix(Double.valueOf(tfPrix.getText()));
                        //Categorie c = anim.get(cmb.getSelectedIndex());
                        //p.setCategorie(c);
                        //User u = new User(123456,20345098,"zeieneb", "yhy,plkdh","kfjfhf", "zeineb@gmail.com", "client");
                        //p.setIdUser(u);
                       // p.setLibelle(tfName.getText());
                        if( ProduitService.getInstance().addProduit(p))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    
                }
                
                
            }
        });
        
        addAll(tfName,tfMarque,tfPrix,tfQte,tfRef,cmb,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
}
