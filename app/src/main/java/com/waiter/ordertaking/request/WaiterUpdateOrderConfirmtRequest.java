package com.waiter.ordertaking.request;

public class WaiterUpdateOrderConfirmtRequest {


    /**
     * order_id : 1621487305975
     * status : Completed
     */

    private String order_id;
    private String status;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
