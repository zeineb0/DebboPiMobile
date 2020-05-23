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
    
    private int salaire,FK_id_entrepot;
    private Date date_fin,date_deb;
    private String nom_transporteur,prenom_transporteur,entreprise;
    private String img;

    public Contrat() {
    }

    
    
    
    public Contrat(int salaire, int FK_id_user, int FK_id_entrepot, Date date_fin, Date date_deb) {
        this.salaire = salaire;
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

    public String getNom_transporteur() {
        return nom_transporteur;
    }

    public void setNom_transporteur(String nom_transporteur) {
        this.nom_transporteur = nom_transporteur;
    }

    public String getPrenom_transporteur() {
        return prenom_transporteur;
    }

    public void setPrenom_transporteur(String prenom_transporteur) {
        this.prenom_transporteur = prenom_transporteur;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    
    
    
    @Override
    public String toString() {
        return "Contrat{" + "salaire=" + salaire + ", FK_id_entrepot=" + FK_id_entrepot + ", date_fin=" + date_fin + ", date_deb=" + date_deb + ", nom_transporteur=" + nom_transporteur + ", prenom_transporteur=" + prenom_transporteur + ", entreprise=" + entreprise + '}';
    }
    
    

   
    
    
}
