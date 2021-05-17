package com.example.ordertakingapp.response;

public class DashboardResponse {


    /**
     * Status : Success
     * Message : Dashboard Details
     * Data : {"user_count":23,"order_count":23,"notification_count":23,"Today_sale":2302,"Table_count":5}
     * Code : 200
     */

    private String Status;
    private String Message;
    /**
     * user_count : 23
     * order_count : 23
     * notification_count : 23
     * Today_sale : 2302
     * Table_count : 5
     * item_count
     */

    private DataBean Data;
    private int Code;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public static class DataBean {
        private int user_count;
        private int order_count;
        private int notification_count;
        private int Today_sale;
        private int Table_count;
        private int item_count;

        public int getItem_count() {
            return item_count;
        }

        public void setItem_count(int item_count) {
            this.item_count = item_count;
        }

        public int getUser_count() {
            return user_count;
        }

        public void setUser_count(int user_count) {
            this.user_count = user_count;
        }

        public int getOrder_count() {
            return order_count;
        }

        public void setOrder_count(int order_count) {
            this.order_count = order_count;
        }

        public int getNotification_count() {
            return notification_count;
        }

        public void setNotification_count(int notification_count) {
            this.notification_count = notification_count;
        }

        public int getToday_sale() {
            return Today_sale;
        }

        public void setToday_sale(int Today_sale) {
            this.Today_sale = Today_sale;
        }

        public int getTable_count() {
            return Table_count;
        }

        public void setTable_count(int Table_count) {
            this.Table_count = Table_count;
        }
    }
}
