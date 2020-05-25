/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author ASUS X550V
 */
public class Employe {
    int id_emp;
    String nom;
    String prenom;
    String cin;
    Date date_embauche;
    Date date_empmois;
    Double salaire;
    int points;
    String recommandations;
    String image_name;
    Date updated_at;
    int signalemp;
    int nbcong;
    String email;
    int FK_id_dep;

    public int getNbcong() {
        return nbcong;
    }

    public Employe(int id_emp) {
        this.id_emp = id_emp;
    }

    
    
    public void setNbcong(int nbcong) {
        this.nbcong = nbcong;
    }

    public int getId_emp() {
        return id_emp;
    }

    public void setId_emp(int id_emp) {
        this.id_emp = id_emp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public Date getDate_embauche() {
        return date_embauche;
    }

    public void setDate_embauche(Date date_embauche) {
        this.date_embauche = date_embauche;
    }

    public Date getDate_empmois() {
        return date_empmois;
    }

    public void setDate_empmois(Date date_empmois) {
        this.date_empmois = date_empmois;
    }

    public Double getSalaire() {
        return salaire;
    }

    public void setSalaire(Double salaire) {
        this.salaire = salaire;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getRecommandations() {
        return recommandations;
    }

    public void setRecommandations(String recommandations) {
        this.recommandations = recommandations;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public int getSignalemp() {
        return signalemp;
    }

    public void setSignalemp(int signalemp) {
        this.signalemp = signalemp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFK_id_dep() {
        return FK_id_dep;
    }

    public void setFK_id_dep(int FK_id_dep) {
        this.FK_id_dep = FK_id_dep;
    }

    @Override
    public String toString() {
        return "Employe{" + "id_emp=" + id_emp + ", nom=" + nom + ", prenom=" + prenom + ", cin=" + cin + ", date_embauche=" + date_embauche + ", date_empmois=" + date_empmois + ", salaire=" + salaire + ", points=" + points + ", recommandations=" + recommandations + ", image_name=" + image_name + ", updated_at=" + updated_at + ", signalemp=" + signalemp + ", email=" + email + ", FK_id_dep=" + FK_id_dep + '}';
    }
    
    
}
