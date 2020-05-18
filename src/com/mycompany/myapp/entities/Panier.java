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
    
    private int idUser;
    private double total;
    private ArrayList<ProduitCommande> produitCommande;

    public Panier(int idUser) {
        this.idUser = idUser;
        this.total = total=0;
        this.produitCommande = new ArrayList<>();
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ArrayList<ProduitCommande> getProduitCommande() {
        return produitCommande;
    }

    public void setProduitCommande(ArrayList<ProduitCommande> produitCommande) {
        this.produitCommande = produitCommande;
    }
    
     public void ajoutProduit(Produit p ,int qtt)
    {   int index=-1;
        for (ProduitCommande pc : this.getProduitCommande()){
            if(pc.getId_produit().equals(p))
                index=this.getProduitCommande().indexOf(pc);
        }
        if(index==-1){
     ProduitCommande pc= new ProduitCommande( p , p.getPrix() , qtt);
        produitCommande.add(pc);
        }
        else{
            this.getProduitCommande().get(index).setQuantite_produit
        (Integer.parseInt((this.getProduitCommande().get(index).getQuantite_produit()+"").
                substring(0,(this.getProduitCommande().get(index).getQuantite_produit()+"").indexOf(".")))+qtt);
        }
  
    }
      public void SupprimerProduit(ProduitCommande pc)
    {   
               this.getProduitCommande().remove(pc);   
               this.total=this.total-(pc.getPrix_produit()*pc.getQuantite_produit());
  
    }
    
    
}
