package com.example.ordertakingapp.request;

public class OverViewItemRequest {


    /**
     * rest_id : 608f6fc9bb5e115d275c28b4
     * waiter_id : 608f701fbb5e115d275c28b8
     * table_no : 2
     */

    private String rest_id;
    private String waiter_id;
    private String table_no;

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

    public String getTable_no() {
        return table_no;
    }

    public void setTable_no(String table_no) {
        this.table_no = table_no;
    }
}
