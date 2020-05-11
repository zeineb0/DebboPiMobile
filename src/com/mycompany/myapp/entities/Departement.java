/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author ASUS X550V
 */
public class Departement {
    int id_dep;
    String nom;
    int etage;
    int nbsalles;
    double budgetannuel;
    int FK_id_ent;

    public int getId_dep() {
        return id_dep;
    }

    public void setId_dep(int id_dep) {
        this.id_dep = id_dep;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getEtage() {
        return etage;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }

    public int getNbsalles() {
        return nbsalles;
    }

    public void setNbsalles(int nbsalles) {
        this.nbsalles = nbsalles;
    }

    public double getBudgetannuel() {
        return budgetannuel;
    }

    public void setBudgetannuel(double budgetannuel) {
        this.budgetannuel = budgetannuel;
    }

    public int getFK_id_ent() {
        return FK_id_ent;
    }

    public void setFK_id_ent(int FK_id_ent) {
        this.FK_id_ent = FK_id_ent;
    }

    @Override
    public String toString() {
        return "Departement{" + "id_dep=" + id_dep + ", nom=" + nom + ", etage=" + etage + ", nbsalles=" + nbsalles + ", budgetannuel=" + budgetannuel + ", FK_id_ent=" + FK_id_ent + '}';
    }
    
    
}
