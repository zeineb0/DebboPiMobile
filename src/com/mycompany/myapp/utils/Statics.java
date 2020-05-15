/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.utils;

import com.codename1.l10n.SimpleDateFormat;
import com.mycompany.myapp.entities.Panier;
import java.util.ArrayList;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class Statics {

    public static final String GHAZI_URL="http://localhost/DebboPiWeb/web/app_dev.php/Mobile";
    public static final String pattern = "HH:mm MM-dd-yyyy";
    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    public static final String BASE_URL="http://localhost/DebboWeb/web/app_dev.php/Stock";
    
    public static final ArrayList<Panier> listPanier = new ArrayList<>();
    
}
