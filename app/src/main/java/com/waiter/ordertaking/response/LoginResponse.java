package com.waiter.ordertaking.response;

public class LoginResponse {

    /**
     * Code : 200
     * Data : {"__v":0,"_id":"6063dff5a01e062f589108a1","active_status":true,"createdAt":"2021-03-31T02:35:33.919Z","created_by":"admin","phone_no":"123452","rest_id":"rest_id","updatedAt":"2021-03-31T02:35:33.919Z","user_name":"New","user_type":1}
     * Message : User Details
     * Status : Success
     */

    private int Code;
    /**
     * __v : 0
     * _id : 6063dff5a01e062f589108a1
     * active_status : true
     * createdAt : 2021-03-31T02:35:33.919Z
     * created_by : admin
     * phone_no : 123452
     * rest_id : rest_id
     * updatedAt : 2021-03-31T02:35:33.919Z
     * user_name : New
     * user_type : 1
     */

    private DataBean Data;
    private String Message;
    private String Status;

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public static class DataBean {
        private int __v;
        private String _id;
        private boolean active_status;
        private String createdAt;
        private String created_by;
        private String phone_no;
        private String rest_id;
        private String updatedAt;
        private String user_name;
        private int user_type;

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public boolean isActive_status() {
            return active_status;
        }

        public void setActive_status(boolean active_status) {
            this.active_status = active_status;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getCreated_by() {
            return created_by;
        }

        public void setCreated_by(String created_by) {
            this.created_by = created_by;
        }

        public String getPhone_no() {
            return phone_no;
        }

        public void setPhone_no(String phone_no) {
            this.phone_no = phone_no;
        }

        public String getRest_id() {
            return rest_id;
        }

        public void setRest_id(String rest_id) {
            this.rest_id = rest_id;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public int getUser_type() {
            return user_type;
        }

        public void setUser_type(int user_type) {
            this.user_type = user_type;
        }
    }
}
