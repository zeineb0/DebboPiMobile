/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author ghazi
 */
public class ProduitCommande {
    private Commande id_commande;
    private Produit id_produit;
    private double prix_produit;
    private double quantite_produit;

    public ProduitCommande() {
    }

    public ProduitCommande(Commande id_commande, Produit id_produit, double prix_produit, double quantite_produit) {
        this.id_commande = id_commande;
        this.id_produit = id_produit;
        this.prix_produit = prix_produit;
        this.quantite_produit = quantite_produit;
    }

    public Commande getId_commande() {
        return id_commande;
    }

    public void setId_commande(Commande id_commande) {
        this.id_commande = id_commande;
    }

    public Produit getId_produit() {
        return id_produit;
    }

    public void setId_produit(Produit id_produit) {
        this.id_produit = id_produit;
    }

    public double getPrix_produit() {
        return prix_produit;
    }

    public void setPrix_produit(double prix_produit) {
        this.prix_produit = prix_produit;
    }

    public double getQuantite_produit() {
        return quantite_produit;
    }

    public void setQuantite_produit(double quantite_produit) {
        this.quantite_produit = quantite_produit;
    }

    @Override
    public String toString() {
        return "ProduitCommande{" + "id_commande=" + id_commande + ", id_produit=" + id_produit + ", prix_produit=" + prix_produit + ", quantite_produit=" + quantite_produit + '}';
    }
    
}
