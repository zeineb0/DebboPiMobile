/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class Categorie {
    
    private int id;
    private String nom;
    private Entrepot entrepot;
    private User idUser;
    private String image;
    private List<Produit> listProduit =new ArrayList<>();
    
    public Categorie(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Entrepot getEntrepot() {
        return entrepot;
    }

    public void setEntrepot(Entrepot entrepot) {
        this.entrepot = entrepot;
    }
    
    
    public Categorie(String nom) {
        this.nom=nom;
    }
    public Categorie(){}

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public List<Produit> getListProduit() {
        return listProduit;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setListProduit(List<Produit> listProduit) {
        this.listProduit = listProduit;
    }

    @Override
    public String toString() {
        return  nom ;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
    
    
}
