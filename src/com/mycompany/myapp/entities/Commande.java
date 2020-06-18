/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ghazi
 */
public class Commande {
    private int id_commande;
    private int id_client;
    private double total;
    private Date date_commande;
    private Date date_exp;
    private ArrayList <ProduitCommande> list;

    public Commande() {
    }

    public Commande(int id_commande, int id_client, double total, Date date_commande, Date date_exp, ArrayList<ProduitCommande> list) {
        this.id_commande = id_commande;
        this.id_client = id_client;
        this.total = total;
        this.date_commande = date_commande;
        this.date_exp = date_exp;
        this.list = list;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(Date date_commande) {
        this.date_commande = date_commande;
    }

    public Date getDate_exp() {
        return date_exp;
    }

    public void setDate_exp(Date date_exp) {
        this.date_exp = date_exp;
    }

    @Override
    public String toString() {
        return "Commande{" + "id_commande=" + id_commande + ", id_client=" + id_client + ", total=" + total + ", date_commande=" + date_commande + ", date_exp=" + date_exp + '}';
    }
    
    
}
