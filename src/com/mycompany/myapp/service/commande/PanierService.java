/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.service.commande;

import com.codename1.io.ConnectionRequest;
import com.codename1.ui.Form;
import com.mycompany.myapp.entities.Panier;
import com.mycompany.myapp.entities.Produit;

/**
 *
 * @author ghazi
 */
public class PanierService extends Form{
    
       public static PanierService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public PanierService() {
         req = new ConnectionRequest();
    }

    public static PanierService getInstance() {
        if (instance == null) {
            instance = new PanierService();
        }
        return instance;
    }
    
}
