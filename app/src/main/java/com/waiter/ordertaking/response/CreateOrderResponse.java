package com.waiter.ordertaking.response;

import java.util.List;

public class CreateOrderResponse {


    /**
     * Status : Success
     * Message : category added successfully
     * Data : {"item_detail":[{"_id":"6097ccebf6f49859caf0a390","rest_id":"608f6fc9bb5e115d275c28b4","category_id":"608f83080ce4f06a62055b59","item_id":"608fd949150f6610bb719d02","waiter_id":"608f701fbb5e115d275c28b8","table_no":"1","item_name":"Tea","item_price":50,"item_status":"Booked","item_count":1,"date_of_create":"Sun May 09 2021 11:52:11 GMT+0000 (Coordinated Universal Time)","updatedAt":"2021-05-09T11:52:11.158Z","createdAt":"2021-05-09T11:52:11.158Z","__v":0}],"_id":"6097d246f6f49859caf0a393","rest_id":"608f6fc9bb5e115d275c28b4","order_id":"1620562502566","table_no":"1","taken_by":"Mohammed1","taken_id":"608f701fbb5e115d275c28b8","order_date_book":"09-05-2021 05:45 PM","order_date_complete":"","remarks":"","order_cast":0,"chef_id":"","order_status":"Booked","updatedAt":"2021-05-09T12:15:02.569Z","createdAt":"2021-05-09T12:15:02.569Z","__v":0}
     * Code : 200
     */

    private String Status;
    private String Message;
    /**
     * item_detail : [{"_id":"6097ccebf6f49859caf0a390","rest_id":"608f6fc9bb5e115d275c28b4","category_id":"608f83080ce4f06a62055b59","item_id":"608fd949150f6610bb719d02","waiter_id":"608f701fbb5e115d275c28b8","table_no":"1","item_name":"Tea","item_price":50,"item_status":"Booked","item_count":1,"date_of_create":"Sun May 09 2021 11:52:11 GMT+0000 (Coordinated Universal Time)","updatedAt":"2021-05-09T11:52:11.158Z","createdAt":"2021-05-09T11:52:11.158Z","__v":0}]
     * _id : 6097d246f6f49859caf0a393
     * rest_id : 608f6fc9bb5e115d275c28b4
     * order_id : 1620562502566
     * table_no : 1
     * taken_by : Mohammed1
     * taken_id : 608f701fbb5e115d275c28b8
     * order_date_book : 09-05-2021 05:45 PM
     * order_date_complete :
     * remarks :
     * order_cast : 0
     * chef_id :
     * order_status : Booked
     * updatedAt : 2021-05-09T12:15:02.569Z
     * createdAt : 2021-05-09T12:15:02.569Z
     * __v : 0
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
        private String _id;
        private String rest_id;
        private String order_id;
        private String table_no;
        private String taken_by;
        private String taken_id;
        private String order_date_book;
        private String order_date_complete;
        private String remarks;
        private int order_cast;
        private String chef_id;
        private String order_status;
        private String updatedAt;
        private String createdAt;
        private int __v;
        /**
         * _id : 6097ccebf6f49859caf0a390
         * rest_id : 608f6fc9bb5e115d275c28b4
         * category_id : 608f83080ce4f06a62055b59
         * item_id : 608fd949150f6610bb719d02
         * waiter_id : 608f701fbb5e115d275c28b8
         * table_no : 1
         * item_name : Tea
         * item_price : 50
         * item_status : Booked
         * item_count : 1
         * date_of_create : Sun May 09 2021 11:52:11 GMT+0000 (Coordinated Universal Time)
         * updatedAt : 2021-05-09T11:52:11.158Z
         * createdAt : 2021-05-09T11:52:11.158Z
         * __v : 0
         */

        private List<ItemDetailBean> item_detail;

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

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getTable_no() {
            return table_no;
        }

        public void setTable_no(String table_no) {
            this.table_no = table_no;
        }

        public String getTaken_by() {
            return taken_by;
        }

        public void setTaken_by(String taken_by) {
            this.taken_by = taken_by;
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

        public String getOrder_date_complete() {
            return order_date_complete;
        }

        public void setOrder_date_complete(String order_date_complete) {
            this.order_date_complete = order_date_complete;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public int getOrder_cast() {
            return order_cast;
        }

        public void setOrder_cast(int order_cast) {
            this.order_cast = order_cast;
        }

        public String getChef_id() {
            return chef_id;
        }

        public void setChef_id(String chef_id) {
            this.chef_id = chef_id;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
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
}
