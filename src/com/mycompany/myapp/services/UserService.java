/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.Data;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class UserService {
    
        public ArrayList<User> users;
    
    public static UserService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    String reponseLog;
    boolean resultUN;
    boolean resultEmail;
    boolean resultPW;
    boolean resultTel;
    
    private UserService() {
         req = new ConnectionRequest();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
    
    public ArrayList<User> parseUsers(String jsonText){
        try {
            users=new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des users et récupération de leurs données
                User u = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                u.setId((int)id);
                u.setNom(obj.get("nom").toString());
                u.setPrenom(obj.get("prenom").toString());
                u.setUsername(obj.get("username").toString());
                u.setEmail(obj.get("email").toString());
                int cinn = Integer.parseInt(obj.get("cin").toString());
                u.setCin(cinn);
                int tell = Integer.parseInt(obj.get("tel").toString());
                u.setTel(tell);
                
                
                //Ajouter la tâche extraite de la réponse Json à la liste
                users.add(u);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */

        return users;
    }
    
     public ArrayList<User> getAllUsers(){
        String url = Statics.BASE_URL+"/forum/users/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUsers(new String(req.getResponseData()));
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
         
         
        return users;
    }
     
     public int getLoggedId(String username){
         int x = 0 ;
         ArrayList<User> tester = getAllUsers();
                  for (int k=0;k<tester.size();k++){
         if (tester.get(k).getUsername().equals(username))
         {
             x = tester.get(k).getId();
           
         }
 
         }

         return x;
         
     }
     
     public User getLoggedInfos(String username){
         User u = new User();
                  ArrayList<User> tester = getAllUsers();
                  for (int k=0;k<users.size();k++){
         if (users.get(k).getUsername().equals(username))
         {
             u = users.get(k);
         }
             
         }
          return u;
          
     }
     

     public String logiiin(String username,String password){
         
         String a ="wedev";
         String b ="123";

                             String jsn = "{\n" +
"    \"username\": \""+a+"\",\n" +
"    \"password\": \""+b+"\"\n" +
"}";       

                         try {
         String url="http://localhost/DebboPiWeb-master/web/app_dev.php/forum/logg";
         req.setUrl(url);
         req.setPost(true);
         req.setContentType("application/json");
         req.setRequestBody(jsn);
          
             
         
          req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reponseLog =  req.getResponseErrorMessage();
                req.removeResponseListener(this); //Supprimer cet actionListener
              
                
            }
        });
       }
          
          catch (Exception e) {
              System.out.println("hemo");
              
          }
       //  System.out.println(reponse);
       NetworkManager.getInstance().addToQueueAndWait(req);
         return reponseLog;
     }
     
         public boolean addUser(User u) {
        String url = "http://localhost/DebboPiWeb-master/web/app_dev.php/forum/new?username="+u.getUsername()+"&nom="+u.getNom()+"&prenom="+u.getPrenom()+"&email="+u.getEmail()+"&password="+u.getPassword()+"&cin="+u.getCin()+"&tel="+u.getTel();
                
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
         
         public boolean usernameExists(String username){
             boolean result=false;
                               ArrayList<User> tester = getAllUsers();
                  for (int k=0;k<tester.size();k++){
         if (tester.get(k).getUsername().equals(username))
         {
             result=true;
         }
             
         }
             
             return result;
         }
         
                  public boolean emailExists(String email){
             boolean result=false;
                               ArrayList<User> tester = getAllUsers();
                  for (int k=0;k<tester.size();k++){
         if (tester.get(k).getEmail().equals(email))
         {
             result=true;
         }
             
         }
             
             return result;
         }
                  
             public boolean changeUsername(String username){
                 
                 
        String url = "http://localhost/DebboPiWeb-master/web/app_dev.php/forum/chusername?id="+Statics.getIdSession()+"&username="+username;
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultUN = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    return resultUN;
}
             
              public boolean changeEmail(String email){

                          String url = "http://localhost/DebboPiWeb-master/web/app_dev.php/forum/chemail?id="+Statics.getIdSession()+"&email="+email;
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultEmail = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    return resultEmail;
                  
                  
}
         public boolean changePassword(String password){
                 
                 
        String url = "http://localhost/DebboPiWeb-master/web/app_dev.php/forum/chpassword?id="+Statics.getIdSession()+"&password="+password;
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultPW = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    return resultPW;
}
         
        public boolean changeTel(int tel){
                 
                 
        String url = "http://localhost/DebboPiWeb-master/web/app_dev.php/forum/chtel?id="+Statics.getIdSession()+"&tel="+tel;
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultTel = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    return resultTel;
}
              
             
             
             
             
             
}
