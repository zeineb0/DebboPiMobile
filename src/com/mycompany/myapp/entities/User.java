/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class User {
         private int id;
    private int cin;
    private Date dn;
    private int tel;
    private String username;
    private String nom;
    private String prenom;
    private String username_canonical;
    private String email_canonical;
    private String email;
    private String enabled;
    private String pasword;
    private int confirmation;
    private String password_requested_at;
    private String confirmation_token;
    private String last_login;
    private String role;
    private String Emailofconnecteduser;
    private static int IdOfConnectedUser;
    private static User userConncter;

    public User() {
        
    }

    public User(int cin, int tel, String username, String nom, String prenom, String email, String role) {
        this.cin = cin;
        this.tel = tel;
        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.role = role;
    }  
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public Date getDn() {
        return dn;
    }

    public void setDn(Date dn) {
        this.dn = dn;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }
    public int getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(int confirmation) {
        this.confirmation = confirmation;
    }

 

    public String getPassword_requested_at() {
        return password_requested_at;
    }

    public void setPassword_requested_at(String password_requested_at) {
        this.password_requested_at = password_requested_at;
    }

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public void setConfirmation_token(String confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmailofconnecteduser() {
        return Emailofconnecteduser;
    }

    public void setEmailofconnecteduser(String Emailofconnecteduser) {
        this.Emailofconnecteduser = Emailofconnecteduser;
    }

    public static int getIdOfConnectedUser() {
        return IdOfConnectedUser;
    }

    public static void setIdOfConnectedUser(int IdOfConnectedUser) {
        User.IdOfConnectedUser = IdOfConnectedUser;
    }

    public static User getUserConncter() {
        return userConncter;
    }

    public static void setUserConncter(User userConncter) {
        User.userConncter = userConncter;
    }
    
    
    
    
}
