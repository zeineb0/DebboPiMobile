/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.gererentrepot;
import com.codename1.components.SpanLabel;
import com.codename1.location.Geofence;
import com.codename1.location.LocationManager;
import com.codename1.messaging.Message;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.mycompany.myapp.entities.Entrepot;
import com.mycompany.myapp.entities.Location;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.UserService;
import com.mycompany.myapp.services.gererentrepot.EntrepotServices;
import com.mycompany.myapp.services.gererentrepot.LocationServices;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import org.joda.time.DateTime;
import org.joda.time.Days;
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

 
    public EntrepotALouerForm(Form previous, User u) {
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
          
            int idEntrepot = ent.getId_entrepot();
           
            Label Adresse = new Label (ent.getAdresse_entrepot());
            double d = (double) ent.getPrix_location();
            Label prix = new Label (String.valueOf(d));
            Label Enteprise = new Label(ent.getEntreprise());

            
            c.add(i);
            c.add(Adresse);
            c.add(prix);
            Button btn= new Button("+ ajouter Location");
            Style butStyle = btn.getAllStyles();
            Stroke borderStroke1 = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);

        butStyle.setBorder(RoundRectBorder.create().
                strokeColor(0x00000).
                strokeOpacity(120).
                stroke(borderStroke1));
        
  
        butStyle.setFgColor(0x474747);

      
        butStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle.setMargin(Component.BOTTOM, 3);
        butStyle.setMargin(Component.TOP, 2);
        butStyle.setMargin(Component.LEFT,0 );
        butStyle.setMargin(Component.RIGHT, 0);

            btn.addActionListener((evt) -> {
            String dialog = "Adresse : " + ent.getAdresse_entrepot() + " \n Prix : " + ent.getPrix_location() + " \n Entreprise : " +ent.getEntreprise() +"\n quantite" +ent.getQuantite_max()+"\n Proprietaire: " +ent.getFk_id_fournisseur().getPrenom()+" "+ent.getFk_id_fournisseur().getNom() ;
             Command cmd1= new Command("ajouter"){
                @Override
                public void actionPerformed(ActionEvent evt) {
                                    
                    Container calculer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Picker date_deb = new Picker();
                    Picker date_fin = new Picker();
                    TextField prix = new TextField();
                    Button calPrix = new Button("calculer prix Location");
                    calculer.add(date_deb);
                    calculer.add(date_fin);
                    calculer.add(prix);
                    calculer.add(calPrix);
                    calPrix.addActionListener((evt1)->{
            DateTime dateTimeDeb = new DateTime(date_deb.getDate().getTime());
            DateTime dateTimeFin = new DateTime(date_fin.getDate().getTime());
            int days = Days.daysBetween(dateTimeDeb, dateTimeFin).getDays();
           if ((Days.daysBetween(dateTimeDeb, dateTimeFin).getDays()==0)||(Days.daysBetween(dateTimeDeb, dateTimeFin).getDays()<0))
          {Dialog.show("Alert", "Merci de verifier les dates de location", new Command("OK"));}
           
           else{
           float soumLem7al = (float) (d/ 30);
           float prixLoc = soumLem7al*days;
           String x = String.valueOf(prixLoc);
           prix.setText(x);
                    System.out.println("soum final :"+x);
                    System.out.println("nbr jours : "+days);
                  
                    System.out.println("bonjour wood");}
                    });
                    Command calculPrix = new Command("Valider"){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    System.out.println("date c bn");
   
      
                        
                        if ((prix.getText().length()==0))
                        {             Dialog.show("Alert", "Merci de calculer le prix location", new Command("OK"));
                        }
                    else
                {
                    try {
                        
                       Location l = new Location(date_deb.getDate(), date_fin.getDate(),Double.valueOf(prix.getText()));
                        if( LocationServices.getInstance().addLocation(l,idEntrepot))
                        {  Dialog.show("Success","Demande de Location Envoyé",new Command("OK"));
                         LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("La demande de location a ete envoyé");
        n.setAlertTitle("Location!");
        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound


        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
        );
        
                        Message m = new Message("Bonjour je vous ai envoyé ce mail pour vous annoncer que je vous ai "
                                + "envoye une demande de location priere de l'accepter car c'est un plaisir de travailler avec vous");
                      
                       Message.sendMessage(new String[] {ent.getFk_id_fournisseur().getEmail()}, "Demande de location", m);
                        }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                }
              
             };
                //   Dialog.show("hi", calculer, calculPrix , TOP, encN);
                             Command cmd2= new Command("Annuler");

                   Dialog.show("Valider Location", calculer, calculPrix,cmd2);
                   
                    
                            
                           
       
                    
                }
              
             };
             Command cmd2= new Command("Annuler");
            
            Dialog.show("Location", dialog, cmd1 , cmd2);
            
            });
            
            
            Stroke borderStroke = new Stroke(3, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 3);  
          
           
            c.add(btn);
            
           
          this.add(c);
          c.getUnselectedStyle().setMargin(10, 10, 10, 10);
           c.getUnselectedStyle().setBorder(Border.createLineBorder(2, 0x000000)); 
            c.getUnselectedStyle().setPadding(10, 10, 10, 10);
             c.getUnselectedStyle().setBgColor(0xffffff);
          
          this.refreshTheme(); 
          
        
          
        }

            if(u.getRole().equals("Client")){
    getToolbar().addMaterialCommandToOverflowMenu("Mes Locations",FontImage.MATERIAL_SHOP ,e-> new LocationListe(previous, u).show());

            }
            else{
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->new ListEntrepot(previous, u).showBack());
    getToolbar().addMaterialCommandToOverflowMenu("Ajouter Entrepot",FontImage.MATERIAL_ADD,e-> new AddEntrepot(current).show());
    getToolbar().addMaterialCommandToOverflowMenu("Entrepot Loué",FontImage.MATERIAL_HIGHLIGHT,e-> new EntrepotLoueForm(previous, u).show());
    getToolbar().addMaterialCommandToOverflowMenu("Liste des entrepots",FontImage.MATERIAL_SHOP ,e-> new ListEntrepot(previous, u).show());
    getToolbar().addMaterialCommandToOverflowMenu("Mes Locations",FontImage.MATERIAL_SHOP ,e-> new LocationListe(previous, u).show());
            }
            this.show();

    }
    
}
    
    
    

