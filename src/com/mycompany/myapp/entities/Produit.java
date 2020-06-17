/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class Produit {
private int id;
    private String libelle;
    private double prix;
    private int reference;
    private String marque;
    //un produit posséde une catégorie
    private String image;
    private Categorie categorie;
    private Entrepot entrepot;
    private int quantite;
    private int idUser;

    public Produit(String libelle) {
        this.libelle = libelle;
    }
    public Produit() {
    }

    public Produit(int id, String libelle, double prix, String marque) {
        this.id = id;
        this.libelle = libelle;
        this.prix = prix;
        this.marque = marque;
    }
    
    

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    
    public int getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public double getPrix() {
        return prix;
    }

    public int getReference() {
        return reference;
    }

    public String getMarque() {
        return marque;
    }

    public String getImage() {
        return image;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public Entrepot getEntrepot() {
        return entrepot;
    }

    @Override
    public String toString() {
        return libelle;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public void setEntrepot(Entrepot entrepot) {
        this.entrepot = entrepot;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }


    
    
    

}
