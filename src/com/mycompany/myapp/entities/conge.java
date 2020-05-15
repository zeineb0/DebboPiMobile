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
public class conge {
    int id;
    Date datesortie;
    Date datearrive;
    String type;
    String etat;
    int FK_id_emp;

    public conge(int id, Date datesortie, Date datearrive, String type, String etat, int FK_id_emp) {
        this.id = id;
        this.datesortie = datesortie;
        this.datearrive = datearrive;
        this.type = type;
        this.etat = etat;
        this.FK_id_emp = FK_id_emp;
    }

    public conge() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatesortie() {
        return datesortie;
    }

    public void setDatesortie(Date datesortie) {
        this.datesortie = datesortie;
    }

    public Date getDatearrive() {
        return datearrive;
    }

    public void setDatearrive(Date datearrive) {
        this.datearrive = datearrive;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getFK_id_emp() {
        return FK_id_emp;
    }

    public void setFK_id_emp(int FK_id_emp) {
        this.FK_id_emp = FK_id_emp;
    }

    @Override
    public String toString() {
        return "conge{" + "id=" + id + ", datesortie=" + datesortie + ", datearrive=" + datearrive + ", type=" + type + ", etat=" + etat + ", FK_id_emp=" + FK_id_emp + '}';
    }
    
    
    
}
