package com.waiter.ordertaking.response;

import java.util.List;

public class WaiterAdminRequestListResponse {

    /**
     * Status : Success
     * Message : Waiter Request list
     * Data : [{"_id":"60a661ee1b2b1a47fc670628","rest_id":"6098ff1b074e747b0fcd04b5","waiter_id":"60a3b19a9bbb7779da13ac7f","waiter_name":"Dinesh","type":"Waiter","title":"Need Food","request_text":"I need food to eat the, i am hungry","request_date":"23-10-2020 11:00 AM","response_text":"","response_date":"","date_of_create":""},{"_id":"60a662851b2b1a47fc670629","rest_id":"6098ff1b074e747b0fcd04b5","waiter_id":"60a3b19a9bbb7779da13ac7f","waiter_name":"Dinesh","type":"Waiter","title":"Need Food","request_text":"I need food to eat the, i am hungry","request_date":"23-10-2020 11:00 AM","response_text":"ok will provide you shorlt ly ","response_date":"23-10-2020 12:00 AM","date_of_create":""}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * _id : 60a661ee1b2b1a47fc670628
     * rest_id : 6098ff1b074e747b0fcd04b5
     * waiter_id : 60a3b19a9bbb7779da13ac7f
     * waiter_name : Dinesh
     * type : Waiter
     * title : Need Food
     * request_text : I need food to eat the, i am hungry
     * request_date : 23-10-2020 11:00 AM
     * response_text :
     * response_date :
     * date_of_create :
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
        private String rest_id;
        private String waiter_id;
        private String waiter_name;
        private String type;
        private String title;
        private String request_text;
        private String request_date;
        private String response_text;
        private String response_date;
        private String date_of_create;

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

        public String getWaiter_id() {
            return waiter_id;
        }

        public void setWaiter_id(String waiter_id) {
            this.waiter_id = waiter_id;
        }

        public String getWaiter_name() {
            return waiter_name;
        }

        public void setWaiter_name(String waiter_name) {
            this.waiter_name = waiter_name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getRequest_text() {
            return request_text;
        }

        public void setRequest_text(String request_text) {
            this.request_text = request_text;
        }

        public String getRequest_date() {
            return request_date;
        }

        public void setRequest_date(String request_date) {
            this.request_date = request_date;
        }

        public String getResponse_text() {
            return response_text;
        }

        public void setResponse_text(String response_text) {
            this.response_text = response_text;
        }

        public String getResponse_date() {
            return response_date;
        }

        public void setResponse_date(String response_date) {
            this.response_date = response_date;
        }

        public String getDate_of_create() {
            return date_of_create;
        }

        public void setDate_of_create(String date_of_create) {
            this.date_of_create = date_of_create;
        }
    }
}
