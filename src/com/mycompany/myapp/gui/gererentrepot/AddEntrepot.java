/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.gererentrepot;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Entrepot;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.gererentrepot.EntrepotServices;
import com.mycompany.myapp.utils.Statics;

/**
 *
 * @author asus
 */
public class AddEntrepot extends Form{    
    public AddEntrepot(Form previous) {
        setTitle("Ajouter un nouveau entrepot");
        setLayout(BoxLayout.y());
        
        //*********************** num txt field *********************
        
        TextField num = new TextField("","Numero fiscale");

        Style userStyle = num.getAllStyles();
        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        userStyle.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        userStyle.setBgColor(0xffffff);
        userStyle.setBgTransparency(255);
        userStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
        userStyle.setMargin(Component.BOTTOM, 3);
        userStyle.setMargin(Component.TOP, 2);
        
        //************************* quant txt field ***************************
        TextField quant= new TextField("", "Quantite max");
        
        Style userStyle1 = quant.getAllStyles();
        userStyle1.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        userStyle1.setBgColor(0xffffff);
        userStyle1.setBgTransparency(255);
        userStyle1.setMarginUnit(Style.UNIT_TYPE_DIPS);
        userStyle1.setMargin(Component.BOTTOM, 3);
        userStyle1.setMargin(Component.TOP, 2);
        //************************ entrp txt field ****************************
        TextField entrep= new TextField("", "Entreprise");
        Style userStyle2 = entrep.getAllStyles();
        userStyle2.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        userStyle2.setBgColor(0xffffff);
        userStyle2.setBgTransparency(255);
        userStyle2.setMarginUnit(Style.UNIT_TYPE_DIPS);
        userStyle2.setMargin(Component.BOTTOM, 3);
        userStyle2.setMargin(Component.TOP, 2);
        //************************ adresse txt field ************************
        TextField adresse= new TextField("", "adresse");
        
        Style userStyle3 = adresse.getAllStyles();
        userStyle3.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        userStyle3.setBgColor(0xffffff);
        userStyle3.setBgTransparency(255);
        userStyle3.setMarginUnit(Style.UNIT_TYPE_DIPS);
        userStyle3.setMargin(Component.BOTTOM, 3);
        userStyle3.setMargin(Component.TOP, 2);
        //*********************** combo box *********************************
        ComboBox<String> etat=new ComboBox<>();
        etat.addItem(">Loué");
        etat.addItem(">A Louer");
        etat.addItem(">Libre");
        Style userStyle4 = etat.getAllStyles();
        userStyle4.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        userStyle4.setBgColor(0xffffff);
        userStyle4.setBgTransparency(255);
        userStyle4.setMarginUnit(Style.UNIT_TYPE_DIPS);
        userStyle4.setMargin(Component.BOTTOM, 3);
        userStyle4.setMargin(Component.TOP, 2);
        
        //*********************** txt field prix ****************************
        TextField prix= new TextField("", "prix de location");
        
        Style userStyle5 = prix.getAllStyles();
        userStyle5.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        userStyle5.setBgColor(0xffffff);
        userStyle5.setBgTransparency(255);
        userStyle5.setMarginUnit(Style.UNIT_TYPE_DIPS);
        userStyle5.setMargin(Component.BOTTOM, 3);
        userStyle5.setMargin(Component.TOP, 2);
        //******************** btn valider *******************************

        Button btnValider = new Button("Ajouter Entrepot");
        Style butStyle = btnValider.getAllStyles();
        butStyle.setBorder(RoundRectBorder.create().
                strokeColor(0x00000).
                strokeOpacity(120).
                stroke(borderStroke));
        
  
        butStyle.setFgColor(0x474747);

      
        butStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle.setMargin(Component.BOTTOM, 3);
        butStyle.setMargin(Component.TOP, 2);
        butStyle.setMargin(Component.LEFT,5);
        butStyle.setMargin(Component.RIGHT, 5);
        //************************* ACTIONS *********************************
        btnValider.addActionListener(new ActionListener() {
          

            @Override
            public void actionPerformed(ActionEvent evt) {
                if(EntrepotServices.getInstance().numFiscaleExist(Integer.valueOf(num.getText()))){
                        Dialog.show("Alert", "Ce numéro fiscale existe deja", new Command("OK"));
                    }
                else if ((num.getText().length()==0)||(quant.getText().length()<3)||(entrep.getText().length()==0)||(adresse.getText().length()==0)||(etat.getSelectedItem().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
  
                    
                 else   {
                        
                    
                    
                    
                    try {
                       // User user=new User(5);
                        
                       Entrepot e = new Entrepot(adresse.getText(),Integer.parseInt(num.getText()),Integer.parseInt(quant.getText()),etat.getSelectedItem().toString(), entrep.getText(),Float.parseFloat(prix.getText()));
                        if( EntrepotServices.getInstance().addEntrepot(e))
                            Dialog.show("Success","Entrepot Ajouté",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                   } 
                
                            }
        });
        
        addAll(num,quant,entrep,adresse,etat,prix,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
            }         
 
    
}


