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
import com.mycompany.myapp.entities.Entrepot;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class EntrepotService {
     public ArrayList<Entrepot> entrepots;
    
    public static EntrepotService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private EntrepotService() {
         req = new ConnectionRequest();
    }

    public static EntrepotService getInstance() {
        if (instance == null) {
            instance = new EntrepotService();
        }
        return instance;
    }
    public ArrayList<Entrepot> getAllEntrepot(){
        String url = Statics.BASE_URL+"/allE";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                entrepots = parseEntrepot(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return entrepots;
    }
         public ArrayList<Entrepot> parseEntrepot(String jsonText){
        try {
            entrepots=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            User.setIdOfConnectedUser(0);
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                
                Entrepot e = new Entrepot();
                float id = Float.parseFloat(obj.get("idEntrepot").toString());
//                float prix = Float.parseFloat(obj.get("prix").toString());
                e.setId_entrepot((int)id);
                e.setAdresse_entrepot(obj.get("adresse").toString());
               entrepots.add(e);
                //System.out.println("********");
            }
           
        } catch (IOException ex) {
            
        }
        return entrepots;
    }
}
