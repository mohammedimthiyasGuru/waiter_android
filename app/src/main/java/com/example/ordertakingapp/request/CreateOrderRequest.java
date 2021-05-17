package com.example.ordertakingapp.request;

import java.util.List;

public class CreateOrderRequest {


    /**
     * rest_id : 608f6fc9bb5e115d275c28b4
     * table_no : 1
     * taken_id : 608f701fbb5e115d275c28b8
     * order_date_book : 23-10-2021 11:00 AM
     * item_detail : [{"_id":"60920638ccb9375cfa41b8a2","rest_id":"608f6fc9bb5e115d275c28b4","category_id":"608f83080ce4f06a62055b59","item_id":"608fd7942392940d525dcaaa","waiter_id":"608f701fbb5e115d275c28b8","table_no":"1","item_name":"Coffee","item_price":100,"item_status":"New Booking","item_count":1,"date_of_create":"Wed May 05 2021 02:43:04 GMT+0000 (Coordinated Universal Time)","updatedAt":"2021-05-05T02:46:19.613Z","createdAt":"2021-05-05T02:43:04.362Z","__v":0},{"_id":"60920638ccb9375cfa41b8a2","rest_id":"608f6fc9bb5e115d275c28b4","category_id":"608f83080ce4f06a62055b59","item_id":"608fd7942392940d525dcaaa","waiter_id":"608f701fbb5e115d275c28b8","table_no":"1","item_name":"Coffee","item_price":100,"item_status":"New Booking","item_count":2,"date_of_create":"Wed May 05 2021 02:43:04 GMT+0000 (Coordinated Universal Time)","updatedAt":"2021-05-05T02:46:19.613Z","createdAt":"2021-05-05T02:43:04.362Z","__v":0},{"_id":"60920638ccb9375cfa41b8a2","rest_id":"608f6fc9bb5e115d275c28b4","category_id":"608f83080ce4f06a62055b59","item_id":"608fd7942392940d525dcaaa","waiter_id":"608f701fbb5e115d275c28b8","table_no":"1","item_name":"Coffee","item_price":300,"item_status":"New Booking","item_count":2,"date_of_create":"Wed May 05 2021 02:43:04 GMT+0000 (Coordinated Universal Time)","updatedAt":"2021-05-05T02:46:19.613Z","createdAt":"2021-05-05T02:43:04.362Z","__v":0}]
     */

    private String rest_id;
    private String table_no;
    private String taken_id;
    private String order_date_book;
    /**
     * _id : 60920638ccb9375cfa41b8a2
     * rest_id : 608f6fc9bb5e115d275c28b4
     * category_id : 608f83080ce4f06a62055b59
     * item_id : 608fd7942392940d525dcaaa
     * waiter_id : 608f701fbb5e115d275c28b8
     * table_no : 1
     * item_name : Coffee
     * item_price : 100
     * item_status : New Booking
     * item_count : 1
     * date_of_create : Wed May 05 2021 02:43:04 GMT+0000 (Coordinated Universal Time)
     * updatedAt : 2021-05-05T02:46:19.613Z
     * createdAt : 2021-05-05T02:43:04.362Z
     * __v : 0
     */

    private List<ItemDetailBean> item_detail;

    public String getRest_id() {
        return rest_id;
    }

    public void setRest_id(String rest_id) {
        this.rest_id = rest_id;
    }

    public String getTable_no() {
        return table_no;
    }

    public void setTable_no(String table_no) {
        this.table_no = table_no;
    }

    public String getTaken_id() {
        return taken_id;
    }

    public void setTaken_id(String taken_id) {
        this.taken_id = taken_id;
    }

    public String getOrder_date_book() {
        return order_date_book;
    }

    public void setOrder_date_book(String order_date_book) {
        this.order_date_book = order_date_book;
    }

    public List<ItemDetailBean> getItem_detail() {
        return item_detail;
    }

    public void setItem_detail(List<ItemDetailBean> item_detail) {
        this.item_detail = item_detail;
    }

    public static class ItemDetailBean {
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
