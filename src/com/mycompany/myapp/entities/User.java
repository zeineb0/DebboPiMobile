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
public class User {
    private int id;
    private String nom;
    private String prenom;
    private String username;
    private String email;
    private String password;
    private int cin;
    private String roles;
    private int tel;
    private String role;



    public User() {
    }

    public User(int id, String nom, String prenom, String username, String email, String password, int cin, String roles, int tel) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
        this.password = password;
        this.cin = cin;
        this.roles = roles;
        this.tel = tel;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    

    public void setUsername(String username) {
        this.username = username;
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
