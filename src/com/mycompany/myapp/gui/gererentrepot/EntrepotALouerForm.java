/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.gererentrepot;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.URLImage.ImageAdapter;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.mycompany.myapp.entities.Entrepot;
import com.mycompany.myapp.services.gererentrepot.EntrepotServices;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
/**
 *
 * @author asus
 */
public class EntrepotALouerForm extends Form {
   
    Form current;
    private SpanLabel lb;
    Form ms;
    private EncodedImage enc,encP,encUp,encDown,encN;
             private ComboBox cmb;
                private ComboBox cmbE;
                Button btn,supp;

 
    public EntrepotALouerForm() {
        current=this;
        setTitle("Liste des Entrepots a louer");
        this.setLayout(new TableLayout(CENTER,2));
        

        for (Entrepot ent : EntrepotServices.getInstance().getALouerEntrepots()){
            
            
                try {
                enc = EncodedImage.create("/3.jpg");
            } catch (IOException ex) {

            }
           Image i = (URLImage.createToStorage(enc.scaledEncoded(400, 400), "entrepot", "http://localhost/DebboPiWeb/web/public/images/entrepot", URLImage.RESIZE_SCALE_TO_FILL));
            Container c = new Container(new TableLayout(CENTER, 1)); 
          
            
            Label Adresse = new Label (ent.getAdresse_entrepot());
            double d = (double) ent.getPrix_location();
            Label prix = new Label (String.valueOf(d));
            Label Enteprise = new Label(ent.getEntreprise());

            
            c.add(i);
            c.add(Adresse);
            c.add(prix);
            Button btn= new Button("+ ajouter Location");
            Button b = new Button("More");
            b.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
            btn.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    
                }
            });
            Stroke borderStroke = new Stroke(3, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 3);  
          
           
            c.add(b);
            c.add(btn);
            
           
          this.add(c);
          c.getUnselectedStyle().setMargin(10, 10, 10, 10);
           c.getUnselectedStyle().setBorder(Border.createLineBorder(2, 0x000000)); 
            c.getUnselectedStyle().setPadding(10, 10, 10, 10);
             c.getUnselectedStyle().setBgColor(0xffffff);
          
          this.refreshTheme(); 
          
        }
          
        

    }
    
}
    
    
    

