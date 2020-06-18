/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Dell
 */
public class Utilisateur {
    
    private int id_user,password;
    private String username,nom,prenom,email;
    
    
    public Utilisateur()
    {
        
    }

    public Utilisateur(int id_user, int password, String username, String nom, String prenom, String email) {
        this.id_user = id_user;
        this.password = password;
        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String toString() {
        return "Utilisateur{" + "id_user=" + id_user + ", password=" + password + ", username=" + username + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + '}';
    }
    
    
    
}
