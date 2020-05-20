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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Employe;
import com.mycompany.myapp.entities.conge;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author ASUS X550V
 */
public class RhService {
    public static RhService instance;
    private ConnectionRequest req;
    public ArrayList<conge> congs;
    public boolean resultOK;

    public RhService() {
        req=new ConnectionRequest();
    }
    public static RhService getInstance(){
        if ( instance == null )
           instance=new RhService();
        return instance;
    }
        public ArrayList<conge> parseconges(String jsonText){
        try {
            congs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> congslistJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list =(List<Map<String,Object>>)congslistJson.get("root");
             for(Map<String,Object> obj : list)
             {
                 conge c =new conge();
                 c.setId((int)Float.parseFloat(obj.get("id").toString()));
                c.setDatearrive(obj.get("date").toString());
                // c.setEtat(obj.get("etat").toString());
                // c.setType(obj.get("type").toString());
                 
   
                congs.add(c);
             }
        } catch (IOException ex) {

        }
                    return congs;

    }
    
    public ArrayList<conge> getAllconges(){
        String url=Statics.RH_URL+"/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                congs = parseconges(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return congs;
    }
    
    public boolean addconge(conge c){
        String url=Statics.RH_URL+"/new?datearrive="+c.getDatearrive()+"&datesortie="+c.getDatesortie()+"&type="+c.getType()+"&raison="+c.getRaison()+"&FKIdEmp="+c.getFK_id_emp()+"&etat="+c.getEtat();
        ConnectionRequest req=new ConnectionRequest(url);
        req.addResponseListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                resultOK=req.getResponseCode()==200;
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
}
