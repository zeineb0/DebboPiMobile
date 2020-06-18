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
import com.mycompany.myapp.services.CategoriesService;
import com.mycompany.myapp.services.EntrepotService;
import java.util.ArrayList;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class AddCategorieForm extends Form{
                    private ComboBox cmbE;
                    private Button b ;
     public AddCategorieForm(Form previous) {
        setTitle("Ajouter une nouvelle catégorie");
        setLayout(BoxLayout.y());
        cmbE = new ComboBox<>();
     cmbE = new ComboBox<>();
      ArrayList<Entrepot> ent = new ArrayList<>();
        ent.addAll(EntrepotService.getInstance().getAllEntrepot());
         System.out.println(EntrepotService.getInstance().getAllEntrepot());
         cmbE.addItem("Choisir un entrepot");
        for (Entrepot object : ent) {
            cmbE.addItem(object.toString());
        }
        Label nomm = new Label("Nom :");
        Label entt = new Label("Veuillez choisir un entrepot :");
        Label photo = new Label("Photo :");
        TextField tfName = new TextField("","Nom");
       // TextField tfStatus= new TextField("", "Status: 0 - 1");
        Button btnValider = new Button("Ajouter catégorie");
        
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
                if ((tfName.getText().length()==0)  || photoField.getText().length()==0)
                    Dialog.show("Alerte", "Veuillez remplir tous les champs", new Command("OK"));
                else if (cmbE.getSelectedItem().toString().equals("Choisir un entrepot"))
                                        Dialog.show("Alerte", "Veuillez remplir tous les champs", new Command("OK"));
                   
                else
                {
                        Categorie c = new Categorie(tfName.getText());
                        //c.setImage(photoField.getText());
                        Entrepot e = ent.get(cmbE.getSelectedIndex());
                        c.setEntrepot(e);
                        if( CategoriesService.getInstance().addCategorie(c))
                        { Dialog.show("Success","Catégorie ajoutée",new Command("OK"));
                                                            new ListCategorieForm(previous);

                        }
                        else
                            Dialog.show("Erreur", "Catégorie non ajoutée", new Command("Veuillez réessayer"));
                        
                }

                
            }
        });
        
        addAll(nomm,tfName,entt,cmbE,photo,photoContainer,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
}
