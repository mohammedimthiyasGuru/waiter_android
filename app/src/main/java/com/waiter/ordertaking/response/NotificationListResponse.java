package com.waiter.ordertaking.response;

import java.util.List;

public class NotificationListResponse {

    /**
     * Status : Success
     * Message : User Notification List
     * Data : [{"_id":"60acf4ee68492a4567b3f550","rest_id":"6098ff1b074e747b0fcd04b5","user_id":"6098ff1b074e747b0fcd04b5","notify_title":"Order Booked","notify_descri":"Order Accept by waiter Dinesh","notify_img":"http://230.png","notify_time":"23-10-2021 11:00 AM","date_and_time":"23-10-2021 11:00 AM","delete_status":false,"read_status":false,"updatedAt":"2021-05-25T13:00:30.178Z","createdAt":"2021-05-25T13:00:30.178Z","__v":0},{"_id":"60acf50368492a4567b3f551","rest_id":"6098ff1b074e747b0fcd04b5","user_id":"6098ff1b074e747b0fcd04b5","notify_title":"Order Dispace","notify_descri":"Order Dispacthced by sriram","notify_img":"http://230.png","notify_time":"23-10-2021 11:00 AM","date_and_time":"23-10-2021 11:00 AM","delete_status":false,"read_status":false,"updatedAt":"2021-05-25T13:00:51.374Z","createdAt":"2021-05-25T13:00:51.374Z","__v":0},{"_id":"60acf50768492a4567b3f552","rest_id":"6098ff1b074e747b0fcd04b5","user_id":"6098ff1b074e747b0fcd04b5","notify_title":"Order Dispace","notify_descri":"Order Dispacthced by sriram","notify_img":"http://230.png","notify_time":"23-10-2021 11:00 AM","date_and_time":"23-10-2021 11:00 AM","delete_status":false,"read_status":false,"updatedAt":"2021-05-25T13:00:55.981Z","createdAt":"2021-05-25T13:00:55.981Z","__v":0},{"_id":"60acf50868492a4567b3f553","rest_id":"6098ff1b074e747b0fcd04b5","user_id":"6098ff1b074e747b0fcd04b5","notify_title":"Order Dispace","notify_descri":"Order Dispacthced by sriram","notify_img":"http://230.png","notify_time":"23-10-2021 11:00 AM","date_and_time":"23-10-2021 11:00 AM","delete_status":false,"read_status":false,"updatedAt":"2021-05-25T13:00:56.797Z","createdAt":"2021-05-25T13:00:56.797Z","__v":0},{"_id":"60acf50a68492a4567b3f554","rest_id":"6098ff1b074e747b0fcd04b5","user_id":"6098ff1b074e747b0fcd04b5","notify_title":"Order Dispace","notify_descri":"Order Dispacthced by sriram","notify_img":"http://230.png","notify_time":"23-10-2021 11:00 AM","date_and_time":"23-10-2021 11:00 AM","delete_status":false,"read_status":false,"updatedAt":"2021-05-25T13:00:58.053Z","createdAt":"2021-05-25T13:00:58.053Z","__v":0},{"_id":"60acf50b68492a4567b3f555","rest_id":"6098ff1b074e747b0fcd04b5","user_id":"6098ff1b074e747b0fcd04b5","notify_title":"Order Dispace","notify_descri":"Order Dispacthced by sriram","notify_img":"http://230.png","notify_time":"23-10-2021 11:00 AM","date_and_time":"23-10-2021 11:00 AM","delete_status":false,"read_status":false,"updatedAt":"2021-05-25T13:00:59.661Z","createdAt":"2021-05-25T13:00:59.661Z","__v":0}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * _id : 60acf4ee68492a4567b3f550
     * rest_id : 6098ff1b074e747b0fcd04b5
     * user_id : 6098ff1b074e747b0fcd04b5
     * notify_title : Order Booked
     * notify_descri : Order Accept by waiter Dinesh
     * notify_img : http://230.png
     * notify_time : 23-10-2021 11:00 AM
     * date_and_time : 23-10-2021 11:00 AM
     * delete_status : false
     * read_status : false
     * updatedAt : 2021-05-25T13:00:30.178Z
     * createdAt : 2021-05-25T13:00:30.178Z
     * __v : 0
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
        private String user_id;
        private String notify_title;
        private String notify_descri;
        private String notify_img;
        private String notify_time;
        private String date_and_time;
        private boolean delete_status;
        private boolean read_status;
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

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getNotify_title() {
            return notify_title;
        }

        public void setNotify_title(String notify_title) {
            this.notify_title = notify_title;
        }

        public String getNotify_descri() {
            return notify_descri;
        }

        public void setNotify_descri(String notify_descri) {
            this.notify_descri = notify_descri;
        }

        public String getNotify_img() {
            return notify_img;
        }

        public void setNotify_img(String notify_img) {
            this.notify_img = notify_img;
        }

        public String getNotify_time() {
            return notify_time;
        }

        public void setNotify_time(String notify_time) {
            this.notify_time = notify_time;
        }

        public String getDate_and_time() {
            return date_and_time;
        }

        public void setDate_and_time(String date_and_time) {
            this.date_and_time = date_and_time;
        }

        public boolean isDelete_status() {
            return delete_status;
        }

        public void setDelete_status(boolean delete_status) {
            this.delete_status = delete_status;
        }

        public boolean isRead_status() {
            return read_status;
        }

        public void setRead_status(boolean read_status) {
            this.read_status = read_status;
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
