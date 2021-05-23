package com.example.ordertakingapp.response;

import java.util.List;

public class KitchenDashoboardListResponse {


    /**
     * Status : Success
     * Message : Waiter History Details
     * Data : [{"_id":"60a5eeab785e571920ac46f7","order_id":"1621487275551","rest_id":"6098ff1b074e747b0fcd04b5","table_no":"6","table_name":"6","taken_by":"Dinesh","order_at":"20-05-2021 10:37 AM","status":"Completed","chef_status":"Completed","waiter_status":"Booked"},{"_id":"60a5eec9785e571920ac46fa","order_id":"1621487305975","rest_id":"6098ff1b074e747b0fcd04b5","table_no":"3","table_name":"3","taken_by":"Dinesh","order_at":"20-05-2021 10:38 AM","status":"Completed","chef_status":"Completed","waiter_status":"Completed"},{"_id":"60a5eed5785e571920ac46fe","order_id":"1621487317717","rest_id":"6098ff1b074e747b0fcd04b5","table_no":"4","table_name":"4","taken_by":"Dinesh","order_at":"20-05-2021 10:38 AM","status":"Completed","chef_status":"Completed","waiter_status":"Booked"},{"_id":"60a5eee2785e571920ac4704","order_id":"1621487330109","rest_id":"6098ff1b074e747b0fcd04b5","table_no":"5","table_name":"5","taken_by":"Dinesh","order_at":"20-05-2021 10:38 AM","status":"Booked","chef_status":"","waiter_status":"Completed"},{"_id":"60a5eeeb785e571920ac4708","order_id":"1621487339321","rest_id":"6098ff1b074e747b0fcd04b5","table_no":"6","table_name":"6","taken_by":"Dinesh","order_at":"20-05-2021 10:38 AM","status":"Booked","chef_status":"","waiter_status":null}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * _id : 60a5eeab785e571920ac46f7
     * order_id : 1621487275551
     * rest_id : 6098ff1b074e747b0fcd04b5
     * table_no : 6
     * table_name : 6
     * taken_by : Dinesh
     * order_at : 20-05-2021 10:37 AM
     * status : Completed
     * chef_status : Completed
     * waiter_status : Booked
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
        private String waiter_status;

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

        public String getWaiter_status() {
            return waiter_status;
        }

        public void setWaiter_status(String waiter_status) {
            this.waiter_status = waiter_status;
        }
    }
}
