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

     public AddCategorieForm(Form previous) {
        setTitle("Ajouter une nouvelle catégorie");
        setLayout(BoxLayout.yCenter());
        cmbE = new ComboBox<>();
     cmbE = new ComboBox<>();
      ArrayList<Entrepot> ent = new ArrayList<>();
        ent.addAll(EntrepotService.getInstance().getAllEntrepot());
         System.out.println(EntrepotService.getInstance().getAllEntrepot());

        for (Entrepot object : ent) {
            cmbE.addItem(object.toString());
        }
        TextField tfName = new TextField("","Nom");
       // TextField tfStatus= new TextField("", "Status: 0 - 1");
        Button btnValider = new Button("Ajouter catégorie");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0)  )
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                        Categorie c = new Categorie(tfName.getText());
                        Entrepot e = ent.get(cmbE.getSelectedIndex());
                        c.setEntrepot(e);
                        if( CategoriesService.getInstance().addCategorie(c))
                            Dialog.show("Success","Catégorie ajoutée",new Command("OK"));
                        else
                            Dialog.show("Erreur", "Catégorie non ajoutée", new Command("Veuillez réessayer"));
                }
                                    new ListCategorieForm(previous);

                
            }
        });
        
        addAll(tfName,cmbE,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
}
