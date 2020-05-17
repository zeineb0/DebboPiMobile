/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Entrepot;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.CategoriesService;
import com.mycompany.myapp.services.EntrepotService;
import com.mycompany.myapp.services.ProduitService;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class ListCategorieForm extends Form{
    private EncodedImage enc;
    Form ms;
    Button btn,supp;
    ComboBox cmbE;
    public ListCategorieForm(Form previous) {
        setTitle("Liste des categories");
        
               cmbE = new ComboBox<>();
      ArrayList<Entrepot> ent = new ArrayList<>();
        ent.addAll(EntrepotService.getInstance().getAllEntrepot());
         System.out.println(EntrepotService.getInstance().getAllEntrepot());

        for (Entrepot object : ent) {
            cmbE.addItem(object);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
      for (Categorie c : CategoriesService.getInstance().getAllCategorie()){
        try {
                enc = EncodedImage.create("/a.png");
            } catch (IOException ex) {

            }
            
         Image i = (URLImage.createToStorage(enc,c.getNom(), "http://localhost/DebboWeb/web/public/images/categories/" + c.getImage() +
                    "", URLImage.RESIZE_SCALE_TO_FILL));
            Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            Label l = new Label (c.getNom());
           // Label tel = new Label (c.getNom());
            l.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    
                    
                    
                     ms = new Form(BoxLayout.y());
                    ms.setTitle("Catégorie");
                    Label l=new Label("nom");
                    TextField tfName = new TextField(c.getNom());
                    Label e=new Label("Entrepot");
                    System.out.println(c.getEntrepot().toString());
                    cmbE.setSelectedItem(c.getEntrepot());
                    btn = new Button("modifier");
                    supp = new Button("suprrimer");
                btn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                         Categorie cmodifié = new Categorie();
                         cmodifié.setId(c.getId());
                         cmodifié.setNom(tfName.getText());
                            System.out.println(tfName.getText());
                         
                        Entrepot e = ent.get(cmbE.getSelectedIndex());
                        cmodifié.setEntrepot(e);
                         //pmodifié.setCategorie(p.getCategorie());
                         //pmodifié.setEntrepot(p.getEntrepot());
                         CategoriesService.getInstance().modifierCategorie(cmodifié);
                                     System.out.println("OOOOOK");
                        }
                    });
        supp.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            CategoriesService.getInstance().supprimerCategorie(c);
                            refreshTheme();
                            showBack();
                          
                        }
                    });
        ms.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            refreshTheme();
                            showBack();
                       
                        }
                    });
                       ms.refreshTheme();
                ms.addAll(l,tfName,e,cmbE,btn,supp);
                    ms.showBack();
   
                }
            });
       
          C2.add(i);
          C2.add(l);
          add(C2);
    }
    
}}
