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
    String raison;
    Employe FK_id_emp;

    public conge(Date datesortie, Date datearrive, String type, String etat, String raison, Employe FK_id_emp) {
        this.datesortie = datesortie;
        this.datearrive = datearrive;
        this.type = type;
        this.etat = etat;
        this.raison = raison;
        this.FK_id_emp = FK_id_emp;
    }
    

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    public conge(int id, Date datesortie, Date datearrive, String type, String etat, String raison, Employe FK_id_emp) {
        this.id = id;
        this.datesortie = datesortie;
        this.datearrive = datearrive;
        this.type = type;
        this.etat = etat;
        this.raison = raison;
        this.FK_id_emp = FK_id_emp;
    }

    public conge(int id, Date datesortie, Date datearrive, String type, String etat, Employe FK_id_emp) {
        this.id = id;
        this.datesortie = datesortie;
        this.datearrive = datearrive;
        this.type = type;
        this.etat = etat;
        this.FK_id_emp = FK_id_emp;
    }
        public conge(String etat) {
        this.etat = etat;
    }

    public conge() {
    }

    public conge(String text, String text0, String text1, String text2, int parseInt) {
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

    public Employe getFK_id_emp() {
        return FK_id_emp;
    }

    public void setFK_id_emp(Employe FK_id_emp) {
        this.FK_id_emp = FK_id_emp;
    }

    @Override
    public String toString() {
        return "conge{" + "id=" + id + ", datesortie=" + datesortie + ", datearrive=" + datearrive + ", type=" + type + ", etat=" + etat + ", raison=" + raison + ", FK_id_emp=" + FK_id_emp.toString() + '}';
    }


    
    
}
