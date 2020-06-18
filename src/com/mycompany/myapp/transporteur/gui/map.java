/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.transporteur.gui;

import com.codename1.maps.Coord;
import com.codename1.maps.MapComponent;
import com.codename1.maps.layers.PointLayer;
import com.codename1.maps.layers.PointsLayer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import java.io.IOException;


/**
 *
 * @author Dell
 */
public class map extends Form{
    private Form main;
    private Coord lastLocation;
    
    
    public map(Form previous) 
    {
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Button b = new Button("Where am I?");
        this.addComponent(b);
        main=previous;
        
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    showMeOnMap();
                } catch (IOException ex) {
                   
                }
            }
        });
    }
    
    
        public void showMeOnMap() throws IOException {
            
        Form maps = new Form("Map");
        maps.setLayout(new BorderLayout());
        maps.setScrollable(false);
        final MapComponent mc = new MapComponent();
        putMeOnMap(mc);
        mc.zoomToLayers();
        maps.addComponent(BorderLayout.CENTER, mc);
        maps.getToolbar().addCommandToLeftBar(new map.BackCommand());
        maps.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ZOOM_IN, e->mc.zoomIn());
        maps.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ZOOM_OUT, e->mc.zoomOut());
        maps.setBackCommand(new map.BackCommand());
        maps.show();
    }
        
    private void putMeOnMap(MapComponent map) throws IOException {
        
        double lat = 0;
        double lng = 0;
            Coord Direction = new Coord(36.8587,10.1884);
            Image i = Image.createImage("/pin.png");
            PointsLayer pl = new PointsLayer();
            pl.setPointIcon(i);
            PointLayer p = new PointLayer(Direction, "You Are Here", i);
            p.setDisplayName(true);
            pl.addPoint(p);
        map.zoomTo(Direction, 5);
            pl.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    PointLayer p = (PointLayer) evt.getSource();
                    System.out.println("pressed " + p);
                    Dialog.show("Details", "You Are Here" + "\n" + p.getLatitude() + "|" + p.getLongitude(), "Ok", null);
                }
            });
            map.addLayer(pl);
        }
    
    
    
    
    class BackCommand extends Command {
        public BackCommand() {
            super("");
            FontImage img = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, UIManager.getInstance().getComponentStyle("TitleCommand"));
            setIcon(img);
        }
        @Override
        public void actionPerformed(ActionEvent evt) {
            main.showBack();
        }
    }

    }
    
