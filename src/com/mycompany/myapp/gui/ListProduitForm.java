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
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.CategoriesService;
import com.mycompany.myapp.services.ProduitService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class ListProduitForm extends Form {

    private SpanLabel lb;
    Form ms = new Form(BoxLayout.y());
    private EncodedImage enc;
    private ComboBox cmb;
    Button btn,supp;
    Container C2;

    public ListProduitForm(Form previous) {
        
        setTitle("Liste des produits");
        this.setLayout(new TableLayout(2,CENTER));
        
        cmb = new ComboBox<>();
        ArrayList<Categorie> anim = new ArrayList<>();
        anim.addAll(CategoriesService.getInstance().getAllCategorie());
     
        cmb.setName("Veuillez");
        for (Categorie object : anim) {
            cmb.addItem(object);
        }
                
        
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        

        for (Produit p : ProduitService.getInstance().getAllProduit())
        {
            C2 = new Container(new TableLayout(2, 1));

            
            try {
                enc = EncodedImage.create("/tv.png");
                } catch (IOException ex) {

                                           }
            Label pro = new Label("-10% off");
             
           Image i = (URLImage.createToStorage(enc, p.getLibelle(), "http://localhost/DebboWeb/web/public/images/produits/" 
                   + p.getImage() +
                   "", URLImage.RESIZE_SCALE_TO_FILL));
                   ImageViewer img2 = new ImageViewer(i.fill(600, 600));

            
            Label l = new Label (p.getLibelle());
            Label tel = new Label (String.valueOf(p.getPrix()));
            
            
            l.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    
                    ms.setTitle("Produit");
                    Label l=new Label("Libelle");
                    TextField tfName = new TextField(p.getLibelle());
                    Label m=new Label("marque");
                    TextField tfMarque = new TextField(p.getMarque());
                    Label q=new Label("Quantité");
                    TextField tfQte = new TextField(String.valueOf(p.getQuantite()));
                    Label pr=new Label("prix");
                    TextField tfPrix = new TextField(String.valueOf(p.getPrix()));
                    Label r=new Label("reference");
                    TextField tfRef = new TextField(String.valueOf(p.getReference()));
                    Label ct=new Label("Catégorie");
                    System.out.println(p.getCategorie().toString());
                    Label e=new Label("Entrepot");
                    System.out.println(p.getEntrepot().toString());
                    String c = p.getCategorie().toString();
                    cmb.setSelectedItem(c);
                    
                    btn = new Button("modifier");
                    supp = new Button("suprrimer");
                    
                    ms.removeAll();
                    ms.addAll(l,tfName,m,tfMarque,q,tfQte,pr,tfPrix,r,tfRef,ct,cmb,btn,supp);

                    btn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                        Produit pmodifié = new Produit();
                        pmodifié.setId(p.getId());
                        pmodifié.setLibelle(tfName.getText());
                        System.out.println(tfName.getText());
                        pmodifié.setMarque(tfMarque.getText());
                        System.out.println(tfMarque.getText());
                        pmodifié.setReference(Integer.valueOf(tfRef.getText()));
                        //System.out.println(tfRef.getText());
                            if (Integer.valueOf(tfQte.getText())<=20){
                                pmodifié.setPrix(Float.valueOf(tfPrix.getText())*0.9);

                            }
                            else{
                        pmodifié.setPrix(Float.valueOf(tfPrix.getText()));}
                        //System.out.println(tfQte.getText());

                        pmodifié.setQuantite(Integer.valueOf(tfQte.getText()));
                       // System.out.println(tfPrix.getText());

                        Categorie c = anim.get(cmb.getSelectedIndex());
                        pmodifié.setCategorie(c);
                         //pmodifié.setCategorie(p.getCategorie());
                         //pmodifié.setEntrepot(p.getEntrepot());
                         ProduitService.getInstance().modifierProduit(pmodifié);
                         System.out.println("OOOOOK");
                         new ListProduitForm(previous);
                        }
                    });        

                    supp.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            ProduitService.getInstance().supprimerProduit(p);
                            new ListProduitForm(previous);
                          
                        }
                    });
                    
                    ms.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,a->showBack());
                    ms.refreshTheme();
                    ms.show(); 


                }
            });
            
            C2.add(img2);
            C2.add(l);
            C2.add(tel);
            if (p.getQuantite()<= 20){
            C2.add(pro);
                  
            }
            C2.setLeadComponent(l);
            
            this.clearClientProperties();
            this.add(C2);
            this.refreshTheme();  
        }
            
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        this.show();
    }
}
