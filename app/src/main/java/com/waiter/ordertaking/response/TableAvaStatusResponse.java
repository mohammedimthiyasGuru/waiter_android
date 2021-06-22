package com.waiter.ordertaking.response;

public class TableAvaStatusResponse {


    /**
     * Status : Success
     * Message : Table Updated
     * Data : {"_id":"608f6ff3bb5e115d275c28b7","rest_id":"608f6fc9bb5e115d275c28b4","table_no":"2","table_desc":".","table_status":"true","table_color_code":"#E59866","table_order_status":"Order_inprogess","date_of_create":"Mon May 03 2021 09:07:22 GMT+0530 (India Standard Time)","updatedAt":"2021-05-09T07:53:08.253Z","createdAt":"2021-05-03T03:37:23.402Z","__v":0}
     * Code : 200
     */

    private String Status;
    private String Message;
    /**
     * _id : 608f6ff3bb5e115d275c28b7
     * rest_id : 608f6fc9bb5e115d275c28b4
     * table_no : 2
     * table_desc : .
     * table_status : true
     * table_color_code : #E59866
     * table_order_status : Order_inprogess
     * date_of_create : Mon May 03 2021 09:07:22 GMT+0530 (India Standard Time)
     * updatedAt : 2021-05-09T07:53:08.253Z
     * createdAt : 2021-05-03T03:37:23.402Z
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
        private String table_no;
        private String table_desc;
        private String table_status;
        private String table_color_code;
        private String table_order_status;
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

        public String getTable_no() {
            return table_no;
        }

        public void setTable_no(String table_no) {
            this.table_no = table_no;
        }

        public String getTable_desc() {
            return table_desc;
        }

        public void setTable_desc(String table_desc) {
            this.table_desc = table_desc;
        }

        public String getTable_status() {
            return table_status;
        }

        public void setTable_status(String table_status) {
            this.table_status = table_status;
        }

        public String getTable_color_code() {
            return table_color_code;
        }

        public void setTable_color_code(String table_color_code) {
            this.table_color_code = table_color_code;
        }

        public String getTable_order_status() {
            return table_order_status;
        }

        public void setTable_order_status(String table_order_status) {
            this.table_order_status = table_order_status;
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
