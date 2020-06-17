/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.service.commande;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Entrepot;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.entities.User;
import static com.mycompany.myapp.services.ProduitService.instance;
import com.mycompany.myapp.services.user.UserService;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ghazi
 */
public class ProduitService {
    public ArrayList<Produit> produits;
    
    public static ProduitService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
     private ProduitService() {
         req = new ConnectionRequest();
    }

    public static ProduitService getInstance() {
        if (instance == null) {
            instance = new ProduitService();
        }
        return instance;
    }
    
    
      public ArrayList<Produit> parseProduit(String jsonText){
        try {
            produits=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
             UserService u = null;
            u.getLoggedInfos("ghazi001");       
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                
                Produit p = new Produit();
                float id = Float.parseFloat(obj.get("idProduit").toString());
                float prix = Float.parseFloat(obj.get("prix").toString());
                //p.setImage(obj.get("imageName").toString());
                p.setId((int)id);
                
                Map<String, Object> CategorieJson = (Map<String, Object>) obj.get("fkCategorie");
                Categorie c = new Categorie();
                
                float idc = Float.parseFloat(CategorieJson.get("idCategorie").toString());
                c.setId((int)idc);
                c.setNom(CategorieJson.get("nom").toString());
                
                Map<String, Object> EntJson = (Map<String, Object>) obj.get("fkEntrepot");
                // Label l = new Label(obj.get("libelle").toString());
                Entrepot e = new Entrepot();
                float ide = Float.parseFloat(EntJson.get("idEntrepot").toString());
                e.setId_entrepot((int)ide);
                e.setAdresse_entrepot(EntJson.get("adresse").toString());
                p.setPrix((prix));
                p.setCategorie(c);
                p.setEntrepot(e);
                float qte = Float.parseFloat(obj.get("quantite").toString());
                p.setQuantite((int)qte);
                float ref = Float.parseFloat(obj.get("reference").toString());
                p.setReference((int)ref);
                p.setLibelle(obj.get("libelle").toString());
                p.setMarque(obj.get("marque").toString());
                //c.setEntrepot((Entrepot)obj.get("fkEntrepot"));
               produits.add(p);
                //System.out.println("********");
            }
        } catch (IOException ex) {
            
        }
        return produits;
        
             
    }
    
       public ArrayList<Produit> getAllProduit(){
        String url = Statics.GHAZI_URL+"/allP";
           System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produits = parseProduit(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produits;
    }
}
