package com.waiter.ordertaking.request;

public class WaiterAdminCreateRequest {

    /**
     * rest_id : 6098ff1b074e747b0fcd04b5
     * waiter_id : 60a3b19a9bbb7779da13ac7f
     * waiter_name : Dinesh
     * type : Waiter
     * title : Need Food
     * request_text : I need food to eat the, i am hungry
     * request_date : 23-10-2020 11:00 AM
     */

    private String rest_id;
    private String waiter_id;
    private String waiter_name;
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

    public String getWaiter_id() {
        return waiter_id;
    }

    public void setWaiter_id(String waiter_id) {
        this.waiter_id = waiter_id;
    }

    public String getWaiter_name() {
        return waiter_name;
    }

    public void setWaiter_name(String waiter_name) {
        this.waiter_name = waiter_name;
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
