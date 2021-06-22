package com.waiter.ordertaking.response;

import java.util.List;

public class TableListResponse {

    /**
     * Status : Success
     * Message : Table Details
     * Data : [{"table_no":"1","table_status":true,"table_color_code":"#E59866","table_order_status":"Order_inprogess","rest_id":"1232424"},
     * {"table_no":"2","table_status":true,"table_color_code":"#F2F4F4","table_order_status":"","rest_id":"1232424"},
     * {"table_no":"3","table_status":false,"table_color_code":"#EC7063","table_order_status":"","rest_id":"1232424"},
     * {"table_no":"4","table_status":true,"table_color_code":"#F2F4F4","table_order_status":"","rest_id":"1232424"},
     * {"table_no":"5","table_status":true,"table_color_code":"#E59866","table_order_status":"Order_inprogess","rest_id":"1232424"},
     * {"table_no":"6","table_status":false,"table_color_code":"#EC7063","table_order_status":"","rest_id":"1232424"}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * table_no : 1
     * table_status : true
     * table_color_code : #E59866
     * table_order_status : Order_inprogess
     * rest_id : 1232424
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
        private String table_no;
        private boolean table_status;
        private String table_color_code;
        private String table_order_status;
        private String rest_id;

        public String getTable_no() {
            return table_no;
        }

        public void setTable_no(String table_no) {
            this.table_no = table_no;
        }

        public boolean isTable_status() {
            return table_status;
        }

        public void setTable_status(boolean table_status) {
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

        public String getRest_id() {
            return rest_id;
        }

        public void setRest_id(String rest_id) {
            this.rest_id = rest_id;
        }
    }
}
