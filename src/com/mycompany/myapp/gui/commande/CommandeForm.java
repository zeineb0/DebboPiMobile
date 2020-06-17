/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.commande;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.service.commande.CommandeService;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ghazi
 */
public class CommandeForm  extends Form{
    Form current;
    private EncodedImage enc;

    public CommandeForm(Form previous) {
        current=this;
        
        setTitle("commande");
           this.setLayout(new TableLayout(CENTER, 4));
        
        Container lib = new Container(new TableLayout(CENTER, 1)); 
        lib.add(new Label("REFERENCE"));
        this.add(lib);
        Container marq = new Container(new TableLayout(CENTER, 1)); 
        marq.add(new Label("TOTAL"));
        this.add(marq);
        Container qtt = new Container(new TableLayout(CENTER, 1)); 
        qtt.add(new Label("DATE"));
        this.add(qtt);
        Container delete = new Container(new TableLayout(CENTER, 1)); 
        delete.add(new Label("DETAILS"));
        this.add(delete);
        
        
       ArrayList<Commande> commandes;
        CommandeService cs= new CommandeService();
        commandes=cs.getAllCommande();
        for(int i=0;i<commandes.size();i++){
        Container com = new Container(new TableLayout(CENTER, 1)); 
        com.add(new Label(""+commandes.get(i).getId_commande()));
        final int id=commandes.get(i).getId_commande();
        this.add(com);
        Container tot = new Container(new TableLayout(CENTER, 1)); 
        tot.add(new Label(""+Statics.convert(commandes.get(i).getTotal())));
        this.add(tot);
        String dat = Statics.simpleDateFormat.format(commandes.get(i).getDate_commande()); 
        Container date = new Container(new TableLayout(CENTER, 1)); 
        date.add(new Label(dat));
        this.add(date);
        Container view = new Container(new TableLayout(CENTER, 1)); 
          try {
                enc = EncodedImage.create("/eye.png");
            } catch (IOException ex) {

            }
            Image icon = (URLImage.createToStorage(enc.scaledEncoded(70, 70), "show", "http://localhost/DebboPiWeb/web/public/images/view" , URLImage.RESIZE_SCALE_TO_FILL));
        Button btn= new Button(icon);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
           new DetailsCommandeForm(current,id).show();
            }
        });
        view.add(btn);
        this.add(view);
        
        
            
        }
       
          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
      
       
        
    }
    
}
