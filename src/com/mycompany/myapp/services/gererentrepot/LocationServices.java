/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services.gererentrepot;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Entrepot;
import com.mycompany.myapp.entities.Location;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import com.mycompany.myapp.entities.User;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus
 */
public class LocationServices {
     public ArrayList<Location> locations;
    public Location location;
    public static LocationServices instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private LocationServices() {
         req = new ConnectionRequest();
    }

    public static LocationServices getInstance() {
        if (instance == null) {
            instance = new LocationServices();
        }
        return instance;
    }
    
    //ajouter Entrepot
    public boolean addLocation(Location l , int id) {
    String url = Statics.GestionEntrepot_URL + "locationM/new/"+id+"?dateDebLocation="+ Statics.simpleDateFormat1.format(l.getDate_deb_location())+"&dateFinLocation="+Statics.simpleDateFormat1.format(l.getDate_fin_location())+"&prixLocation="+l.getPrix_location()+ "&id="+Statics.getIdSession()+"&etat="+"En Attente";
            
            req.setUrl(url);
            System.out.println(url);
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
    public ArrayList<Location> parseLocation(String jsonText){
        try {
            locations=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> locationsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)locationsListJson.get("root");
            for(Map<String,Object> obj : list){
               Location l = new Location();
                float id = Float.parseFloat(obj.get("idLocation").toString());
                l.setId_location((int)id);
                Map<String, Object> dateDebLocationJson = (Map<String, Object>) obj.get("dateDebLocation");
                float datedebLoc = Float.parseFloat(dateDebLocationJson.get("timestamp").toString());
                Date dateDeb = new Date((long) datedebLoc * 1000);

                l.setDate_deb_location(dateDeb);
                Map<String, Object> dateFinLocationJson = (Map<String, Object>) obj.get("dateFinLocation");
                float dateFinLoc = Float.parseFloat(dateFinLocationJson.get("timestamp").toString());
                Date dateFin = new Date((long) dateFinLoc * 1000);
                String dat = Statics.simpleDateFormat.format(dateFin);

                l.setDate_fin_location(dateFin);
                float prix=Float.parseFloat(obj.get("prixLocation").toString());
               l.setPrix_location((float)prix);
            Map<String,Object> Entrepotlist = (Map<String,Object>)obj.get("fkEntrepot");
               Entrepot e = new Entrepot();
               e.setEntreprise(Entrepotlist.get("entreprise").toString());
               e.setAdresse_entrepot(Entrepotlist.get("adresse").toString());
               float quant=Float.parseFloat(Entrepotlist.get("quantiteMax").toString());
               e.setQuantite_max((int)quant);
               float num=Float.parseFloat(Entrepotlist.get("numFiscale").toString());
               e.setNum_fiscale((int)num);
              
              Map<String,Object> userlist = (Map<String,Object>)Entrepotlist.get("id");
                User u = new User();

               u.setNom((userlist.get("nom")).toString());
               u.setPrenom((userlist.get("prenom")).toString());
            
                e.setFk_id_fournisseur(u); 
                 l.setFK_id_entrepot(e);
                locations.add(l);
            }
            
            
        } catch (IOException ex) {
            
        }
        return locations;
    }
   
    
    public ArrayList<Location> getAllLocations(){
        String url = Statics.GestionEntrepot_URL+"locationM/?idUser="+Statics.getIdSession();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                locations = parseLocation(new String(req.getResponseData()));
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return locations;
    }
    

      //delete 
       public Boolean DeleteLocation(int idLocation){
        String url = Statics.GestionEntrepot_URL+"locationM/" +idLocation+"/delete";
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
    
}
