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
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Contrat;
import com.mycompany.myapp.entities.Entrepot;
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
public class ContratService {
    
    
     public ArrayList<Contrat> Contrats;
     public ArrayList<Entrepot> entrepots;
    
    public static ContratService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    
    
    private ContratService() {
         req = new ConnectionRequest();
    }

    
     public static ContratService getInstance()
    {
        if (instance == null) {
            instance = new ContratService();
        }
        return instance;
    }
    
    
    
    
    public ArrayList<Contrat> parseContrat(String jsonText) 
    {
        try{
            Contrats = new ArrayList<>();
            JSONParser j = new JSONParser();
            
            Map<String,Object> ContratListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = ( List<Map<String,Object>>)ContratListJson.get("root");
            
            for( Map<String,Object> obj : list)
            {
                Contrat c = new Contrat();
                
                Map<String, Object> dateContratJson1 = (Map<String, Object>) obj.get("datedeb");
                float dateContratFloat1 = Float.parseFloat(dateContratJson1.get("timestamp").toString());
                Date dateContrat_deb = new Date((long) dateContratFloat1 * 1000);
                c.setDate_deb(dateContrat_deb);
                
                
                 Map<String, Object> dateContratJson2 = (Map<String, Object>) obj.get("datefin");
                float dateContratFloat2 = Float.parseFloat(dateContratJson2.get("timestamp").toString());
                Date dateContrat_fin = new Date((long) dateContratFloat2 * 1000);
                c.setDate_fin(dateContrat_fin);
                
                
                
                float id_entrepot = Float.parseFloat(obj.get("idEntrepot").toString());
                c.setFK_id_entrepot((int)id_entrepot);
                
                float id_transporteur = Float.parseFloat(obj.get("id").toString());
                c.setFK_id_transporteur((int)id_transporteur);
                
                c.setEntreprise(obj.get("entreprise").toString());
                
                c.setNom_transporteur(obj.get("nom").toString());
                
                c.setPrenom_transporteur(obj.get("prenom").toString());
                
                float salaire= Float.parseFloat(obj.get("salaire").toString());
                c.setSalaire((int)salaire);
                
                
                c.setImg("/contract2.png");
     
                Contrats.add(c);
               
            }
        }catch(IOException e)
                {
                    
                }
        return Contrats;
    }
    
    public ArrayList<Contrat> parseContratEXP(String jsonText) 
    {
        try{
            Contrats = new ArrayList<>();
            JSONParser j = new JSONParser();
            
            Map<String,Object> ContratListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = ( List<Map<String,Object>>)ContratListJson.get("root");
            
            for( Map<String,Object> obj : list)
            {
                Contrat c = new Contrat();
                
                Map<String, Object> dateContratJson1 = (Map<String, Object>) obj.get("datedeb");
                float dateContratFloat1 = Float.parseFloat(dateContratJson1.get("timestamp").toString());
                Date dateContrat_deb = new Date((long) dateContratFloat1 * 1000);
                c.setDate_deb(dateContrat_deb);
                
                
                 Map<String, Object> dateContratJson2 = (Map<String, Object>) obj.get("datefin");
                float dateContratFloat2 = Float.parseFloat(dateContratJson2.get("timestamp").toString());
                Date dateContrat_fin = new Date((long) dateContratFloat2 * 1000);
                c.setDate_fin(dateContrat_fin);
                
                
                
                float id_entrepot = Float.parseFloat(obj.get("idEntrepot").toString());
                c.setFK_id_entrepot((int)id_entrepot);
                
                c.setEntreprise(obj.get("entreprise").toString());
                
                c.setNom_transporteur(obj.get("nom").toString());
                
                c.setPrenom_transporteur(obj.get("prenom").toString());
                
                float salaire= Float.parseFloat(obj.get("salaire").toString());
                c.setSalaire((int)salaire);
            
                
                c.setImg("/exp.png");
     
                Contrats.add(c);
               
            }
        }catch(IOException e)
                {
                    
                }
        return Contrats;
    }
    
    
    
    public ArrayList<Contrat> getContrat()
    {
        String url = "http://localhost/DebboPiWeb/web/app_dev.php/Transporteur/affContrat/2";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Contrats = parseContrat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return Contrats;
        
    }
    
    
    public ArrayList<Contrat> getContratEXP()
    {
        String url = "http://localhost/DebboPiWeb/web/app_dev.php/Transporteur/affContratEXP/2";
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Contrats = parseContratEXP(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return Contrats;
        
    }
    
    
    
    public boolean supprimerContrat(String id_transpoteur,String id_entrepot)
    {
        String url = "http://localhost/DebboPiWeb/web/app_dev.php/Transporteur/suppContrat?id_entrepot="+id_entrepot+"&id_transporteur="+id_transpoteur;
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
    
     public ArrayList<Entrepot> parseEntrepot(String jsonText){
        try {
            entrepots=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            //User.setIdOfConnectedUser(0);
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                
                Entrepot e = new Entrepot();
                float id = Float.parseFloat(obj.get("idEntrepot").toString());
//                float prix = Float.parseFloat(obj.get("prix").toString());
                e.setId_entrepot((int)id);
                e.setEntreprise(obj.get("entreprise").toString());
               entrepots.add(e);
                //System.out.println("********");
            }
           
        } catch (IOException ex) {
            
        }
        return entrepots;
    }
     
     
     public ArrayList<Entrepot> getAllEntrepot(){
        String url ="http://localhost/DebboPiWeb/web/app_dev.php/Transporteur/getE";
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
