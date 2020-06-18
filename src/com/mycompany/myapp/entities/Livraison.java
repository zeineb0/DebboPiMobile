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
public class Livraison {
    
    private int id_livraison,FK_id_user,FK_id_commande;
    private Date date_livraison;
    private String adresse_livraison,etat_livraison,acceptation;

    public Livraison(int id_livraison, int FK_id_user, int FK_id_commande, Date date_livraison, String adresse_livraison, String etat_livraison, String acceptation) {
        this.id_livraison = id_livraison;
        this.FK_id_user = FK_id_user;
        this.FK_id_commande = FK_id_commande;
        this.date_livraison = date_livraison;
        this.adresse_livraison = adresse_livraison;
        this.etat_livraison = etat_livraison;
        this.acceptation = acceptation;
    }

    public Livraison(int id_livraison, int FK_id_user, int FK_id_commande, Date date_livraison, String adresse_livraison, String etat_livraison) {
        this.id_livraison = id_livraison;
        this.FK_id_user = FK_id_user;
        this.FK_id_commande = FK_id_commande;
        this.date_livraison = date_livraison;
        this.adresse_livraison = adresse_livraison;
        this.etat_livraison = etat_livraison;
    }

    public int getId_livraison() {
        return id_livraison;
    }

    public void setId_livraison(int id_livraison) {
        this.id_livraison = id_livraison;
    }

    public int getFK_id_user() {
        return FK_id_user;
    }

    public void setFK_id_user(int FK_id_user) {
        this.FK_id_user = FK_id_user;
    }

    public int getFK_id_commande() {
        return FK_id_commande;
    }

    public void setFK_id_commande(int FK_id_commande) {
        this.FK_id_commande = FK_id_commande;
    }

    public Date getDate_livraison() {
        return date_livraison;
    }

    public void setDate_livraison(Date date_livraison) {
        this.date_livraison = date_livraison;
    }

    public String getAdresse_livraison() {
        return adresse_livraison;
    }

    public void setAdresse_livraison(String adresse_livraison) {
        this.adresse_livraison = adresse_livraison;
    }

    public String getEtat_livraison() {
        return etat_livraison;
    }

    public void setEtat_livraison(String etat_livraison) {
        this.etat_livraison = etat_livraison;
    }

    public String getAcceptation() {
        return acceptation;
    }

    public void setAcceptation(String acceptation) {
        this.acceptation = acceptation;
    }

    @Override
    public String toString() {
        return "Livraison{" + "id_livraison=" + id_livraison + ", FK_id_user=" + FK_id_user + ", FK_id_commande=" + FK_id_commande + ", date_livraison=" + date_livraison + ", adresse_livraison=" + adresse_livraison + ", etat_livraison=" + etat_livraison + ", acceptation=" + acceptation + '}';
    }
    
    
     
    
}
