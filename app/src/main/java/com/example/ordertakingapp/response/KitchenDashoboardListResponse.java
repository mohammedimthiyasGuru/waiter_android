package com.example.ordertakingapp.response;

import java.util.List;

public class KitchenDashoboardListResponse {


    /**
     * Status : Success
     * Message : Kitchen Details
     * Data : [{"_id":"60a23587c097ea292a56ac9a","order_id":"1621243271346","rest_id":"6098ff1b074e747b0fcd04b5","table_no":"1","table_name":"1","taken_by":"Dinesh","order_at":"17-05-2021 02:51 PM","status":"Completed","chef_status":"Completed"}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * _id : 60a23587c097ea292a56ac9a
     * order_id : 1621243271346
     * rest_id : 6098ff1b074e747b0fcd04b5
     * table_no : 1
     * table_name : 1
     * taken_by : Dinesh
     * order_at : 17-05-2021 02:51 PM
     * status : Completed
     * chef_status : Completed
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
        private String order_id;
        private String rest_id;
        private String table_no;
        private String table_name;
        private String taken_by;
        private String order_at;
        private String status;
        private String chef_status;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
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

        public String getTable_name() {
            return table_name;
        }

        public void setTable_name(String table_name) {
            this.table_name = table_name;
        }

        public String getTaken_by() {
            return taken_by;
        }

        public void setTaken_by(String taken_by) {
            this.taken_by = taken_by;
        }

        public String getOrder_at() {
            return order_at;
        }

        public void setOrder_at(String order_at) {
            this.order_at = order_at;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getChef_status() {
            return chef_status;
        }

        public void setChef_status(String chef_status) {
            this.chef_status = chef_status;
        }
    }
}
