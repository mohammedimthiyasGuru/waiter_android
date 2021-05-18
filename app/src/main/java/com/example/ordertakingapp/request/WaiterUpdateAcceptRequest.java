package com.example.ordertakingapp.request;

public class WaiterUpdateAcceptRequest {

    /**
     * order_id : 1621243271346
     * chef_id : 609900e577ada17c96829762
     */

    private String order_id;
    private String chef_id;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getChef_id() {
        return chef_id;
    }

    public void setChef_id(String chef_id) {
        this.chef_id = chef_id;
    }
}
