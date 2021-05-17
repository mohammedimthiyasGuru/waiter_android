package com.example.ordertakingapp.response;

import java.util.List;

public class OverViewItemResponse {


    /**
     * Status : Success
     * Message : Over all item details
     * Data : [{"_id":"6097af41a55f3141d12d2ca3","rest_id":"608f6fc9bb5e115d275c28b4","category_id":"608f83080ce4f06a62055b59","item_id":"608fd949150f6610bb719d02","waiter_id":"608f701fbb5e115d275c28b8","table_no":"2","item_name":"Tea","item_price":50,"item_status":"Booked","item_count":12,"date_of_create":"Sun May 09 2021 09:45:37 GMT+0000 (Coordinated Universal Time)","updatedAt":"2021-05-09T10:57:41.765Z","createdAt":"2021-05-09T09:45:37.249Z","__v":0},{"_id":"6097af5ba55f3141d12d2ca4","rest_id":"608f6fc9bb5e115d275c28b4","category_id":"608f83080ce4f06a62055b59","item_id":"608fd7942392940d525dcaaa","waiter_id":"608f701fbb5e115d275c28b8","table_no":"2","item_name":"Coffee","item_price":100,"item_status":"Booked","item_count":1,"date_of_create":"Sun May 09 2021 09:46:03 GMT+0000 (Coordinated Universal Time)","updatedAt":"2021-05-09T09:46:03.507Z","createdAt":"2021-05-09T09:46:03.507Z","__v":0}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * _id : 6097af41a55f3141d12d2ca3
     * rest_id : 608f6fc9bb5e115d275c28b4
     * category_id : 608f83080ce4f06a62055b59
     * item_id : 608fd949150f6610bb719d02
     * waiter_id : 608f701fbb5e115d275c28b8
     * table_no : 2
     * item_name : Tea
     * item_price : 50
     * item_status : Booked
     * item_count : 12
     * date_of_create : Sun May 09 2021 09:45:37 GMT+0000 (Coordinated Universal Time)
     * updatedAt : 2021-05-09T10:57:41.765Z
     * createdAt : 2021-05-09T09:45:37.249Z
     * __v : 0
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
        private String _id;
        private String rest_id;
        private String category_id;
        private String item_id;
        private String waiter_id;
        private String table_no;
        private String item_name;
        private int item_price;
        private String item_status;
        private int item_count;
        private String date_of_create;
        private String updatedAt;
        private String createdAt;
        private int __v;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

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

        public String getItem_status() {
            return item_status;
        }

        public void setItem_status(String item_status) {
            this.item_status = item_status;
        }

        public int getItem_count() {
            return item_count;
        }

        public void setItem_count(int item_count) {
            this.item_count = item_count;
        }

        public String getDate_of_create() {
            return date_of_create;
        }

        public void setDate_of_create(String date_of_create) {
            this.date_of_create = date_of_create;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }
    }
}
