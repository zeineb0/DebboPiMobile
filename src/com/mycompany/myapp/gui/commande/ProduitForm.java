/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.commande;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.URLImage.ImageAdapter;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.table.TableLayout;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Entrepot;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.service.commande.ProduitService;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author ghazi
 */
public class ProduitForm  extends Form{
    Form current;
    private SpanLabel lb;
    Form ms;
    private EncodedImage enc,encP,encUp,encDown,encN;
             private ComboBox cmb;
                private ComboBox cmbE;
                Button btn,supp;

 
    public ProduitForm() {
        current=this;
        setTitle("Liste des produits");
        this.setLayout(new TableLayout(CENTER,2));
        

        for (Produit p : ProduitService.getInstance().getAllProduit()){
            
            
             
            try {
                enc = EncodedImage.create("/debbo.png");
            } catch (IOException ex) {

            }
           Image i = (URLImage.createToStorage(enc.scaledEncoded(400, 400), p.getLibelle(), "http://localhost/DebboPiWeb/web/public/images/produits/" + p.getImage() +
                    "", URLImage.RESIZE_SCALE_TO_FILL));
              
            Container c = new Container(new TableLayout(CENTER, 1)); 
          
            
            Label mar = new Label (p.getMarque());
           double d = (double) Math.round(p.getPrix() * 100) / 100;
            Label prix = new Label (String.valueOf(d));
            Label qtt= new Label();
            qtt.setText("1");
            qtt.getUnselectedStyle().setAlignment(CENTER);
            qtt.getUnselectedStyle().setMargin(20, 20, 10, 150);
            try {
                encUp = EncodedImage.create("/up.png");
            } catch (IOException ex) {

            }
            Image up = (URLImage.createToStorage(encUp.scaledEncoded(70, 70), "down", "http://localhost/DebboPiWeb/web/public/images/produits/" + p.getImage() +
                    "", URLImage.RESIZE_SCALE_TO_FILL));
            Button btnUp= new Button(up);
            btnUp.getUnselectedStyle().setPadding(0, 0, 0, 0);
            try {
                encDown = EncodedImage.create("/down.png");
            } catch (IOException ex) {

            }
              Image down = (URLImage.createToStorage(encDown.scaledEncoded(70, 70), "up", "http://localhost/DebboPiWeb/web/public/images/produits/" + p.getImage() +
                    "", URLImage.RESIZE_SCALE_TO_FILL));
            Button btnDown= new Button(down);
            btnDown.getUnselectedStyle().setPadding(0, 0, 0, 0);
            btnUp.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                      if((Integer.parseInt(qtt.getText())<p.getQuantite()))
                      {qtt.setText((Integer.parseInt(qtt.getText())+1)+"");}
                        
                }
            });
            btnDown.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                     if((Integer.parseInt(qtt.getText())>1))
                    {qtt.setText((Integer.parseInt(qtt.getText())-1)+"");
                    } 
                }
            });
            Container c2 = new Container(BoxLayout.x()); 
            Container c3 = new Container(BoxLayout.y()); 
            
            c3.add(btnUp); c3.add(btnDown);
            
            c2.add(qtt);
            c2.add(c3);
            c.add(i);
            c.add(mar);
            c.add(prix);
            c.add(c2);
            Button btn= new Button("+ ajouter au panier");
            btn.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Statics.panier.ajoutProduit(p,Integer.parseInt(qtt.getText()));
                    Statics.panier.setTotal(Statics.panier.getTotal()+(p.getPrix()*Integer.parseInt(qtt.getText())));
                    
                }
            });
            Stroke borderStroke = new Stroke(3, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 3);  
          
            c.add(btn);
            
           
          this.add(c);
          c.getUnselectedStyle().setMargin(10, 10, 10, 10);
           c.getUnselectedStyle().setBorder(Border.createLineBorder(2, 0x000000)); 
            c.getUnselectedStyle().setPadding(10, 10, 10, 10);
             c.getUnselectedStyle().setBgColor(0xffffff);
          
          this.refreshTheme(); 
          
        }
          
            try {
                enc = EncodedImage.create("/panier.png");
            } catch (IOException ex) {

            }
           Image panier = (URLImage.createToStorage(enc.scaledEncoded(100, 100), "panier",
                   "", URLImage.RESIZE_SCALE_TO_FILL));
            try {
                encN = EncodedImage.create("/notif.png");
            } catch (IOException ex) {

            }
           Image notif = (URLImage.createToStorage(encN.scaledEncoded(100, 100), "notif",
                   "", URLImage.RESIZE_SCALE_TO_FILL));
       getToolbar().addCommandToRightBar("", panier , (evt) -> {
           if(!Statics.panier.getProduitCommande().isEmpty())
        { 
           new PanierForm(current).show();}
       else{
            Dialog.show("", "Votre Panier est Vide...", "Fermer","");
            
        }    
       });
        getToolbar().addCommandToRightBar("",  notif , (evt) -> {
          
           new NotificationForm(current).show();
        
       });
          getToolbar().addCommandToLeftSideMenu("commande",  null , (evt) -> {
          
           new CommandeForm(current).show();
        
       });  
    }
    
}
