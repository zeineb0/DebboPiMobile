/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.commande;

import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.service.commande.CommandeService;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ghazi
 */
public class DetailsCommandeForm  extends Form{
    Form current;
    private EncodedImage enc;
    public DetailsCommandeForm(Form previous,int id) {
        current=this;
        
        setTitle("commande");
           
        
        
        
        
    
        CommandeService cs= new CommandeService();
        String jsonText =cs.getCommande(id);
       try {
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            Container c = new Container(BoxLayout.y()); 
        
                double total= Double.parseDouble(list.get(0).get("total").toString());
                Map<String, Object> dateCommandeJson = (Map<String, Object>)list.get(0).get("dateCommande");
                float dateCommandeFloat = Float.parseFloat(dateCommandeJson.get("timestamp").toString());
                Date dateCommande = new Date((long) dateCommandeFloat * 1000);
                Map<String, Object> dateCommandeExpJson = (Map<String, Object>)list.get(0).get("dateExp");
                float dateCommandeExp = Float.parseFloat(dateCommandeExpJson.get("timestamp").toString());
                Date dateExp = new Date((long) dateCommandeExp * 1000);
                c.add(new Label("REFERENCE:" + "2211548"));
                c.add(new Label("TOTAL: "+Statics.convert(total)));
                String dat = Statics.simpleDateFormat.format(dateCommande); 
                c.add(new Label("DATE: "+dat));
                dat = Statics.simpleDateFormat.format(dateExp);    
                c.add(new Label("DELAIS: "+dat));
                c.add(new Label("____________________________________________"));
                this.add(c);
                
                  Container c2 = new Container(BoxLayout.x()); 
        c2.add(new Label("REFERENCE"));
        c2.add(new Label("MARQUE"));
        c2.add(new Label("PRIX"));
        c2.add(new Label("QTT"));
        c.add(c2);
        
            for(Map<String,Object> obj : list){
              c2 = new Container(BoxLayout.x()); 
             String ref=(obj.get("reference").toString());
             String marque=obj.get("marque").toString();
             double qtt= Double.parseDouble(obj.get("quantiteProduit").toString());   
             double prix= Double.parseDouble(obj.get("prixProduit").toString());   
              c2.add(""+ref);
              c2.add(marque);
              c2.add(""+qtt);
              c2.add(""+prix);
              c.add(c2);            
                         }
            
            
        } catch (IOException ex) {
            
        }
       // Container com = new Container(new TableLayout(CENTER, 1)); 
      //  com.add(new Label(""+commandes.getId_commande()));
       // this.add(com);
       
      //  Container view = new Container(new TableLayout(CENTER, 1)); 
       //   try {
      //          enc = EncodedImage.create("/eye.png");
      //      } catch (IOException ex) {

      //      }
      //      Image icon = (URLImage.createToStorage(enc.scaledEncoded(70, 70), "show", "" , URLImage.RESIZE_SCALE_TO_FILL));
      //  Button btn= new Button(icon);
     //   view.add(btn);
      //  this.add(view);
        
        
            
        
       
          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
      
       
        
    }
    
}

    
