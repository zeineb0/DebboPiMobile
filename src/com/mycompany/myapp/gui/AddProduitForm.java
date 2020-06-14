/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Entrepot;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.CategoriesService;
import com.mycompany.myapp.services.EntrepotService;
import com.mycompany.myapp.services.ProduitService;
import java.util.ArrayList;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class AddProduitForm extends Form{
    
                private ComboBox cmb;
                private ComboBox cmbE;
       public static boolean isValidFloat(String str) {
		boolean isValid = false;
		try {
			Integer newInput = Integer.valueOf(str);
			float i = newInput.floatValue();
			isValid = true;
		} finally {
			return isValid;
		}
	}
     public AddProduitForm(Form previous) {
        setTitle("Ajouter une nouveau Produit");
        setLayout(BoxLayout.y());
        Label nom = new Label("Libelle : ");
        Label marq = new Label("Marque : ");
        Label ref = new Label("Référence : ");
        Label prix = new Label("Prix : ");
        Label qte = new Label("Quantité : ");
        Label categ = new Label("Catégorie : ");
        Label photo = new Label("Photo : ");
        TextField tfName = new TextField("","Libelle");
        TextField tfMarque = new TextField("","Marque");
        TextField tfRef = new TextField("","Reference");
        TextField tfPrix = new TextField("","Prix");
        TextField tfQte = new TextField("","Quantite");

          cmb = new ComboBox<>();
        ArrayList<Categorie> anim = new ArrayList<>();
        anim.addAll(CategoriesService.getInstance().getAllCategorie());
            cmb.addItem("Choisir une catégorie");

        for (Categorie object : anim) {
            
            cmb.addItem(object);
        }
          
      
       // TextField tfStatus= new TextField("", "Status: 0 - 1");
        Button btnValider = new Button("Ajouter produit");
        cmb.addActionListener((evt) -> {
            Categorie a = anim.get(cmb.getSelectedIndex());
        });
        
               //pour la photo
        Label photoLabel = new Label("Photo");
        Button selectPhoto = new Button("parcourir");
        TextField photoField = new TextField("", "Importer une photo", 10, TextArea.ANY);
        photoField.setEditable(false);
        selectPhoto.addActionListener((evt) -> {
            if (Dialog.show("Photo!", "une annonce avec des  photos est 10 fois plus visible", null, "Gallerie") == false) {
                Display.getInstance().openGallery((e) -> {
                    if (e != null && e.getSource() != null) {
                        String file = (String) e.getSource();
                        photoField.setText(file.substring(file.lastIndexOf('/') + 1));
                    }
                }, Display.GALLERY_IMAGE);
            } else {
                System.out.println("ici on va accerder à l'appareille photo");
            }
        });
        Container photoContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        photoContainer.add(photoLabel);
        photoContainer.add(photoField);
        photoContainer.add(selectPhoto);
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
         
                if ((tfName.getText().length()==0)|| (tfMarque.getText().length()==0)|| (tfRef.getText().length()==0)||(tfQte.getText().length()==0) || (photoField.getText().length()==0) )
                    Dialog.show("Alerte", "Veuillez remplir tous les champs", new Command("OK"));
                else if((isValidFloat(tfPrix.getText())==false))
                    Dialog.show("Alerte", "Le prix doit être numerique ", new Command("OK"));
                 else if((isValidFloat(tfQte.getText())==false))
                    Dialog.show("Alerte", "La quantite doit être numerique ", new Command("OK"));
                else if((isValidFloat(tfRef.getText())==false))
                    Dialog.show("Alerte", "La reference doit être numerique ", new Command("OK"));
                else if(cmb.getSelectedItem().toString().equals("Choisir une catégorie"))
                    Dialog.show("Alerte", "Veuillez choisir une catégorie ", new Command("OK"));
                else
                {
                        Produit p = new Produit();
                        p.setLibelle(tfName.getText());
                        p.setMarque(tfMarque.getText());
                        p.setReference(Integer.valueOf(tfRef.getText()));
                        p.setQuantite(Integer.valueOf(tfQte.getText()));
                        p.setPrix(Double.valueOf(tfPrix.getText()));
                        System.out.println(photoField.getText());

                        Categorie c = anim.get(cmb.getSelectedIndex());
                        p.setCategorie(c);
                    
                        //System.out.println(ProduitService.getInstance().addProduit(p));
                         if( ProduitService.getInstance().addProduit(p))
                            Dialog.show("Success","Produit ajouté",new Command("OK"));
                       else
                            Dialog.show("ERREUR", "Produit non ajouté", new Command("OK"));
                                 new ListProduitForm(previous);
   
                }
                
            }
        });
        
        addAll(nom,tfName,marq,tfMarque,prix,tfPrix,qte,tfQte,ref,tfRef,categ,cmb,photo,photoContainer,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
}
