package com.waiter.ordertaking;

public class Pojo_kitchen {

    private int image;
    private String table_name;
    private String taken_by;
    private String date;
    private String status;

    public Pojo_kitchen(int image, String table_name, String taken_by, String date, String status) {
        this.image = image;
        this.table_name = table_name;
        this.taken_by = taken_by;
        this.date = date;
        this.status = status;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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
}
