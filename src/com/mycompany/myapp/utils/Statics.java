/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.utils;

import com.codename1.l10n.SimpleDateFormat;
import com.mycompany.myapp.entities.Panier;


/**
 *
 * @author Zeineb_yahiaoui
 */
public class Statics {
    public static final String BASE_URL="http://localhost/DebboWeb/web/app_dev.php";
    public static String rstLog ="";
    public static int idSession;
    public static String loggedPlainPW;

    public static String getLoggedPlainPW() {
        return loggedPlainPW;
    }

    public static void setLoggedPlainPW(String loggedPlainPW) {
        Statics.loggedPlainPW = loggedPlainPW;
    }
    
    

    public static int getIdSession() {
        return idSession;
    }

    public static void setIdSession(int idSession) {
        Statics.idSession = idSession;
    }
    
    

    public static String getRstLog() {
        return rstLog;
    }

    public static void setRstLog(String rstLog) {
        Statics.rstLog = rstLog;
    }
    

    public static final String GHAZI_URL="http://localhost/DebboWeb/web/app_dev.php/Mobile";
    public static final String pattern = "MM-dd-yyyy";
    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    public static final String BASEZ_URL="http://localhost/DebboWeb/web/app_dev.php/Stock";
       
    public static  Panier panier= new Panier(8);
    public static double convert(double p){
       double d = (double) Math.round(p * 100) / 100;
       return d;
    };
    public static final String pattern = "dd-MM-yyyy";
    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    
}
