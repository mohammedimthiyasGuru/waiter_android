package com.example.ordertakingapp;

public class Pojo_WaiterDetail  {

    private String waiterNo;
    private String waiterName;
    private String orderCount;
    private String date;
    private String time;
    private String viewMore;
    private  int proImg;

    public Pojo_WaiterDetail(String waiterNo, String waiterName, String orderCount, String date, String time, String viewMore, int proImg) {
        this.waiterNo = waiterNo;
        this.waiterName = waiterName;
        this.orderCount = orderCount;
        this.date = date;
        this.time = time;
        this.viewMore = viewMore;
        this.proImg = proImg;
    }

    public String getWaiterNo() {
        return waiterNo;
    }

    public void setWaiterNo(String waiterNo) {
        this.waiterNo = waiterNo;
    }

    public String getWaiterName() {
        return waiterName;
    }

    public void setWaiterName(String waiterName) {
        this.waiterName = waiterName;
    }

    public String getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(String orderCount) {
        this.orderCount = orderCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getViewMore() {
        return viewMore;
    }

    public void setViewMore(String viewMore) {
        this.viewMore = viewMore;
    }

    public int getProImg() {
        return proImg;
    }

    public void setProImg(int proImg) {
        this.proImg = proImg;
    }
}
