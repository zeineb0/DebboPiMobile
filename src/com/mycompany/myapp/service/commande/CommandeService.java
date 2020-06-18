/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.service.commande;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import static com.codename1.io.JSONParser.mapToJson;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.processing.Result;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.Panier;
import com.mycompany.myapp.entities.ProduitCommande;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ghazi
 */
public class CommandeService {
 

     public ArrayList<Commande> commandes;
     public Commande commande;
     public String retour;
    public static CommandeService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public CommandeService() {
         req = new ConnectionRequest();
    }

    public static CommandeService getInstance() {
        if (instance == null) {
            instance = new CommandeService();
        }
        return instance;
    }
    
    
    public Commande parseCommande(String jsonText){
        commande= new Commande();
        try {
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                
                
                double total= Double.parseDouble(obj.get("total").toString());
                Map<String, Object> dateCommandeJson = (Map<String, Object>) obj.get("dateCommande");
                float dateCommandeFloat = Float.parseFloat(dateCommandeJson.get("timestamp").toString());
                Date dateCommande = new Date((long) dateCommandeFloat * 1000);
          
    
                commande.setTotal(total);
                commande.setDate_commande(dateCommande);
                
                         }
        } catch (IOException ex) {
            
        }
             return commande;        
    }
    
      public ArrayList<Commande> parseCommandes(String jsonText){
        try {
            commandes=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                
                Commande c = new Commande();
                int idC = Integer.parseInt(obj.get("idCommande").toString().substring(0,obj.get("idCommande").toString().indexOf('.') ));
                double total= Double.parseDouble(obj.get("total").toString());
                Map<String, Object> dateCommandeJson = (Map<String, Object>) obj.get("dateCommande");
                float dateCommandeFloat = Float.parseFloat(dateCommandeJson.get("timestamp").toString());
                Date dateCommande = new Date((long) dateCommandeFloat * 1000);
          
                c.setId_commande(idC);
                c.setTotal(total);
                c.setDate_commande(dateCommande);
                commandes.add(c);
                         }
        } catch (IOException ex) {
            
        }
             return commandes;        
    }
    
    
    
     public ArrayList<Commande> getAllCommande(){
        String url = Statics.GHAZI_URL+"/commande/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commandes = parseCommandes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return commandes;
    }
    
        public String getCommande(int id){
        String url = Statics.GHAZI_URL+"/commande/"+id+"/";
        req.setUrl(url);
            System.out.println(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                retour=new String(req.getResponseData());
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return retour;
    }
        
        
        public boolean passerCommande(){
            Panier p= Statics.panier;
            String produit="";
            for(ProduitCommande pc: p.getProduitCommande()){
                produit+=pc.getId_produit().getId()+"-"+pc.getQuantite_produit()+"-"+Statics.convert(pc.getPrix_produit())+"_";
            }
            String url= Statics.GHAZI_URL+"/newCommande?total=" +p.getTotal()+"&idU="+p.getIdUser()+"&produits="+produit;
            
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
