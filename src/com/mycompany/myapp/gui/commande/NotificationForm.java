/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.commande;

import com.codename1.io.ConnectionRequest;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Blog;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.service.commande.CommandeService;
import com.mycompany.myapp.service.commande.NotificationService;
import com.mycompany.myapp.utils.Notifications;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author ghazi
 */
public class NotificationForm  extends Form{
    Form current;
    public NotificationForm() {
        current=this;
        setTitle("Notification");
        setLayout(BoxLayout.y());
       ArrayList<Notifications> notifs;
        NotificationService cs= new NotificationService();
        notifs=cs.getNotification();
        for(int i=0;i<notifs.size();i++){
        add(new Label(""+notifs.get(i).getBlog().getTitle()));
        add(new Label(""+notifs.get(i).getBlog().getDescription()));
        add(new Label(""+notifs.get(i).getBlog().getAuteur()));
        String date = Statics.simpleDateFormat.format(notifs.get(i).getNotification().getNotification_date());
        add(new Label(date));
        }   
    
    }
}
