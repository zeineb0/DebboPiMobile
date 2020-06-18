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
   

    public Notification() {
    }

    public Notification(int id, String title, String description, String icon, String route, String route_parameters, Date notification_date, int seen) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.icon = icon;
        this.route = route;
        this.route_parameters = route_parameters;
        this.notification_date = notification_date;
        this.seen = seen;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getRoute_parameters() {
        return route_parameters;
    }

    public void setRoute_parameters(String route_parameters) {
        this.route_parameters = route_parameters;
    }

    public Date getNotification_date() {
        return notification_date;
    }

    public void setNotification_date(Date notification_date) {
        this.notification_date = notification_date;
    }

    public int getSeen() {
        return seen;
    }

    public void setSeen(int seen) {
        this.seen = seen;
    }
    
}
