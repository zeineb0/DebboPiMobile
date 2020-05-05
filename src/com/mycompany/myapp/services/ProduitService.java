/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zeineb_yahiaoui
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
    public boolean addProduit(Produit p) {
        String url = Statics.BASE_URL + "/new?nom=" + p.getLibelle(); //+ c.getEntrepot();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     public ArrayList<Produit> parseCategorie(String jsonText){
        try {
            produits=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                
                Produit p = new Produit();
                float id = Float.parseFloat(obj.get("idCategorie").toString());
                p.setId((int)id);
                p.setLibelle(obj.get("libelle").toString());
                //c.setEntrepot((Entrepot)obj.get("fkEntrepot"));
               produits.add(p);
            }
            
        } catch (IOException ex) {
            
        }
        return produits;
    }
       public ArrayList<Produit> getAllProduit(){
        String url = Statics.BASE_URL+"/allP";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produits = parseCategorie(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produits;
    }
    
}
