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
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class ListProduitForm extends Form{

    private SpanLabel lb;
    Form ms;
    private EncodedImage enc;
          private ComboBox cmb;
                private ComboBox cmbE;
                Button btn,supp;
                Container C2;

    public ListProduitForm() {
    }

    public ListProduitForm(Form previous) {
        setTitle("Liste des produits");
        this.setLayout(new TableLayout(2,CENTER));
          cmb = new ComboBox<>();
        ArrayList<Categorie> anim = new ArrayList<>();
        anim.addAll(CategoriesService.getInstance().getAllCategorie());

        for (Categorie object : anim) {
            cmb.addItem(object);
        }
          cmbE = new ComboBox<>();
      ArrayList<Entrepot> ent = new ArrayList<>();
        ent.addAll(EntrepotService.getInstance().getAllEntrepot());
         System.out.println(EntrepotService.getInstance().getAllEntrepot());

        for (Entrepot object : ent) {
            cmbE.addItem(object);
        }
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        
                //Container hii = new Container(new TableLayout(, 2));

        for (Produit p : ProduitService.getInstance().getAllProduit()){
            
            
               //Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            try {
                enc = EncodedImage.create("/a.png");
            } catch (IOException ex) {

            }

            Image i = (URLImage.createToStorage(enc.scaledEncoded(400,400), p.getLibelle(), "http://localhost/DebboWeb/web/public/images/produits/" + p.getImage() +
                    "", URLImage.RESIZE_SCALE_TO_FILL));

             C2 = new Container(new TableLayout(1, 1));
            
            Label l = new Label (p.getMarque());
            Label tel = new Label (String.valueOf(p.getReference()));
            l.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ms = new Form(BoxLayout.y());
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

                    cmbE.setSelectedItem(p.getEntrepot());
                    btn = new Button("modifier");
                    supp = new Button("suprrimer");
                    ms.addAll(l,tfName,m,tfMarque,q,tfQte,pr,tfPrix,r,tfRef,ct,cmb,e,cmbE,btn,supp);

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
                                                     System.out.println(tfRef.getText());

                         pmodifié.setPrix(Float.valueOf(tfPrix.getText()));
                                                     System.out.println(tfQte.getText());

                         pmodifié.setQuantite(Integer.valueOf(tfQte.getText()));
                                                     System.out.println(tfPrix.getText());

                         Categorie c = anim.get(cmb.getSelectedIndex());
                        pmodifié.setCategorie(c);
                        Entrepot e = ent.get(cmbE.getSelectedIndex());
                        pmodifié.setEntrepot(e);
                         //pmodifié.setCategorie(p.getCategorie());
                         //pmodifié.setEntrepot(p.getEntrepot());
                         ProduitService.getInstance().modifierProduit(pmodifié);
                                     System.out.println("OOOOOK");
                        }
                    });
        supp.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            ProduitService.getInstance().supprimerProduit(p);
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
                    ms.showBack();


                }
            });
                       C2.add(i);
                     //this.add(i);

            C2.add(l);
            C2.add(tel);
            //C1.add(C2);
            C2.setLeadComponent(l);
            
   this.clearClientProperties();
                  this.add(C2);
         // hii.add(C1);
          this.refreshTheme();  
        }
     
        
        /*SpanLabel sp = new SpanLabel();
        sp.setText(ProduitService.getInstance().getAllProduit().toString());
        add(sp);*/
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    //public static void add(Container c){};
}
