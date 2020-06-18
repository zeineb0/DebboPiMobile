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
import com.codename1.ui.table.TableLayout;
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
    Form ms = new Form(BoxLayout.y());
    Button btn,supp;
    ComboBox cmbE;
    Container C2;
    public ListCategorieForm(Form previous) {
    setTitle("Liste des categories");
        
        cmbE = new ComboBox<>();
        ArrayList<Entrepot> ent = new ArrayList<>();
        ent.addAll(EntrepotService.getInstance().getAllEntrepot());
        for (Entrepot object : ent) {
            cmbE.addItem(object);
        }
        
        for (Categorie c : CategoriesService.getInstance().getAllCategorie()){
                         C2 = new Container(new TableLayout(2, 1));

            try {
                enc = EncodedImage.create("/tv.png");
                } catch (IOException ex) {

                                            }
            Image i = (URLImage.createToStorage(enc, c.getNom(), "http://localhost/DebboWeb/web/public/images/categories/" 
                   + c.getImage() +
                   "", URLImage.RESIZE_SCALE_TO_FILL));
                   ImageViewer img2 = new ImageViewer(i.fill(400, 400));
       
            
            Label l = new Label (c.getNom());
            
            l.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ms.setTitle("Catégorie");
                    Label l=new Label("nom");
                    TextField tfName = new TextField(c.getNom());
                    Label e=new Label("Entrepot");
                    System.out.println(c.getEntrepot().toString());
                    cmbE.setSelectedItem(c.getEntrepot());
                    btn = new Button("modifier");
                    supp = new Button("suprrimer");
                    ms.removeAll();
                    ms.addAll(l,tfName,e,cmbE,btn,supp);
                    
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
                        new ListCategorieForm(previous);
                        }
                    });
                             
                supp.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            CategoriesService.getInstance().supprimerCategorie(c);
                                                  new ListCategorieForm(previous);

                          
                        }
                    });
                
                    ms.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,a->showBack());
                    ms.refreshTheme();
                    ms.showBack();
                }
            });
       
          C2.add(img2);
          C2.add(l);
          
          this.clearClientProperties();
          this.add(C2);
          this.refreshTheme();  

    }
     this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
     this.show();
    
}
}
