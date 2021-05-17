package com.example.ordertakingapp;

public class Pojo_KitchenUserDetail {

    private String kitchenNo;
    private String kitchenUserName;
    private String orderCount;
    private String date;
    private String time;
    private String viewMore;
    private  int proImg;

    public Pojo_KitchenUserDetail(String kitchenNo, String kitchenUserName, String orderCount, String date, String time, String viewMore, int proImg) {
        this.kitchenNo = kitchenNo;
        this.kitchenUserName = kitchenUserName;
        this.orderCount = orderCount;
        this.date = date;
        this.time = time;
        this.viewMore = viewMore;
        this.proImg = proImg;
    }

    public String getKitchenNo() {
        return kitchenNo;
    }

    public void setKitchenNo(String kitchenNo) {
        this.kitchenNo = kitchenNo;
    }

    public String getKitchenUserName() {
        return kitchenUserName;
    }

    public void setKitchenUserName(String kitchenUserName) {
        this.kitchenUserName = kitchenUserName;
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
