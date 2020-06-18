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
     public conge cong;
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
                 Map<String, Object> dates = (Map<String, Object>) obj.get("datesortie");
                 float datess = Float.parseFloat(dates.get("timestamp").toString());
                Date l = new Date((long) datess * 1000);
                c.setDatesortie(l);
                  Map<String, Object> datea = (Map<String, Object>) obj.get("datearrive");
                 float dateaa = Float.parseFloat(datea.get("timestamp").toString());
                Date l1 = new Date((long) dateaa * 1000);
                c.setDatearrive(l1);
                 c.setType(obj.get("type").toString());
                 c.setRaison(obj.get("raison").toString());
                 c.setEtat(obj.get("etat").toString());
                 Employe e =new Employe();
                 e.setId_emp((int)Float.parseFloat(obj.get("fKIdEmp").toString()));        
                 c.setFK_id_emp(e);               
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
    
        public boolean modifierconge(conge c,int id){
        String url=Statics.RH_URL+"/modifierconge?id="+id+"&datearrive="+Statics.simpleDateFormat.format(c.getDatearrive())+"&datesortie="+Statics.simpleDateFormat.format(c.getDatesortie())+"&type="+c.getType()+"&raison="+c.getRaison();
       
        ConnectionRequest req=new ConnectionRequest(url);
        req.addResponseListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
    public conge addconge(conge c,long d){
        String url=Statics.RH_URL+"/new?datearrive="+Statics.simpleDateFormat.format(c.getDatearrive())+"&datesortie="+Statics.simpleDateFormat.format(c.getDatesortie())+"&type="+c.getType()+"&etat="+c.getEtat()+"&raison="+c.getRaison()+"&FKIdEmp="+c.getFK_id_emp().getId_emp()+"&d="+d;
        ConnectionRequest req=new ConnectionRequest(url);
        req.addResponseListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                cong = parseconge(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return cong;
    }
    
    public conge parseconge(String jsonText){
        try {
            cong = new conge();
            JSONParser j = new JSONParser();
            Map<String,Object> congslistJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
       //      Map<String, Object> obj = (Map<String, Object>) congslistJson.get("id");
         //        System.out.println(obj.toString());
//                 cong.setId((int)Float.parseFloat(obj.toString()));
                 
                  Map<String, Object> emp = (Map<String, Object>) congslistJson.get("fKIdEmp");
                   System.out.println(emp.get("idEmp").toString());
                  Employe e= new Employe((int)Float.parseFloat(emp.get("idEmp").toString()));
                  e.setNbcong((int)Float.parseFloat(emp.get("nbcong").toString()));
                  cong.setFK_id_emp(e);
//                 c.setDatearrive(obj.get("date").toString());
//                 c.setEtat(obj.get("etat").toString());
//                 c.setType(obj.get("type").toString());
               
             
        } catch (IOException ex) {

        }

        return cong;

    }
    
    
}
