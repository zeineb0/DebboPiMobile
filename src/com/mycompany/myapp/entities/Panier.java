/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.ArrayList;

/**
 *
 * @author ghazi
 */
public class Panier {
    
    int idUser;
    double total;
    ArrayList<ProduitCommande> produitCommande;
    int nbrProduit;

    public Panier(int idUser) {
        this.idUser = idUser;
        this.total = total=0;
        this.produitCommande = new ArrayList<>();
        this.nbrProduit = 0;
    }
    
    
    
    
}
