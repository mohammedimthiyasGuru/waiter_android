package com.waiter.ordertaking;

public class Pojo_OrderDetail {
    private String table_number;
    private String table_name;
    private String taken_by;
    private String date;
    private String status;
    private String cost;


    public Pojo_OrderDetail(String table_number, String table_name, String taken_by, String date, String status,String cost) {
        this.table_number = table_number;
        this.table_name = table_name;
        this.taken_by = taken_by;
        this.date = date;
        this.status = status;
        this.cost = cost;
    }

    public String getTable_number() {
        return table_number;
    }

    public void setTable_number(String table_number) {
        this.table_number = table_number;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getTaken_by() {
        return taken_by;
    }

    public void setTaken_by(String taken_by) {
        this.taken_by = taken_by;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

}
