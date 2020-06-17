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
import com.codename1.facebook.Post;
import com.codename1.share.FacebookShare;
import com.codename1.ui.Dialog;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.exception.FacebookException;
import com.restfb.types.FacebookType;
/**
 *
 * @author ghazi
 */
public class DetailsCommandeForm  extends Form{
    Form current;
    private EncodedImage enc;
    double prix;
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
              prix= Double.parseDouble(obj.get("prixProduit").toString());   
              c2.add(""+ref);
              c2.add(marque);
              c2.add(""+qtt);
              c2.add(""+prix);
              c.add(c2);            
                         }
        
          Button partagerBtn = new Button("partager");
            partagerBtn.addActionListener((evt) -> {
                System.err.println("-_-_-_-_-__-_-_-_- : fbpartage");
                String accessToken = "EAADitfGMiOIBANfaoakY8QzIyJKsFjnvW5i5T98xKZBTJusyxrukTSJqCR5Fr7uqspEPZC1ZB0lEJdHs1sSUWE498qaEcY8p85CqrZCVLju90Y2FNhl4ZAjV0WmpEbHYMAiMA0iqXX4RXfKgIKQZCx2ZAaKfY1TQZBt5RZC1lY7hQlQD830bZANydq33efLYZAZBjhL2zEcb0KpQ1p3f8LVFZBkuZC";
                FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.VERSION_7_0);
                System.out.println(fbClient.toString());
            try{   
                // FacebookType response = fbClient.publish("me?fields=id,name", FacebookType.class);
               //  System.out.println(response);
            FacebookType response = fbClient.publish("me/feed", FacebookType.class,
                        Parameter.with("Ghazi Hachicha a commandé des produits de "+prix+"Dt", "Commande"));
             Dialog.show("Succes", "Votre Article à été partagé sur facebook", "Fermer", null);
            }catch(FacebookException f){
                Dialog.show("error", f.getMessage(), "Fermer", null);
            }
             //  System.out.println("fb.com/" + response.getId());
               
                
            });
            
            add(partagerBtn);
            add(new Label(""));
        } catch (IOException ex) {
            
        }
    
        
            
        
       
          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
      
       
        
    }
    
}

    
