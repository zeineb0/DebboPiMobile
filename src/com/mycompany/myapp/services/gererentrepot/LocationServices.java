/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services.gererentrepot;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Location;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import com.mycompany.myapp.entities.User;

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
    String url = Statics.GestionEntrepot_URL + "LocationM/new/"+id+"?dateDebLocation="+ Statics.simpleDateFormat.format(l.getDate_deb_location())+"&dateFinLocation="+Statics.simpleDateFormat.format(l.getDate_fin_location())+"&prixLocation="+l.getPrix_location()+ "&id="+l.getFK_id_user().getId();
            
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
