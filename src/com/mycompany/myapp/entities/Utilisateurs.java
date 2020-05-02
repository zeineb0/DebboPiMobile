/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author ASUS
 */
public class Utilisateurs {
 
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private int tel;
    private int date_de_naissance;

    public Utilisateurs() {
    }
    
    

    public Utilisateurs(int id, String nom, String prenom, String email, String password, int tel, int date_de_naissance) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.date_de_naissance = date_de_naissance;
    }

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public int getDate_de_naissance() {
        return date_de_naissance;
    }

    public void setDate_de_naissance(int date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
    }
    
    
    
    
}
