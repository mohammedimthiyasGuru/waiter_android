package com.example.ordertakingapp;

public class Pojo_PaymentDetails {

    private String transactionName;
    private String transactionContent;
    private String amount;
    private String date;
    private String status;
    private  int proImg;

    public Pojo_PaymentDetails(String transactionName, String transactionContent, String amount, String date, String status, int proImg) {
        this.transactionName = transactionName;
        this.transactionContent = transactionContent;
        this.amount = amount;
        this.date = date;
        this.status = status;
        this.proImg = proImg;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getTransactionContent() {
        return transactionContent;
    }

    public void setTransactionContent(String transactionContent) {
        this.transactionContent = transactionContent;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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

    public int getProImg() {
        return proImg;
    }

    public void setProImg(int proImg) {
        this.proImg = proImg;
    }
}
