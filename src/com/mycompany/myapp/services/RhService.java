/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.ConnectionRequest;

/**
 *
 * @author ASUS X550V
 */
public class RhService {
    public static RhService instance;
    private ConnectionRequest req;

    public RhService() {
        req=new ConnectionRequest();
    }
    public static RhService getInstance(){
        if ( instance == null )
           instance=new RhService();
        return instance;
    }
    
    
    
}
