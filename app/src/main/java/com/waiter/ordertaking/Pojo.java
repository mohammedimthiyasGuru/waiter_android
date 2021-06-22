package com.waiter.ordertaking;

public class Pojo {

    private String listName;
    private  int imgMinus;
    private int imgPlus;
    private String listCount;

    public Pojo(String listName, int imgMinus, int imgPlus, String listCount) {
        this.listName = listName;
        this.imgMinus = imgMinus;
        this.imgPlus = imgPlus;
        this.listCount = listCount;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public int getImgMinus() {
        return imgMinus;
    }

    public void setImgMinus(int imgMinus) {
        this.imgMinus = imgMinus;
    }

    public int getImgPlus() {
        return imgPlus;
    }

    public void setImgPlus(int imgPlus) {
        this.imgPlus = imgPlus;
    }

    public String getListCount() {
        return listCount;
    }

    public void setListCount(String listCount) {
        this.listCount = listCount;
    }
}
