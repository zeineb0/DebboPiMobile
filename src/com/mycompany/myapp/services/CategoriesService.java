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
import com.mycompany.myapp.entities.Entrepot;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class CategoriesService {
      public ArrayList<Categorie> categories;
    
    public static CategoriesService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private CategoriesService() {
         req = new ConnectionRequest();
    }

    public static CategoriesService getInstance() {
        if (instance == null) {
            instance = new CategoriesService();
        }
        return instance;
    }
    public boolean addCategorie(Categorie c) {
        String url = Statics.BASE_URL + "/newC?nom=" + c.getNom()
                +"&fkEntrepot="+ c.getEntrepot().getId_entrepot();
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
        return resultOK;
    }
     public ArrayList<Categorie> parseCategorie(String jsonText){
        try {
            categories=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                
                Categorie c = new Categorie();
                float id = Float.parseFloat(obj.get("idCategorie").toString());
                c.setId((int)id);
                c.setNom(obj.get("nom").toString());
                c.setImage(obj.get("imageName").toString());
                Map<String, Object> EntJson = (Map<String, Object>) obj.get("fkEntrepot");
                Entrepot e = new Entrepot();
                float ide = Float.parseFloat(EntJson.get("idEntrepot").toString());
                e.setId_entrepot((int)ide);
                e.setAdresse_entrepot(EntJson.get("adresse").toString());
                c.setEntrepot(e);
                //c.setEntrepot((Entrepot)obj.get("fkEntrepot"));
                categories.add(c);
            }
                            System.out.println(categories);

        } catch (IOException ex) {
            
        }
        return categories;
    }
       public ArrayList<Categorie> getAllCategorie(){
        String url = Statics.BASE_URL+"/allC";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                categories = parseCategorie(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categories;
    }
       
          public void modifierCategorie(Categorie c) {

        String Url = Statics.BASE_URL + "/modifC?idCategorie=" + c.getId() + "&nom="+c.getNom()+"&fkEntrepot="+c.getEntrepot().getId_entrepot();
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

    public void supprimerCategorie(Categorie c) {
        String url = Statics.BASE_URL+"/suppC?idCategorie="+c.getId();
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
