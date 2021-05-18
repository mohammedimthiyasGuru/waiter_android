package com.example.ordertakingapp.response;

public class BlockUnBlockChefResponse {


    /**
     * Status : Success
     * Message : Chef Updated
     * Data : {"_id":"609900c677ada17c96829761","rest_id":"6098ff1b074e747b0fcd04b5","chef_name":"Imthiyas","chef_number":9514497862,"chef_emailid":"imthi@gmail.com","chef_address":"Chennai","chef_emergency_no":9876543210,"chef_status":"false","date_of_create":"Mon May 10 2021 15:15:42 GMT+0530 (India Standard Time)","updatedAt":"2021-05-18T09:28:09.980Z","createdAt":"2021-05-10T09:45:42.847Z","__v":0}
     * Code : 200
     */

    private String Status;
    private String Message;
    /**
     * _id : 609900c677ada17c96829761
     * rest_id : 6098ff1b074e747b0fcd04b5
     * chef_name : Imthiyas
     * chef_number : 9514497862
     * chef_emailid : imthi@gmail.com
     * chef_address : Chennai
     * chef_emergency_no : 9876543210
     * chef_status : false
     * date_of_create : Mon May 10 2021 15:15:42 GMT+0530 (India Standard Time)
     * updatedAt : 2021-05-18T09:28:09.980Z
     * createdAt : 2021-05-10T09:45:42.847Z
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
        private String chef_name;
        private long chef_number;
        private String chef_emailid;
        private String chef_address;
        private long chef_emergency_no;
        private String chef_status;
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

        public String getChef_name() {
            return chef_name;
        }

        public void setChef_name(String chef_name) {
            this.chef_name = chef_name;
        }

        public long getChef_number() {
            return chef_number;
        }

        public void setChef_number(long chef_number) {
            this.chef_number = chef_number;
        }

        public String getChef_emailid() {
            return chef_emailid;
        }

        public void setChef_emailid(String chef_emailid) {
            this.chef_emailid = chef_emailid;
        }

        public String getChef_address() {
            return chef_address;
        }

        public void setChef_address(String chef_address) {
            this.chef_address = chef_address;
        }

        public long getChef_emergency_no() {
            return chef_emergency_no;
        }

        public void setChef_emergency_no(long chef_emergency_no) {
            this.chef_emergency_no = chef_emergency_no;
        }

        public String getChef_status() {
            return chef_status;
        }

        public void setChef_status(String chef_status) {
            this.chef_status = chef_status;
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
