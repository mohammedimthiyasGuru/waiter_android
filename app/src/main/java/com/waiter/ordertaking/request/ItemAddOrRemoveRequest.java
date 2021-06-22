package com.waiter.ordertaking.request;

public class ItemAddOrRemoveRequest {


    /**
     * rest_id : 608f6fc9bb5e115d275c28b4
     * category_id : 608f83080ce4f06a62055b59
     * item_id : 608fd7942392940d525dcaaa
     * waiter_id : 3
     * table_no : 2
     * item_name : Coffee
     * item_price : 100
     */

    private String rest_id;
    private String category_id;
    private String item_id;
    private String waiter_id;
    private String table_no;
    private String item_name;
    private int item_price;

    public String getRest_id() {
        return rest_id;
    }

    public void setRest_id(String rest_id) {
        this.rest_id = rest_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
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

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getItem_price() {
        return item_price;
    }

    public void setItem_price(int item_price) {
        this.item_price = item_price;
    }
}
