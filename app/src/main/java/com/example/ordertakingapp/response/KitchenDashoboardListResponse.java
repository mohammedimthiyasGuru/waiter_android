package com.example.ordertakingapp.response;

import java.util.List;

public class KitchenDashoboardListResponse {

    /**
     * Status : Success
     * Message : Kitchen Details
     * Data : [{"rest_id":"1234","table_no":"1","table_name":"MK1","taken_by":"Mohammed-(1234)","order_at":"23-10-2012 01:00 AM","status":"Booked"},{"rest_id":"1234","table_no":"2","table_name":"MK2","taken_by":"Dinesh-(1234)","order_at":"23-10-2012 12:00 AM","status":"Order Accept"},{"rest_id":"1234","table_no":"3","table_name":"MK3","taken_by":"Sriram-(1234)","order_at":"23-10-2012 04:00 AM","status":"Order Delivery"},{"rest_id":"1234","table_no":"4","table_name":"MK4","taken_by":"Mohammed-(1234)","order_at":"23-10-2012 09:00 AM","status":"Booked"},{"rest_id":"1234","table_no":"5","table_name":"MK5","taken_by":"Mohammed-(1234)","order_at":"23-10-2012 10:00 AM","status":"Booked"},{"rest_id":"1234","table_no":"6","table_name":"MK6","taken_by":"Mohammed-(1234)","order_at":"23-10-2012 12:00 AM","status":"Booked"}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * rest_id : 1234
     * table_no : 1
     * table_name : MK1
     * taken_by : Mohammed-(1234)
     * order_at : 23-10-2012 01:00 AM
     * status : Booked
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
        private String rest_id;
        private String table_no;
        private String table_name;
        private String taken_by;
        private String order_at;
        private String status;

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
    }
}
