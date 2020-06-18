/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.service.commande;

import com.codename1.io.CharArrayReader;
import com.mycompany.myapp.entities.Blog;
import com.mycompany.myapp.entities.Notification;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.utils.Statics;
import com.mycompany.myapp.utils.Notifications;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ghazi
 */
public class NotificationService {
        public ArrayList<Notifications> blogs;
       
    public static NotificationService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public NotificationService() {
         req = new ConnectionRequest();
    }

    public static NotificationService getInstance() {
        if (instance == null) {
            instance = new NotificationService();
        }
        return instance;
    }
    
     public ArrayList<Notifications> parseNotification(String jsonText){
        try {
            blogs=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Notification n = new Notification();
                Blog b = new Blog();
                int idB = Integer.parseInt(obj.get("id").toString().substring(0,obj.get("id").toString().indexOf('.') ));
                String title= obj.get("title").toString();
                String desc= obj.get("description").toString();
                String auteur= obj.get("auteur").toString();
                b.setId(idB);
                b.setTitle(title);
                b.setDescription(desc);
                b.setAuteur(auteur);
                Map<String, Object> dateNotifJson = (Map<String, Object>) obj.get("date");
                float dateNotifFloat = Float.parseFloat(dateNotifJson.get("timestamp").toString());
                Date notification_date = new Date((long) dateNotifFloat * 1000);
                n.setId(idB);
                n.setNotification_date(notification_date);
                
                Notifications Nb = new Notifications(b, n);
                blogs.add(Nb);
                
                         }
        } catch (IOException ex) {
            
        }
             return blogs;        
    }
    
    
    
     public ArrayList<Notifications> getNotification(){
        String url = Statics.GHAZI_URL+"/notification/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                blogs = parseNotification(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return blogs;
    }
    
}
