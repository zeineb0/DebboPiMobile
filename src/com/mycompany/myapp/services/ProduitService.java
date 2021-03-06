/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.components.ImageViewer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Entrepot;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.gui.ListProduitForm;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
        
        if (p.getQuantite()<=20){
                String url = Statics.BASEZ_URL + "/newP?libelle="+p.getLibelle()+"&reference="+p.getReference()+"&promotion="+1
                        +"&prix="+p.getPrix()+"&marque="+p.getMarque()+"&quantite="+p.getQuantite()+"&fkCategorie="+p.getCategorie().getId()+"&idUser="+Statics.getIdSession();

        }
        String url = Statics.BASEZ_URL + "/newP?libelle="+p.getLibelle()+"&idUser="+Statics.getIdSession()+"&reference="+p.getReference()+"&prix="+p.getPrix()+"&marque="+p.getMarque()+"&quantite="+p.getQuantite()+"&fkCategorie="+p.getCategorie().getId();
        System.out.println(url);
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
     public ArrayList<Produit> parseProduit(String jsonText){
        try {
            produits=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            //User.setIdOfConnectedUser(0);
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                
                Produit p = new Produit();
                float id = Float.parseFloat(obj.get("idProduit").toString());
                float prix = Float.parseFloat(obj.get("prix").toString());
                p.setImage(obj.get("imageName").toString());
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
            }
        } catch (IOException ex) {
            
        }
        return produits;
        
             
    }
    
       public ArrayList<Produit> getAllProduit(){
        String url = Statics.BASEZ_URL+"/allP";
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
         public void modifierProduit(Produit p) {

        String Url = Statics.BASEZ_URL + "/modifP?idProduit=" + p.getId() + "&libelle="+p.getLibelle()+"&marque="+p.getMarque()+"&reference="+p.getReference()
                +"&prix="+p.getPrix()+"&quantite="+p.getQuantite()+"&fkCategorie="+p.getCategorie().getId()+"&fkEntrepot="+p.getEntrepot().getId_entrepot();
        req.setUrl(Url);
             System.out.println(Url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
 
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public void supprimerProduit(Produit p) {

        String url = Statics.BASEZ_URL+"/suppP?idProduit="+p.getId();
        req.setUrl(url);
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(req);

    }
}
