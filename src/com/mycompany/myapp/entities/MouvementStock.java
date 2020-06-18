/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class MouvementStock {
    private int id;
    private String natureDuStock;
    private Date dateMouv;
    private int quantite;
      private Produit p;
    private Categorie c;
    //private Entrepot e;
    
    
    public MouvementStock() {
    }

    public int getId() {
        return id;
    }

    public String getNatureDuStock() {
        return natureDuStock;
    }

    public Produit getP() {
        return p;
    }

    public Categorie getC() {
        return c;
    }

    public Date getDateMouv() {
        return dateMouv;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNatureDuStock(String natureDuStock) {
        this.natureDuStock = natureDuStock;
    }

    public void setP(Produit p) {
        this.p = p;
    }

    public void setC(Categorie c) {
        this.c = c;
    }

    public void setDateMouv(Date dateMouv) {
        this.dateMouv = dateMouv;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    /*public Entrepot getE() {
        return e;
    }

    public void setE(Entrepot e) {
        this.e = e;
    }*/

    @Override
    public String toString() {
        return "MouvementStock{" + "id=" + id + ", natureDuStock=" + natureDuStock + ", p=" + p + ", c=" + c +
                //", e=" + e +
                ", dateMouv=" + dateMouv + ", quantite=" + quantite + '}';
    }

    
    
}
