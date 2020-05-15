/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.service.commande;

import com.mycompany.myapp.entities.Blog;
import com.codename1.io.ConnectionRequest;
import java.util.ArrayList;

/**
 *
 * @author ghazi
 */
public class NotificationService {
        public ArrayList<Blog> blogs;
       
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
}
