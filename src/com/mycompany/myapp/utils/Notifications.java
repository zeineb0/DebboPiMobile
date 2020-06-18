/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.utils;

import com.mycompany.myapp.entities.Blog;
import com.mycompany.myapp.entities.Notification;
/**
 *
 * @author ghazi
 */
public class Notifications {
    private Blog blog;
    private Notification notification;

    public Notifications() {
    }

    
    
    public Notifications(Blog blog, Notification notification) {
        this.blog = blog;
        this.notification = notification;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }
    
    
    
    
    
}
