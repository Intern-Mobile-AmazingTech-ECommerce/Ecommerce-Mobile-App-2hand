package com.example.ecommercemobileapp2hand.Models;

import java.time.LocalDateTime;
import java.util.Date;

public class Notifications {
    private int notifications_id;
    private String notifications_content;
    private LocalDateTime created_at;
    private int user_id;
    private boolean viewed;
    public Notifications(){

    }
    public Notifications(int notifications_id, String notifications_content, LocalDateTime created_at, int user_id,boolean viewed) {
        this.notifications_id = notifications_id;
        this.notifications_content = notifications_content;
        this.created_at = created_at;
        this.user_id = user_id;
        this.viewed= viewed;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    public int getNotifications_id() {
        return notifications_id;
    }

    public void setNotifications_id(int notifications_id) {
        this.notifications_id = notifications_id;
    }

    public String getNotifications_content() {
        return notifications_content;
    }

    public void setNotifications_content(String notifications_content) {
        this.notifications_content = notifications_content;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
