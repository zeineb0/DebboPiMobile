/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author asus
 */
public class Location {
     int id_location;
    Date date_deb_location;
    Date date_fin_location;
    double prix_location;
   Entrepot FK_id_entrepot;
    User FK_id_user;

    public Location() {
    }

    public Location(int id_location, Date date_deb_location, Date date_fin_location, double prix_location, Entrepot FK_id_entrepot, User FK_id_user) {
        this.id_location = id_location;
        this.date_deb_location = date_deb_location;
        this.date_fin_location = date_fin_location;
        this.prix_location = prix_location;
        this.FK_id_entrepot = FK_id_entrepot;
        this.FK_id_user = FK_id_user;
    }

  
 public Location( Date date_deb_location, Date date_fin_location, double prix_location,  User FK_id_user) {
        this.date_deb_location = date_deb_location;
        this.date_fin_location = date_fin_location;
        this.prix_location = prix_location;
        this.FK_id_user = FK_id_user;
    }
  

    @Override
    public String toString() {
        return "Location{" + "id_location=" + id_location + ", date_deb_location=" + date_deb_location + ", date_fin_location=" + date_fin_location + ", prix_location=" + prix_location + ", FK_id_entrepot=" + FK_id_entrepot + ", FK_id_user=" + FK_id_user + '}';
    }

    public int getId_location() {
        return id_location;
    }

    public void setId_location(int id_location) {
        this.id_location = id_location;
    }

    public Date getDate_deb_location() {
        return date_deb_location;
    }

    public void setDate_deb_location(Date date_deb_location) {
        this.date_deb_location = date_deb_location;
    }

    public Date getDate_fin_location() {
        return date_fin_location;
    }

    public void setDate_fin_location(Date date_fin_location) {
        this.date_fin_location = date_fin_location;
    }

    public double getPrix_location() {
        return prix_location;
    }

    public void setPrix_location(double prix_location) {
        this.prix_location = prix_location;
    }

    public Entrepot getFK_id_entrepot() {
        return FK_id_entrepot;
    }

    public void setFK_id_entrepot(Entrepot FK_id_entrepot) {
        this.FK_id_entrepot = FK_id_entrepot;
    }

    public User getFK_id_user() {
        return FK_id_user;
    }

    public void setFK_id_user(User FK_id_user) {
        this.FK_id_user = FK_id_user;
    }

 
    
    
}
