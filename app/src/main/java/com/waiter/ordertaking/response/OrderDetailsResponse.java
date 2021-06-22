package com.waiter.ordertaking.response;

import java.util.List;

public class OrderDetailsResponse {

    /**
     * Status : Success
     * Message : order list details
     * Data : {"item_detail":[{"__v":0,"_id":"60a2357ec097ea292a56ac95","category_id":"6099012177ada17c96829763","createdAt":"2021-05-17T09:21:02.965Z","date_of_create":"Mon May 17 2021 09:21:02 GMT+0000 (Coordinated Universal Time)","item_count":2,"item_id":"6099018e77ada17c96829768","item_name":"Bucket Combo","item_price":500,"item_status":"Completed","table_no":"1","updatedAt":"2021-05-17T09:21:03.278Z","waiter_id":"6099003577ada17c9682975f"},{"__v":0,"_id":"60a2357fc097ea292a56ac96","category_id":"6099012177ada17c96829763","createdAt":"2021-05-17T09:21:03.787Z","date_of_create":"Mon May 17 2021 09:21:03 GMT+0000 (Coordinated Universal Time)","item_count":2,"item_id":"609901aa77ada17c96829769","item_name":"Special Feast","item_price":500,"item_status":"Completed","table_no":"1","updatedAt":"2021-05-17T09:21:04.920Z","waiter_id":"6099003577ada17c9682975f"},{"__v":0,"_id":"60a23580c097ea292a56ac97","category_id":"6099012177ada17c96829763","createdAt":"2021-05-17T09:21:04.387Z","date_of_create":"Mon May 17 2021 09:21:04 GMT+0000 (Coordinated Universal Time)","item_count":2,"item_id":"609901bb77ada17c9682976a","item_name":"Simple Feast","item_price":130,"item_status":"Completed","table_no":"1","updatedAt":"2021-05-17T09:21:05.275Z","waiter_id":"6099003577ada17c9682975f"},{"__v":0,"_id":"60a23582c097ea292a56ac98","category_id":"6099012177ada17c96829763","createdAt":"2021-05-17T09:21:06.852Z","date_of_create":"Mon May 17 2021 09:21:06 GMT+0000 (Coordinated Universal Time)","item_count":1,"item_id":"6099021677ada17c9682976e","item_name":"Crispy Drumstick","item_price":60,"item_status":"Completed","table_no":"1","updatedAt":"2021-05-17T09:21:06.852Z","waiter_id":"6099003577ada17c9682975f"},{"__v":0,"_id":"60a23583c097ea292a56ac99","category_id":"6099012177ada17c96829763","createdAt":"2021-05-17T09:21:07.174Z","date_of_create":"Mon May 17 2021 09:21:07 GMT+0000 (Coordinated Universal Time)","item_count":1,"item_id":"609902e777ada17c96829770","item_name":"Chocolat With Ice SW","item_price":80,"item_status":"InProgress","table_no":"1","updatedAt":"2021-05-17T09:21:07.174Z","waiter_id":"6099003577ada17c9682975f"}],"_id":"60a23587c097ea292a56ac9a","rest_id":"6098ff1b074e747b0fcd04b5","order_id":"1621243271346","table_no":"1","taken_by":"Dinesh","taken_id":"6099003577ada17c9682975f","order_date_book":"17-05-2021 02:51 PM","order_date_complete":"","remarks":"","order_cast":0,"chef_id":"609900e577ada17c96829762","order_status":"Completed","chef_status":"Completed","waiter_status":"Completed","updatedAt":"2021-05-17T11:42:44.329Z","createdAt":"2021-05-17T09:21:11.348Z","__v":0}
     * Code : 200
     */

    private String Status;
    private String Message;
    /**
     * item_detail : [{"__v":0,"_id":"60a2357ec097ea292a56ac95","category_id":"6099012177ada17c96829763","createdAt":"2021-05-17T09:21:02.965Z","date_of_create":"Mon May 17 2021 09:21:02 GMT+0000 (Coordinated Universal Time)","item_count":2,"item_id":"6099018e77ada17c96829768","item_name":"Bucket Combo","item_price":500,"item_status":"Completed","table_no":"1","updatedAt":"2021-05-17T09:21:03.278Z","waiter_id":"6099003577ada17c9682975f"},{"__v":0,"_id":"60a2357fc097ea292a56ac96","category_id":"6099012177ada17c96829763","createdAt":"2021-05-17T09:21:03.787Z","date_of_create":"Mon May 17 2021 09:21:03 GMT+0000 (Coordinated Universal Time)","item_count":2,"item_id":"609901aa77ada17c96829769","item_name":"Special Feast","item_price":500,"item_status":"Completed","table_no":"1","updatedAt":"2021-05-17T09:21:04.920Z","waiter_id":"6099003577ada17c9682975f"},{"__v":0,"_id":"60a23580c097ea292a56ac97","category_id":"6099012177ada17c96829763","createdAt":"2021-05-17T09:21:04.387Z","date_of_create":"Mon May 17 2021 09:21:04 GMT+0000 (Coordinated Universal Time)","item_count":2,"item_id":"609901bb77ada17c9682976a","item_name":"Simple Feast","item_price":130,"item_status":"Completed","table_no":"1","updatedAt":"2021-05-17T09:21:05.275Z","waiter_id":"6099003577ada17c9682975f"},{"__v":0,"_id":"60a23582c097ea292a56ac98","category_id":"6099012177ada17c96829763","createdAt":"2021-05-17T09:21:06.852Z","date_of_create":"Mon May 17 2021 09:21:06 GMT+0000 (Coordinated Universal Time)","item_count":1,"item_id":"6099021677ada17c9682976e","item_name":"Crispy Drumstick","item_price":60,"item_status":"Completed","table_no":"1","updatedAt":"2021-05-17T09:21:06.852Z","waiter_id":"6099003577ada17c9682975f"},{"__v":0,"_id":"60a23583c097ea292a56ac99","category_id":"6099012177ada17c96829763","createdAt":"2021-05-17T09:21:07.174Z","date_of_create":"Mon May 17 2021 09:21:07 GMT+0000 (Coordinated Universal Time)","item_count":1,"item_id":"609902e777ada17c96829770","item_name":"Chocolat With Ice SW","item_price":80,"item_status":"InProgress","table_no":"1","updatedAt":"2021-05-17T09:21:07.174Z","waiter_id":"6099003577ada17c9682975f"}]
     * _id : 60a23587c097ea292a56ac9a
     * rest_id : 6098ff1b074e747b0fcd04b5
     * order_id : 1621243271346
     * table_no : 1
     * taken_by : Dinesh
     * taken_id : 6099003577ada17c9682975f
     * order_date_book : 17-05-2021 02:51 PM
     * order_date_complete :
     * remarks :
     * order_cast : 0
     * chef_id : 609900e577ada17c96829762
     * order_status : Completed
     * chef_status : Completed
     * waiter_status : Completed
     * updatedAt : 2021-05-17T11:42:44.329Z
     * createdAt : 2021-05-17T09:21:11.348Z
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
        private String chef_status;
        private String waiter_status;
        private String updatedAt;
        private String createdAt;
        private int __v;
        /**
         * __v : 0
         * _id : 60a2357ec097ea292a56ac95
         * category_id : 6099012177ada17c96829763
         * createdAt : 2021-05-17T09:21:02.965Z
         * date_of_create : Mon May 17 2021 09:21:02 GMT+0000 (Coordinated Universal Time)
         * item_count : 2
         * item_id : 6099018e77ada17c96829768
         * item_name : Bucket Combo
         * item_price : 500
         * item_status : Completed
         * table_no : 1
         * updatedAt : 2021-05-17T09:21:03.278Z
         * waiter_id : 6099003577ada17c9682975f
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

        public String getChef_status() {
            return chef_status;
        }

        public void setChef_status(String chef_status) {
            this.chef_status = chef_status;
        }

        public String getWaiter_status() {
            return waiter_status;
        }

        public void setWaiter_status(String waiter_status) {
            this.waiter_status = waiter_status;
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
            private int __v;
            private String _id;
            private String category_id;
            private String createdAt;
            private String date_of_create;
            private int item_count;
            private String item_id;
            private String item_name;
            private int item_price;
            private String item_status;
            private String table_no;
            private String updatedAt;
            private String waiter_id;

            public int get__v() {
                return __v;
            }

            public void set__v(int __v) {
                this.__v = __v;
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getDate_of_create() {
                return date_of_create;
            }

            public void setDate_of_create(String date_of_create) {
                this.date_of_create = date_of_create;
            }

            public int getItem_count() {
                return item_count;
            }

            public void setItem_count(int item_count) {
                this.item_count = item_count;
            }

            public String getItem_id() {
                return item_id;
            }

            public void setItem_id(String item_id) {
                this.item_id = item_id;
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

            public String getTable_no() {
                return table_no;
            }

            public void setTable_no(String table_no) {
                this.table_no = table_no;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public String getWaiter_id() {
                return waiter_id;
            }

            public void setWaiter_id(String waiter_id) {
                this.waiter_id = waiter_id;
            }
        }
    }
}
