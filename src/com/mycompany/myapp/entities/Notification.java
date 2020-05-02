/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author ghazi
 */
public class Notification {
    private int id;
    private String title;
    private String description;
    private String icon;
    private String route;
    private String route_parameters;
    private Date notification_date;
    private int seen;
    private int idUser;

    public Notification() {
    }

    public Notification(int id, String title, String description, String icon, String route, String route_parameters, Date notification_date, int seen, int idUser) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.icon = icon;
        this.route = route;
        this.route_parameters = route_parameters;
        this.notification_date = notification_date;
        this.seen = seen;
        this.idUser = idUser;
    }
    
}
