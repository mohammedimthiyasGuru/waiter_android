package com.waiter.ordertaking.request;

public class BlockUnBlockWaiterRequest {


    /**
     * _id : 6099001377ada17c9682975e
     * waiter_status : true
     */

    private String _id;
    private String waiter_status;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getWaiter_status() {
        return waiter_status;
    }

    public void setWaiter_status(String waiter_status) {
        this.waiter_status = waiter_status;
    }
}
