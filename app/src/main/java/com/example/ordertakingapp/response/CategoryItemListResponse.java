package com.example.ordertakingapp.response;

import java.util.List;

public class CategoryItemListResponse {

    /**
     * Status : Success
     * Message : categroies list details
     * Data : [{"cat_id":"123456","item_id":"123456","item_count":0,"item_name":"Mangoes","price":129},{"cat_id":"123456","item_id":"123456","item_count":0,"item_name":"Mangoes","price":129},{"cat_id":"123456","item_id":"123456","item_count":0,"item_name":"Mangoes","price":129},{"cat_id":"123456","item_id":"123456","item_count":0,"item_name":"Mangoes","price":129},{"cat_id":"123456","item_id":"123456","item_count":0,"item_name":"Mangoes","price":129},{"cat_id":"123456","item_id":"123456","item_count":0,"item_name":"Mangoes","price":129},{"cat_id":"123456","item_id":"123456","item_count":0,"item_name":"Mangoes","price":129},{"cat_id":"123456","item_id":"123456","item_count":2,"item_name":"Mangoes","price":129},{"cat_id":"123456","item_id":"123456","item_count":0,"item_name":"Mangoes","price":129},{"cat_id":"123456","item_id":"123456","item_count":1,"item_name":"Mangoes","price":129},{"cat_id":"123456","item_id":"123456","item_count":0,"item_name":"Mangoes","price":129},{"cat_id":"123456","item_id":"123456","item_count":0,"item_name":"Mangoes","price":129}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * cat_id : 123456
     * item_id : 123456
     * item_count : 0
     * item_name : Mangoes
     * price : 129
     */

    private List<DataBean> Data;

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

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private String cat_id;
        private String item_id;
        private int item_count;
        private String item_name;
        private int price;

        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(String cat_id) {
            this.cat_id = cat_id;
        }

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }

        public int getItem_count() {
            return item_count;
        }

        public void setItem_count(int item_count) {
            this.item_count = item_count;
        }

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
