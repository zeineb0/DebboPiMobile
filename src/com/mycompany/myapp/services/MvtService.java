/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Entrepot;
import com.mycompany.myapp.entities.MouvementStock;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class MvtService {
  public ArrayList<MouvementStock> mvts;
    
    public static MvtService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private MvtService() {
         req = new ConnectionRequest();
    }

    public static MvtService getInstance() {
        if (instance == null) {
            instance = new MvtService();
        }
        return instance;
    }
    public boolean addMvt(MouvementStock m) {
        String url = Statics.BASEZ_URL + "/newP?natureMouvement="+m.getNatureDuStock()+"&dateMouv="+m.getDateMouv()+
        "&fkProduit="+m.getP().getId()+"&fkEntrepot="+m.getE().getId_entrepot();
        
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     public ArrayList<MouvementStock> parseMvt(String jsonText){
        try {
            mvts=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           // User.setIdOfConnectedUser(0);
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                MouvementStock m = new MouvementStock();
                float id = Float.parseFloat(obj.get("idMouv").toString());
                m.setId((int)id);
                m.setNatureDuStock(obj.get("natureMouvement").toString());
                
                Map<String, Object> datem = (Map<String, Object>) obj.get("dateMouv");
                 float datemm = Float.parseFloat(datem.get("timestamp").toString());
                Date l = new Date((long) datemm * 1000);
                m.setDateMouv(l);
                //c.setEntrepot((Entrepot)obj.get("fkEntrepot"));
                
                Map<String, Object> ProduitJson = (Map<String, Object>) obj.get("fkProduit");
                Produit p = new Produit();
                float idp = Float.parseFloat(ProduitJson.get("idProduit").toString());
                p.setId((int)idp);
                p.setLibelle(ProduitJson.get("libelle").toString());
                m.setP(p);
                
                Map<String, Object> EntJson = (Map<String, Object>) obj.get("fkEntrepot");
                Entrepot e = new Entrepot();
                float ide = Float.parseFloat(EntJson.get("idEntrepot").toString());
                e.setId_entrepot((int)ide);
                e.setAdresse_entrepot(EntJson.get("adresse").toString());
                m.setE(e);

           
               mvts.add(m);
                //System.out.println("********");
            }
        } catch (IOException ex) {
            
        }
        return mvts;
        
             
    }

       public ArrayList<MouvementStock> getAllMvt(){
        String url = Statics.BASEZ_URL+"/allM";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                mvts = parseMvt(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return mvts;
    }
       /*  public void modifierProduit(Produit p) {

        ConnectionRequest con = new ConnectionRequest();

        String Url = Statics.BASE_URL + "modifierfiche?" + "id=" + p.getId() + "&observation=" + p.getObservation() + "&medicament=" + ta.getMedicament() + "&prochainRDV=" + datdebu;
        con.setUrl(Url);
       http://localhost/DebboWeb/web/app_dev.php/Stock/modifP?idProduit=62&libelle=plateau&marque=oui&reference=12345&prix=40&quantite=40&fkCategorie=2&fkEntrepot=2
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            System.out.println("222222222" + str);
            System.out.println("333333333" + Url);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void supprimerficheDeDressage(FicheDeSoin ta) {

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(ToolsUtilities.UrlAhmedMakni + "dellficheDesoin/" + ta.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    message = new String(con.getResponseData(), "utf-8");
                    System.out.println("message" + message);
                } catch (UnsupportedEncodingException ex) {
                    System.out.println(ex.toString());
                }
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(con);

    }*/   
}
