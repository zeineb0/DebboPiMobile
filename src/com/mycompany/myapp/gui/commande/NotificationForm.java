/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.commande;

import com.codename1.io.ConnectionRequest;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
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
    public NotificationForm(Form previous) {
        current=this;
        setTitle("Notification");
         setLayout(BoxLayout.y());
       ArrayList<Notifications> notifs;
        NotificationService cs= new NotificationService();
        notifs=cs.getNotification();
        for(int i=0;i<notifs.size();i++){
        add(new Label(""+notifs.get(i).getBlog().getTitle()));
        Container c= new Container(BoxLayout.x());
        c.add(new Label(""+notifs.get(i).getBlog().getAuteur()));
        c.add(new Label(""+notifs.get(i).getBlog().getDescription()));
        add(c);
        String date = Statics.simpleDateFormat.format(notifs.get(i).getNotification().getNotification_date());
        Label d=new Label(date);
        d.getUnselectedStyle().setBorder(Border.createCompoundBorder(null, Border.createLineBorder(2, 0x000000),null, null));
        add(d);
        }   
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
}
