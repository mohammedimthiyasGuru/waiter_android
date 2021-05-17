package com.example.ordertakingapp;

public class Pojo_Notification {

    private String notification_name;
    private String notification_content;
    private String time;
    private  int profile_img;
    private int star;

    public Pojo_Notification(String notification_name, String notification_content, String time, int profile_img, int star) {
        this.notification_name = notification_name;
        this.notification_content = notification_content;
        this.time = time;
        this.profile_img = profile_img;
        this.star = star;
    }

    public String getNotification_name() {
        return notification_name;
    }

    public void setNotification_name(String notification_name) {
        this.notification_name = notification_name;
    }

    public String getNotification_content() {
        return notification_content;
    }

    public void setNotification_content(String notification_content) {
        this.notification_content = notification_content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(int profile_img) {
        this.profile_img = profile_img;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
