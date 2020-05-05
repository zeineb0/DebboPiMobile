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
      public ArrayList<Categorie> tasks;
    
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
    public boolean addTask(Categorie c) {
        String url = Statics.BASE_URL + "/new?nom=" + c.getNom(); //+ c.getEntrepot();
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
     public ArrayList<Categorie> parseCategorie(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                
                Categorie c = new Categorie();
                float id = Float.parseFloat(obj.get("idCategorie").toString());
                c.setId((int)id);
                c.setNom(obj.get("nom").toString());
                //c.setEntrepot((Entrepot)obj.get("fkEntrepot"));
                tasks.add(c);
            }
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
       public ArrayList<Categorie> getAllCategorie(){
        String url = Statics.BASE_URL+"/allC";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseCategorie(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
}
