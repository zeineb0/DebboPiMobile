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
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Entrepot;
import com.mycompany.myapp.entities.MouvementStock;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.CategoriesService;
import com.mycompany.myapp.services.EntrepotService;
import com.mycompany.myapp.services.MvtService;
import com.mycompany.myapp.services.ProduitService;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class ListMvtForm extends Form{
   Form ms;
    private EncodedImage enc;
          private ComboBox cmb;
                private ComboBox cmbE;
                Button btn,supp;
                Container C2;
    public ListMvtForm(Form previous) {
        setTitle("Liste des mvts");
        
       /* SpanLabel sp = new SpanLabel();
        sp.setText(MvtService.getInstance().getAllMvt().toString());
        add(sp);*/
        
        
        
        
         cmb = new ComboBox<>();
        ArrayList<Produit> anim = new ArrayList<>();
        anim.addAll(ProduitService.getInstance().getAllProduit());

        for (Produit object : anim) {
            cmb.addItem(object);
        }
          cmbE = new ComboBox<>();
      ArrayList<Entrepot> ent = new ArrayList<>();
        ent.addAll(EntrepotService.getInstance().getAllEntrepot());
         //System.out.println(EntrepotService.getInstance().getAllEntrepot());

        for (Entrepot object : ent) {
            cmbE.addItem(object);
        }
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        
                //Container hii = new Container(new TableLayout(, 2));

        for (MouvementStock m : MvtService.getInstance().getAllMvt()){
            
                         C2 = new Container(new TableLayout(2, 1));

               //Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            try {
                enc = EncodedImage.create("/tv.png");
            } catch (IOException ex) {

            }
            Produit p = m.getP();
            Image i = (URLImage.createToStorage(enc, p.getLibelle(), "http://localhost/DebboWeb/web/public/images/produits/" 
                    + p.getImage() +
                    "", URLImage.RESIZE_SCALE_TO_FILL));

                   ImageViewer img2 = new ImageViewer(i.fill(400, 400));
            
            Label l = new Label (m.getNatureDuStock());
            String dateeeee = String.valueOf(m.getDateMouv());
            Label l1 = new Label (dateeeee.substring(dateeeee.lastIndexOf(':')+1));
            Label ll1 = new Label (String.valueOf(m.getP()).toString());
            Picker d = new Picker();
           // Label tel = new Label (Date.valueOf(m.getDateMouv()));
            l.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ms = new Form(BoxLayout.y());
                    ms.setTitle("Mouvement");
                    Label l=new Label("Nature");
                    TextField tfName = new TextField(m.getNatureDuStock());
                    Label date=new Label("Date");
                     d.setDate(m.getDateMouv());
                    Label q=new Label("Quantité");
                    TextField tfQte = new TextField(String.valueOf(m.getQuantite()));
                    
                    Label e=new Label("Entrepot");
                    
                    for (Entrepot object : ent) {
                        Entrepot enn = m.getE();
                        Entrepot entt = ent.get(cmbE.getSelectedIndex());
                         cmbE.setSelectedItem(entt);
                            }
                                        Label p=new Label("Produit");

                    for (Produit object : anim) {
                        Produit proo=anim.get(cmb.getSelectedIndex());
                         cmb.setSelectedItem(m.getP());
                            }
                   // System.out.println(p.getEntrepot().toString());
                   
                    btn = new Button("modifier");
                    supp = new Button("suprrimer");
                    ms.removeAll();
                                        ms.addAll(l,tfName,date,d,q,tfQte,p,cmb,e,cmbE,btn,supp);


                btn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            MouvementStock modif = new MouvementStock();
                         //Produit pmodifié = new Produit();
                         modif.setId(m.getId());
                         modif.setNatureDuStock(tfName.getText());
                            System.out.println(tfName.getText());
                         modif.setDateMouv(d.getDate());
                         // System.out.println(tfMarque.getText());
                         modif.setQuantite(Integer.valueOf(tfQte.getText()));
                                                     System.out.println(tfQte.getText());

                         Produit pri = anim.get(cmb.getSelectedIndex());
                        modif.setP(pri);
                        Entrepot e = ent.get(cmbE.getSelectedIndex());
                        modif.setE(e);
                            System.out.println(modif);
                         //pmodifié.setCategorie(p.getCategorie());
                         //pmodifié.setEntrepot(p.getEntrepot());
                         MvtService.getInstance().modifierMvt(modif);
                                     System.out.println("OOOOOK");
                        new ListMvtForm(previous);
                        }
                    });
        supp.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            MvtService.getInstance().supprimerMvt(m);
                                                    new ListMvtForm(previous);

                          
                        }
                    });
        ms.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,a->showBack());
                       ms.refreshTheme();
                    ms.showBack();


                }
            });
                       C2.add(img2);
                     //this.add(i);

            C2.add(l);
            C2.addAll(l1,ll1);
            //C2.add(tel);
            //C1.add(C2);
            C2.setLeadComponent(l);
            
   this.clearClientProperties();
                  this.add(C2);
         // hii.add(C1);
          this.refreshTheme();  
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        this.show();
        
        
        
    }
    
}
