package com.example.ordertakingapp.response;

public class BlockUnBlockWaiterResponse {


    /**
     * Status : Success
     * Message : Waiter Updated
     * Data : {"_id":"6099001377ada17c9682975e","rest_id":"6098ff1b074e747b0fcd04b5","waiter_name":"Santhosh","waiter_number":9876543212,"waiter_emailid":"santhosh@gmail.com","waiter_address":"Saleem","waiter_emergency_no":9876543210,"waiter_status":"true","date_of_create":"Mon May 10 2021 15:12:43 GMT+0530 (India Standard Time)","updatedAt":"2021-05-17T14:29:14.654Z","createdAt":"2021-05-10T09:42:43.668Z","__v":0}
     * Code : 200
     */

    private String Status;
    private String Message;
    /**
     * _id : 6099001377ada17c9682975e
     * rest_id : 6098ff1b074e747b0fcd04b5
     * waiter_name : Santhosh
     * waiter_number : 9876543212
     * waiter_emailid : santhosh@gmail.com
     * waiter_address : Saleem
     * waiter_emergency_no : 9876543210
     * waiter_status : true
     * date_of_create : Mon May 10 2021 15:12:43 GMT+0530 (India Standard Time)
     * updatedAt : 2021-05-17T14:29:14.654Z
     * createdAt : 2021-05-10T09:42:43.668Z
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
        private String waiter_name;
        private long waiter_number;
        private String waiter_emailid;
        private String waiter_address;
        private long waiter_emergency_no;
        private String waiter_status;
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

        public String getWaiter_name() {
            return waiter_name;
        }

        public void setWaiter_name(String waiter_name) {
            this.waiter_name = waiter_name;
        }

        public long getWaiter_number() {
            return waiter_number;
        }

        public void setWaiter_number(long waiter_number) {
            this.waiter_number = waiter_number;
        }

        public String getWaiter_emailid() {
            return waiter_emailid;
        }

        public void setWaiter_emailid(String waiter_emailid) {
            this.waiter_emailid = waiter_emailid;
        }

        public String getWaiter_address() {
            return waiter_address;
        }

        public void setWaiter_address(String waiter_address) {
            this.waiter_address = waiter_address;
        }

        public long getWaiter_emergency_no() {
            return waiter_emergency_no;
        }

        public void setWaiter_emergency_no(long waiter_emergency_no) {
            this.waiter_emergency_no = waiter_emergency_no;
        }

        public String getWaiter_status() {
            return waiter_status;
        }

        public void setWaiter_status(String waiter_status) {
            this.waiter_status = waiter_status;
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
