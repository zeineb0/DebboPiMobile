/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services.gererentrepot;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Entrepot;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus
 */
public class EntrepotServices {
    public ArrayList<Entrepot> entrepots;
    
    public static EntrepotServices instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private EntrepotServices() {
         req = new ConnectionRequest();
    }

    public static EntrepotServices getInstance() {
        if (instance == null) {
            instance = new EntrepotServices();
        }
        return instance;
    }

//    public boolean addEntrepot(Entrepot t) {
//        String url = Statics.GestionEntrepot_URL + "/newE?adresse=" + e.getName() + "&prix=" + e.getStatus()+"&entreprise="e;getEnt();
//        req.setUrl(url);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }

    public ArrayList<Entrepot> parseEntrepot(String jsonText){
        try {
            entrepots=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> entrepotsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)entrepotsListJson.get("root");
            for(Map<String,Object> obj : list){
                Entrepot e = new Entrepot();
                float id = Float.parseFloat(obj.get("idEntrepot").toString());
                e.setId_entrepot((int)id);
                e.setAdresse_entrepot((obj.get("adresse").toString()));
               // t.setName(obj.get("nom").toString());
                Map<String,Object> userlist = (Map<String,Object>)entrepotsListJson.get("id");

                
                entrepots.add(e);
            }
            
            
        } catch (IOException ex) {
            
        }
        return entrepots;
    }
    
    public ArrayList<Entrepot> getAllEntrepots(){
        String url = Statics.GestionEntrepot_URL+"entrepotM/";
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
}
