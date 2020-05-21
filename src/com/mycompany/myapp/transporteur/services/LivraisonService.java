/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.transporteur.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Livraison;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dell
 */
public class LivraisonService {
    
    public ArrayList<Livraison> livraisons;
    
    public static LivraisonService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    
    
    private LivraisonService() {
         req = new ConnectionRequest();
    }

    
     public static LivraisonService getInstance()
    {
        if (instance == null) {
            instance = new LivraisonService();
        }
        return instance;
    }
    
    
    
    
    
    
    
     public ArrayList<Livraison> parseLivraison(String jsonText) 
    {
        try{
            livraisons = new ArrayList<>();
            JSONParser j = new JSONParser();
            
            Map<String,Object> livraisonsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = ( List<Map<String,Object>>)livraisonsListJson.get("root");
            
            for( Map<String,Object> obj : list)
            {
                Livraison l = new Livraison();
                
                Map<String, Object> dateLivraisonJson = (Map<String, Object>) obj.get("dateLivraison");
                float dateLivraisonFloat = Float.parseFloat(dateLivraisonJson.get("timestamp").toString());
                Date dateLivraison = new Date((long) dateLivraisonFloat * 1000);
                l.setDate_livraison(dateLivraison);
                float id = Float.parseFloat(obj.get("idLivraison").toString());
                l.setId_livraison((int)id);
                l.setAdresse_livraison(obj.get("adresseLivraison").toString());
                l.setEtat_livraison(obj.get("etatLivraison").toString());
                l.setTel(obj.get("tel").toString());
                float id_c= Float.parseFloat(obj.get("idCommande").toString());
                l.setFK_id_commande((int)id_c);
                l.setImg("/liv.jpg");
                    
                
              
                livraisons.add(l);
               
            }
        }catch(IOException e)
                {
                    
                }
        return livraisons;
    }
    
    
    
    
    public ArrayList<Livraison> getLivraisons()
    {
        String url = "http://localhost/DebboPiWeb/web/app_dev.php/Transporteur/affLivL/1";
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                livraisons = parseLivraison(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return livraisons;
        
    }
    
    public boolean supprimerLivraison(String id_liv)
    {
        String url = "http://localhost/DebboPiWeb/web/app_dev.php/Transporteur/suppLiv/"+id_liv;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               resultOK =req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
        
    }
    
    public boolean modifierLivraison(Livraison l) {
            String url ="http://localhost/DebboPiWeb/web/app_dev.php/Transporteur/modLiv?id_liv="+l.getId_livraison()+"&date="+l.getDate_livraison()+"&id_user="+1;
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
    
    
    
    
    
    
    
    
    
}
