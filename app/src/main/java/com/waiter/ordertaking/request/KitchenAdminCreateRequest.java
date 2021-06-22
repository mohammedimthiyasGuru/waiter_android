package com.waiter.ordertaking.request;

public class KitchenAdminCreateRequest {


    /**
     * rest_id : 6098ff1b074e747b0fcd04b5
     * chef_id : 60a3b19a9bbb7779da13ac7f
     * chef_name : Dinesh
     * type : Chef
     * title : Need Food
     * request_text : I need food to eat the, i am hungry
     * request_date : 23-10-2020 11:00 AM
     */

    private String rest_id;
    private String chef_id;
    private String chef_name;
    private String type;
    private String title;
    private String request_text;
    private String request_date;

    public String getRest_id() {
        return rest_id;
    }

    public void setRest_id(String rest_id) {
        this.rest_id = rest_id;
    }

    public String getChef_id() {
        return chef_id;
    }

    public void setChef_id(String chef_id) {
        this.chef_id = chef_id;
    }

    public String getChef_name() {
        return chef_name;
    }

    public void setChef_name(String chef_name) {
        this.chef_name = chef_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRequest_text() {
        return request_text;
    }

    public void setRequest_text(String request_text) {
        this.request_text = request_text;
    }

    public String getRequest_date() {
        return request_date;
    }

    public void setRequest_date(String request_date) {
        this.request_date = request_date;
    }
}
