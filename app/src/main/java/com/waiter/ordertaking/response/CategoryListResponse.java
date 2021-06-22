package com.waiter.ordertaking.response;

import java.util.List;

public class CategoryListResponse {

    /**
     * Status : Success
     * Message : categroies list details
     * Data : [{"cat_id":"123456","cat_name":"Mango"},{"cat_id":"123457","cat_name":"Mango1"},{"cat_id":"123458","cat_name":"Mango2"},{"cat_id":"123459","cat_name":"Mango3"},{"cat_id":"123450","cat_name":"Mango4"},{"cat_id":"123451","cat_name":"Mango6"},{"cat_id":"123452","cat_name":"Mango7"}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * cat_id : 123456
     * cat_name : Mango
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
        private String cat_name;

        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(String cat_id) {
            this.cat_id = cat_id;
        }

        public String getCat_name() {
            return cat_name;
        }

        public void setCat_name(String cat_name) {
            this.cat_name = cat_name;
        }
    }
}
