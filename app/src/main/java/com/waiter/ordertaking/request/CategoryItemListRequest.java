package com.waiter.ordertaking.request;

public class CategoryItemListRequest {


    /**
     * rest_id : 12345
     * cat_id : 123455
     * table_no : 1
     * waiter_id : 123123
     */

    private String rest_id;
    private String cat_id;
    private String table_no;
    private String waiter_id;

    public String getRest_id() {
        return rest_id;
    }

    public void setRest_id(String rest_id) {
        this.rest_id = rest_id;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getTable_no() {
        return table_no;
    }

    public void setTable_no(String table_no) {
        this.table_no = table_no;
    }

    public String getWaiter_id() {
        return waiter_id;
    }

    public void setWaiter_id(String waiter_id) {
        this.waiter_id = waiter_id;
    }
}
