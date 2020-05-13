/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.CategoriesService;
import com.mycompany.myapp.services.ProduitService;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class ListProduitForm extends Form{

    private SpanLabel lb;
    private EncodedImage enc;

    public ListProduitForm(Form previous) {
        setTitle("Liste des produits");
        
        
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        
        
        for (Produit p : ProduitService.getInstance().getAllProduit()){
            
            
               Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            try {
                enc = EncodedImage.create("/a.png");
            } catch (IOException ex) {

            }

            Image i = (URLImage.createToStorage(enc, p.getLibelle(), "http://localhost/DebboWeb/web/public/images/produits/" + p.getImage() + "", URLImage.RESIZE_SCALE));

            Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            Label l = new Label (p.getMarque());
            Label tel = new Label (String.valueOf(p.getPrix()));
            l.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Dialog.show(p.getLibelle(), p.getMarque(),"ok",null);
                }
            });
            C2.add(l);
            C2.add(tel);
            C1.add(i);
            C1.add(C2);
            C1.setLeadComponent(l);
            
            
            
          this.add(C1);
          this.refreshTheme();
          
   
          
        }
        
        
        /*SpanLabel sp = new SpanLabel();
        sp.setText(ProduitService.getInstance().getAllProduit().toString());
        add(sp);*/
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    //public static void add(Container c){};
}
