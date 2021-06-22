package com.waiter.ordertaking.request;

public class BlockUnBlockChefRequest {

    /**
     * _id : 609900c677ada17c96829761
     * chef_status : false
     */

    private String _id;
    private String chef_status;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getChef_status() {
        return chef_status;
    }

    public void setChef_status(String chef_status) {
        this.chef_status = chef_status;
    }
}
