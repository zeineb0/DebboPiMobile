/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author Dell
 */
public class Contrat {
    
    private int salaire,FK_id_user,FK_id_entrepot;
    private Date date_fin,date_deb;

    public Contrat(int salaire, int FK_id_user, int FK_id_entrepot, Date date_fin, Date date_deb) {
        this.salaire = salaire;
        this.FK_id_user = FK_id_user;
        this.FK_id_entrepot = FK_id_entrepot;
        this.date_fin = date_fin;
        this.date_deb = date_deb;
    }

    public int getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }

    public int getFK_id_user() {
        return FK_id_user;
    }

    public void setFK_id_user(int FK_id_user) {
        this.FK_id_user = FK_id_user;
    }

    public int getFK_id_entrepot() {
        return FK_id_entrepot;
    }

    public void setFK_id_entrepot(int FK_id_entrepot) {
        this.FK_id_entrepot = FK_id_entrepot;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public Date getDate_deb() {
        return date_deb;
    }

    public void setDate_deb(Date date_deb) {
        this.date_deb = date_deb;
    }

    @Override
    public String toString() {
        return "Contrat{" + "salaire=" + salaire + ", FK_id_user=" + FK_id_user + ", FK_id_entrepot=" + FK_id_entrepot + ", date_fin=" + date_fin + ", date_deb=" + date_deb + '}';
    }
    
    
    
}
