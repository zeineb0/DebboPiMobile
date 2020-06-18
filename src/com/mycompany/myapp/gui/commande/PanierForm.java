/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.commande;

import com.codename1.components.InteractionDialog;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;
import com.mycompany.myapp.entities.Panier;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.entities.ProduitCommande;
import com.mycompany.myapp.service.commande.CommandeService;
import com.mycompany.myapp.service.commande.NotificationService;
import com.mycompany.myapp.utils.Notifications;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author ghazi
 */
public class PanierForm extends Form{
    Form current;
    public PanierForm(Form previous) {
    
            current=this;
        setTitle("Panier");
            this.setLayout(new TableLayout(CENTER, 5));
        
        Container lib = new Container(new TableLayout(CENTER, 1)); 
        lib.add(new Label("LIBELLE"));
        this.add(lib);
        Container marq = new Container(new TableLayout(CENTER, 1)); 
        marq.add(new Label("MARQUE"));
        this.add(marq);
        Container prix = new Container(new TableLayout(CENTER, 1)); 
        prix.add(new Label("PRIX"));
        this.add(prix);
        Container qtt = new Container(new TableLayout(CENTER, 1)); 
        qtt.add(new Label("QTT"));
        this.add(qtt);
        Container delete = new Container(new TableLayout(CENTER, 1)); 
        delete.add(new Label(" "));
        this.add(delete);
            
        for(int i=0;i<Statics.panier.getProduitCommande().size();i++){
            ProduitCommande pc= Statics.panier.getProduitCommande().get(i);
        lib = new Container(new TableLayout(CENTER, 1)); 
        lib.add(new Label(""+pc.getId_produit().getLibelle()));
        this.add(lib);
        marq = new Container(new TableLayout(CENTER, 1)); 
        marq.add(new Label(""+pc.getId_produit().getMarque()));
        this.add(marq);
        prix = new Container(new TableLayout(CENTER, 1)); 
        prix.add(new Label(""+Statics.convert(pc.getPrix_produit())));
        this.add(prix);
        qtt = new Container(new TableLayout(CENTER, 1)); 
        qtt.add(new Label(""+(int)pc.getQuantite_produit()));
        this.add(qtt);
        delete = new Container(new TableLayout(CENTER, 1)); 
        
        Button remove= new Button("X");
            remove.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Statics.panier.SupprimerProduit(pc);
                    if(Statics.panier.getProduitCommande().isEmpty())
                    { previous.showBack();
                     Dialog.show("", "Votre Panier est Vide...", "Fermer","");
                    }   
                    else{
                    new PanierForm(previous).show();  }
                }
            });
        this.add(remove);
            
       
        }
        Container c = new Container(BoxLayout.y()); 
        c.add("************");
        c.add(new Label("Total : "+Statics.convert(Statics.panier.getTotal())));
        c.add("************");
        Button btn= new Button("passer Commande");
            btn.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
       
        Boolean b=Dialog.show("Passer Commande", "Voulez vous passer cette commande...", "Passer", "Annuler");

            if(b){
                CommandeService cs = new CommandeService();
                    if(cs.passerCommande())
                    {
                        Statics.panier= new Panier(8);
                        previous.showBack();
                        Dialog.show("Succées", "Votre commande a été passer avec succéés...", "Fermer","");
                    }
                    
                }
                         
            }
        });
        c.add(btn);
        this.add(c);
       
      
        
        
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
       
        }   
    
   
    
}
