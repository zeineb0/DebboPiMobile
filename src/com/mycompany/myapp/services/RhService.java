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
import com.mycompany.myapp.entities.Employe;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author ASUS X550V
 */
public class RhService {
    public static RhService instance;
    private ConnectionRequest req;
    public ArrayList<Employe> emps;

    private RhService() {
        req=new ConnectionRequest();
    }
    public static RhService getInstance(){
        if ( instance == null )
           instance=new RhService();
        return instance;
    }
        public ArrayList<Employe> parseEmployes(String jsonText){
        try {
            emps = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> EmpsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list =(List<Map<String,Object>>)EmpsListJson.get("root");
             for(Map<String,Object> obj : list)
             {
                 Employe e =new Employe();
                 float id =Float.parseFloat(obj.get("id_emp").toString());
                 e.setId_emp((int) id);
             }
        } catch (IOException ex) {

        }
                    return emps;

    }
    
    public ArrayList<Employe> getAllEmployes(){
        String url=Statics.RH_URL+"/emp/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                emps = parseEmployes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return emps;
    }
    
    
    
    
}
