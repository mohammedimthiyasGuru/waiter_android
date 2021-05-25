package com.example.ordertakingapp.response;

import java.util.List;

public class AdminRequestListResponse {


    /**
     * Status : Success
     * Message : Admin Request list
     * Data : [{"_id":"60a661ee1b2b1a47fc670628","rest_id":"6098ff1b074e747b0fcd04b5","user_name":"Dinesh","type":"Waiter","title":"Need Food","request_text":"I need food to eat the, i am hungry","request_date":"23-10-2020 11:00 AM","response_text":"","response_date":"","date_of_create":""},{"_id":"60a662851b2b1a47fc670629","rest_id":"6098ff1b074e747b0fcd04b5","user_name":"Dinesh","type":"Waiter","title":"Need Food","request_text":"I need food to eat the, i am hungry","request_date":"23-10-2020 11:00 AM","response_text":"ok will provide you shorlt ly ","response_date":"23-10-2020 12:00 AM","date_of_create":""},{"_id":"60a7609af7606361420a4ea7","rest_id":"6098ff1b074e747b0fcd04b5","user_name":"Dinesh","type":"Waiter","title":"Need Food","request_text":"I need food to eat the, i am hungry","request_date":"23-10-2020 11:00 AM","response_text":"","response_date":"","date_of_create":""},{"_id":"60a7611bf7606361420a4ea8","rest_id":"6098ff1b074e747b0fcd04b5","user_name":"Dinesh","type":"Waiter","title":"Need lucnh","request_text":"I need food to eat the, i am hungry","request_date":"21-05-2021 12:58 PM","response_text":"","response_date":"","date_of_create":""},{"_id":"60a7612cf7606361420a4ea9","rest_id":"6098ff1b074e747b0fcd04b5","user_name":"Dinesh","type":"Waiter","title":"Need dinner","request_text":"I need food to eat the, i am hungry","request_date":"21-05-2021 12:59 PM","response_text":"","response_date":"","date_of_create":""},{"_id":"60a7632cf7606361420a4eaa","rest_id":"6098ff1b074e747b0fcd04b5","user_name":"Dinesh","type":"Waiter","title":"Need dinner","request_text":"I need food to eat the, i am hungry","request_date":"21-05-2021 12:59 PM","response_text":"I need food to eat the","response_date":"21-05-2021 12:59 PM","date_of_create":""},{"_id":"60a766b0f7606361420a4eab","rest_id":"6098ff1b074e747b0fcd04b5","user_name":"Dinesh","type":"Waiter","title":"Need dinner","request_text":"I need food to eat the, i am hungry","request_date":"21-05-2021 12:59 PM","response_text":"I need food to eat the","response_date":"21-05-2021 12:59 PM","date_of_create":""},{"_id":"60a766b2f7606361420a4eac","rest_id":"6098ff1b074e747b0fcd04b5","user_name":"Dinesh","type":"Waiter","title":"Need dinner","request_text":"I need food to eat the, i am hungry","request_date":"21-05-2021 12:59 PM","response_text":"I need food to eat the","response_date":"21-05-2021 12:59 PM","date_of_create":""},{"_id":"60a7802cf7606361420a4ead","rest_id":"6098ff1b074e747b0fcd04b5","user_name":"","type":"3","title":"Select Category Title","request_text":"","request_date":"21-05-2021 03:10 PM","response_text":"","response_date":"","date_of_create":""},{"_id":"60a786aaf7606361420a4eae","rest_id":"6098ff1b074e747b0fcd04b5","user_name":"","type":"3","title":"Title 2","request_text":"I need food","request_date":"21-05-2021 03:38 PM","response_text":"","response_date":"","date_of_create":""},{"_id":"60a8cf8cec1a190bb0ba838f","rest_id":"6098ff1b074e747b0fcd04b5","user_name":"","type":"3","title":"Title 1","request_text":"this is good for all ","request_date":"22-05-2021 03:01 PM","response_text":"","response_date":"","date_of_create":""},{"_id":"60aa036d33770e27242a0353","rest_id":"6098ff1b074e747b0fcd04b5","user_name":"","type":"3","title":"Title 1","request_text":"Food Details","request_date":"23-05-2021 12:55 PM","response_text":"","response_date":"","date_of_create":""},{"_id":"60ac19d76f69ee0dfc266ab7","rest_id":"6098ff1b074e747b0fcd04b5","user_name":"Dinesh","type":"Chef","title":"Need Food","request_text":"I need food to eat the, i am hungry","request_date":"23-10-2020 11:00 AM","response_text":"","response_date":"","date_of_create":""},{"_id":"60acb20e68492a4567b3f50c","rest_id":"6098ff1b074e747b0fcd04b5","user_name":"","type":"3","title":"Title 1","request_text":"jrjebeb","request_date":"25-05-2021 01:45 PM","response_text":"","response_date":"","date_of_create":""},{"_id":"60acd3cb68492a4567b3f52c","rest_id":"6098ff1b074e747b0fcd04b5","user_name":"Dinesh","type":"Chef","title":"Need Food","request_text":"I need food to eat the, i am hungry","request_date":"23-10-2020 11:00 AM","response_text":"","response_date":"","date_of_create":""}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * _id : 60a661ee1b2b1a47fc670628
     * rest_id : 6098ff1b074e747b0fcd04b5
     * user_name : Dinesh
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
        private String user_name;
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

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
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
