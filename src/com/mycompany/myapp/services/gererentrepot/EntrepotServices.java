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
import com.mycompany.myapp.entities.User;
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
    public Entrepot entrepot;
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
//ajouter Entrepot
    public boolean addEntrepot(Entrepot e) {
    String url = Statics.GestionEntrepot_URL + "entrepotM/new?adresse=" +e.getAdresse_entrepot()+"&numFiscale="+e.getNum_fiscale()+"&quantiteMax="+e.getQuantite_max()+"&etat="+e.getEtat()+"&prixLocation="+e.getPrix_location()+"&entreprise="+e.getEntreprise()+
                    "&id="+e.getFk_id_fournisseur().getId();
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
    //Modifier Entrepot
    public boolean modifierEntrepot(int idEntrepot, Entrepot e) {
    String url = Statics.GestionEntrepot_URL + "entrepotM/"+idEntrepot+"/edit?etat="+e.getEtat()+"&prixLocation="+e.getPrix_location()+"&entreprise="+e.getEntreprise()     ;
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
//Affichage Entrepot
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
                float num=Float.parseFloat(obj.get("numFiscale").toString());
               e.setNum_fiscale((int)num);
               float quant=Float.parseFloat(obj.get("quantiteMax").toString());
               e.setQuantite_max((int)quant);
               e.setEtat((obj.get("etat").toString()));
               float prix=Float.parseFloat(obj.get("prixLocation").toString());
               e.setPrix_location((float)prix);
               e.setEntreprise((obj.get("entreprise").toString()));

              Map<String,Object> userlist = (Map<String,Object>)obj.get("id");
                User u = new User();

               u.setNom((userlist.get("nom")).toString());
               u.setPrenom((userlist.get("prenom")).toString());
            
                e.setFk_id_fournisseur(u); 
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
    //entrepot details
    public Entrepot parseDetailsEntrepot(String jsonText){
        try {
           // System.out.println(jsonText);
            entrepot=new Entrepot();
            JSONParser j = new JSONParser();
            //System.out.println("1");
            Map<String,Object> entrepotsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                       
                entrepot.setAdresse_entrepot((entrepotsListJson.get("adresse").toString()));
                float num=Float.parseFloat(entrepotsListJson.get("numFiscale").toString());
               entrepot.setNum_fiscale((int)num);
               float quant=Float.parseFloat(entrepotsListJson.get("quantiteMax").toString());
               entrepot.setQuantite_max((int)quant);
               entrepot.setEtat((entrepotsListJson.get("etat").toString()));
               float prix=Float.parseFloat(entrepotsListJson.get("prixLocation").toString());
               entrepot.setPrix_location((float)prix);
               entrepot.setEntreprise((entrepotsListJson.get("entreprise").toString()));

              Map<String,Object> userlist = (Map<String,Object>)entrepotsListJson.get("id");
                User u = new User();

               u.setNom((userlist.get("nom")).toString());
               u.setPrenom((userlist.get("prenom")).toString());
            
                entrepot.setFk_id_fournisseur(u); 
              
            
          
            
        } catch (IOException ex) {
            
        }
        return entrepot;
    }
    
    
      public Entrepot getDetailEntrepots(int idEntrepot){
        String url = Statics.GestionEntrepot_URL+"entrepotM/" +idEntrepot;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println(new String(req.getResponseData()));
                entrepot = parseDetailsEntrepot(new String(req.getResponseData()));
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return entrepot;
    }
      //delete 
       public Boolean DeleteEntrepot(int idEntrepot){
        String url = Statics.GestionEntrepot_URL+"entrepotM/" +idEntrepot+"/delete";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK=req.getResponseCode()==200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    //affichage entrepot a louer
       public ArrayList<Entrepot> parseALouerEntrepot(String jsonText){
        try {
            entrepots=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> entrepotsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)entrepotsListJson.get("root");
            for(Map<String,Object> obj : list){
                Entrepot e = new Entrepot();
                e.setAdresse_entrepot((obj.get("adresse").toString()));
//                float num=Float.parseFloat(obj.get("numFiscale").toString());
//               e.setNum_fiscale((int)num);
//               float quant=Float.parseFloat(obj.get("quantiteMax").toString());
//               e.setQuantite_max((int)quant);
//               e.setEtat((obj.get("etat").toString()));
               float prix=Float.parseFloat(obj.get("prixLocation").toString());
               e.setPrix_location((float)prix);
               e.setEntreprise((obj.get("entreprise").toString()));

               
//                Map<String,Object> userlist = (Map<String,Object>)obj.get("id");
//                User u = new User();
//               u.setNom((userlist.get("nom")).toString());
//               u.setPrenom((userlist.get("prenom")).toString());
//                e.setFk_id_fournisseur(u); 
                entrepots.add(e);
            }
            
            
        } catch (IOException ex) {
            
        }
        return entrepots;
    }
    
    public ArrayList<Entrepot> getALouerEntrepots(){
        String url = Statics.GestionEntrepot_URL+"entrepotM/alouer?idUser="+4;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                entrepots = parseALouerEntrepot(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return entrepots;
    }
     //affichage entrepot Loué
       public ArrayList<Entrepot> parseLoueEntrepot(String jsonText){
        try {
            entrepots=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> entrepotsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)entrepotsListJson.get("root");
            for(Map<String,Object> obj : list){
                Entrepot e = new Entrepot();
                e.setAdresse_entrepot((obj.get("adresse").toString()));
                float num=Float.parseFloat(obj.get("numFiscale").toString());
               e.setNum_fiscale((int)num);
               float quant=Float.parseFloat(obj.get("quantiteMax").toString());
               e.setQuantite_max((int)quant);
               e.setEtat((obj.get("etat").toString()));
               float prix=Float.parseFloat(obj.get("prixLocation").toString());
               e.setPrix_location((float)prix);
               e.setEntreprise((obj.get("entreprise").toString()));

               
                Map<String,Object> userlist = (Map<String,Object>)obj.get("id");
                User u = new User();
               u.setNom((userlist.get("nom")).toString());
               u.setPrenom((userlist.get("prenom")).toString());
                e.setFk_id_fournisseur(u); 
                entrepots.add(e);
            }
            
            
        } catch (IOException ex) {
            
        }
        return entrepots;
    }
    
    public ArrayList<Entrepot> getLoueEntrepots(){
        String url = Statics.GestionEntrepot_URL+"entrepotM/loue?idUser="+4;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                entrepots = parseLoueEntrepot(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return entrepots;
    }
    
    
    
}
