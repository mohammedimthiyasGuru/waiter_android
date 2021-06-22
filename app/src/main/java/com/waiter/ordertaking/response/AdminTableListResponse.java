package com.waiter.ordertaking.response;

import java.util.List;

public class AdminTableListResponse {

    /**
     * Status : Success
     * Message : Table List
     * Data : [{"_id":"60b2484467f25056fe286cae","rest_id":{"_id":"60b2434a67f25056fe286ca7","res_name":"Hotel New Ganesh Bhavan","log_id":"ganesh@gmail.com","res_contact_no":9876543210,"res_google_link":".","res_open_time":"09:00 AM","res_close_time":"10:00 PM","res_address":"725, Muthamizh Nagar, Kodungaiyur, Chennai, Tamil Nadu 600118","res_person_name":"Ganesh","res_person_contact":8765432100,"res_date_of_exp":"2021-05-31","res_status":"Active","password":"12345","create_by":"Admin","updatedAt":"2021-06-05T14:19:03.304Z","createdAt":"2021-05-29T13:36:10.459Z","__v":0,"chef_count":2,"user_count":13,"waiter_count":10},"table_no":"1","table_desc":".","table_status":"true","table_color_code":"#0262f7","table_order_status":"","date_of_create":"Sat May 29 2021 19:27:24 GMT+0530 (India Standard Time)","updatedAt":"2021-06-03T11:37:19.670Z","createdAt":"2021-05-29T13:57:24.511Z","__v":0},{"_id":"60b2484a67f25056fe286caf","rest_id":{"_id":"60b2434a67f25056fe286ca7","res_name":"Hotel New Ganesh Bhavan","log_id":"ganesh@gmail.com","res_contact_no":9876543210,"res_google_link":".","res_open_time":"09:00 AM","res_close_time":"10:00 PM","res_address":"725, Muthamizh Nagar, Kodungaiyur, Chennai, Tamil Nadu 600118","res_person_name":"Ganesh","res_person_contact":8765432100,"res_date_of_exp":"2021-05-31","res_status":"Active","password":"12345","create_by":"Admin","updatedAt":"2021-06-05T14:19:03.304Z","createdAt":"2021-05-29T13:36:10.459Z","__v":0,"chef_count":2,"user_count":13,"waiter_count":10},"table_no":"2","table_desc":".","table_status":"true","table_color_code":"#0262f7","table_order_status":"","date_of_create":"Sat May 29 2021 19:27:30 GMT+0530 (India Standard Time)","updatedAt":"2021-06-03T05:47:43.870Z","createdAt":"2021-05-29T13:57:30.506Z","__v":0},{"_id":"60b2485867f25056fe286cb0","rest_id":{"_id":"60b2434a67f25056fe286ca7","res_name":"Hotel New Ganesh Bhavan","log_id":"ganesh@gmail.com","res_contact_no":9876543210,"res_google_link":".","res_open_time":"09:00 AM","res_close_time":"10:00 PM","res_address":"725, Muthamizh Nagar, Kodungaiyur, Chennai, Tamil Nadu 600118","res_person_name":"Ganesh","res_person_contact":8765432100,"res_date_of_exp":"2021-05-31","res_status":"Active","password":"12345","create_by":"Admin","updatedAt":"2021-06-05T14:19:03.304Z","createdAt":"2021-05-29T13:36:10.459Z","__v":0,"chef_count":2,"user_count":13,"waiter_count":10},"table_no":"3","table_desc":".","table_status":"true","table_color_code":"#0262f7","table_order_status":"","date_of_create":"Sat May 29 2021 19:27:43 GMT+0530 (India Standard Time)","updatedAt":"2021-06-03T05:47:46.131Z","createdAt":"2021-05-29T13:57:44.005Z","__v":0},{"_id":"60b2485c67f25056fe286cb1","rest_id":{"_id":"60b2434a67f25056fe286ca7","res_name":"Hotel New Ganesh Bhavan","log_id":"ganesh@gmail.com","res_contact_no":9876543210,"res_google_link":".","res_open_time":"09:00 AM","res_close_time":"10:00 PM","res_address":"725, Muthamizh Nagar, Kodungaiyur, Chennai, Tamil Nadu 600118","res_person_name":"Ganesh","res_person_contact":8765432100,"res_date_of_exp":"2021-05-31","res_status":"Active","password":"12345","create_by":"Admin","updatedAt":"2021-06-05T14:19:03.304Z","createdAt":"2021-05-29T13:36:10.459Z","__v":0,"chef_count":2,"user_count":13,"waiter_count":10},"table_no":"4","table_desc":".","table_status":"true","table_color_code":"#0262f7","table_order_status":"","date_of_create":"Sat May 29 2021 19:27:48 GMT+0530 (India Standard Time)","updatedAt":"2021-06-03T05:47:48.164Z","createdAt":"2021-05-29T13:57:48.442Z","__v":0},{"_id":"60b248ae67f25056fe286cb2","rest_id":{"_id":"60b2434a67f25056fe286ca7","res_name":"Hotel New Ganesh Bhavan","log_id":"ganesh@gmail.com","res_contact_no":9876543210,"res_google_link":".","res_open_time":"09:00 AM","res_close_time":"10:00 PM","res_address":"725, Muthamizh Nagar, Kodungaiyur, Chennai, Tamil Nadu 600118","res_person_name":"Ganesh","res_person_contact":8765432100,"res_date_of_exp":"2021-05-31","res_status":"Active","password":"12345","create_by":"Admin","updatedAt":"2021-06-05T14:19:03.304Z","createdAt":"2021-05-29T13:36:10.459Z","__v":0,"chef_count":2,"user_count":13,"waiter_count":10},"table_no":"234","table_desc":".","table_status":"true","table_color_code":"#0262f7","table_order_status":"","date_of_create":"Sat May 29 2021 19:29:10 GMT+0530 (India Standard Time)","updatedAt":"2021-06-03T05:47:49.899Z","createdAt":"2021-05-29T13:59:10.798Z","__v":0}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * _id : 60b2484467f25056fe286cae
     * rest_id : {"_id":"60b2434a67f25056fe286ca7","res_name":"Hotel New Ganesh Bhavan","log_id":"ganesh@gmail.com","res_contact_no":9876543210,"res_google_link":".","res_open_time":"09:00 AM","res_close_time":"10:00 PM","res_address":"725, Muthamizh Nagar, Kodungaiyur, Chennai, Tamil Nadu 600118","res_person_name":"Ganesh","res_person_contact":8765432100,"res_date_of_exp":"2021-05-31","res_status":"Active","password":"12345","create_by":"Admin","updatedAt":"2021-06-05T14:19:03.304Z","createdAt":"2021-05-29T13:36:10.459Z","__v":0,"chef_count":2,"user_count":13,"waiter_count":10}
     * table_no : 1
     * table_desc : .
     * table_status : true
     * table_color_code : #0262f7
     * table_order_status :
     * date_of_create : Sat May 29 2021 19:27:24 GMT+0530 (India Standard Time)
     * updatedAt : 2021-06-03T11:37:19.670Z
     * createdAt : 2021-05-29T13:57:24.511Z
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
        /**
         * _id : 60b2434a67f25056fe286ca7
         * res_name : Hotel New Ganesh Bhavan
         * log_id : ganesh@gmail.com
         * res_contact_no : 9876543210
         * res_google_link : .
         * res_open_time : 09:00 AM
         * res_close_time : 10:00 PM
         * res_address : 725, Muthamizh Nagar, Kodungaiyur, Chennai, Tamil Nadu 600118
         * res_person_name : Ganesh
         * res_person_contact : 8765432100
         * res_date_of_exp : 2021-05-31
         * res_status : Active
         * password : 12345
         * create_by : Admin
         * updatedAt : 2021-06-05T14:19:03.304Z
         * createdAt : 2021-05-29T13:36:10.459Z
         * __v : 0
         * chef_count : 2
         * user_count : 13
         * waiter_count : 10
         */

        private RestIdBean rest_id;
        private String table_no;
        private String table_desc;
        private String table_status;
        private String table_color_code;
        private String table_order_status;
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

        public RestIdBean getRest_id() {
            return rest_id;
        }

        public void setRest_id(RestIdBean rest_id) {
            this.rest_id = rest_id;
        }

        public String getTable_no() {
            return table_no;
        }

        public void setTable_no(String table_no) {
            this.table_no = table_no;
        }

        public String getTable_desc() {
            return table_desc;
        }

        public void setTable_desc(String table_desc) {
            this.table_desc = table_desc;
        }

        public String getTable_status() {
            return table_status;
        }

        public void setTable_status(String table_status) {
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

        public static class RestIdBean {
            private String _id;
            private String res_name;
            private String log_id;
            private long res_contact_no;
            private String res_google_link;
            private String res_open_time;
            private String res_close_time;
            private String res_address;
            private String res_person_name;
            private long res_person_contact;
            private String res_date_of_exp;
            private String res_status;
            private String password;
            private String create_by;
            private String updatedAt;
            private String createdAt;
            private int __v;
            private int chef_count;
            private int user_count;
            private int waiter_count;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getRes_name() {
                return res_name;
            }

            public void setRes_name(String res_name) {
                this.res_name = res_name;
            }

            public String getLog_id() {
                return log_id;
            }

            public void setLog_id(String log_id) {
                this.log_id = log_id;
            }

            public long getRes_contact_no() {
                return res_contact_no;
            }

            public void setRes_contact_no(long res_contact_no) {
                this.res_contact_no = res_contact_no;
            }

            public String getRes_google_link() {
                return res_google_link;
            }

            public void setRes_google_link(String res_google_link) {
                this.res_google_link = res_google_link;
            }

            public String getRes_open_time() {
                return res_open_time;
            }

            public void setRes_open_time(String res_open_time) {
                this.res_open_time = res_open_time;
            }

            public String getRes_close_time() {
                return res_close_time;
            }

            public void setRes_close_time(String res_close_time) {
                this.res_close_time = res_close_time;
            }

            public String getRes_address() {
                return res_address;
            }

            public void setRes_address(String res_address) {
                this.res_address = res_address;
            }

            public String getRes_person_name() {
                return res_person_name;
            }

            public void setRes_person_name(String res_person_name) {
                this.res_person_name = res_person_name;
            }

            public long getRes_person_contact() {
                return res_person_contact;
            }

            public void setRes_person_contact(long res_person_contact) {
                this.res_person_contact = res_person_contact;
            }

            public String getRes_date_of_exp() {
                return res_date_of_exp;
            }

            public void setRes_date_of_exp(String res_date_of_exp) {
                this.res_date_of_exp = res_date_of_exp;
            }

            public String getRes_status() {
                return res_status;
            }

            public void setRes_status(String res_status) {
                this.res_status = res_status;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getCreate_by() {
                return create_by;
            }

            public void setCreate_by(String create_by) {
                this.create_by = create_by;
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

            public int getChef_count() {
                return chef_count;
            }

            public void setChef_count(int chef_count) {
                this.chef_count = chef_count;
            }

            public int getUser_count() {
                return user_count;
            }

            public void setUser_count(int user_count) {
                this.user_count = user_count;
            }

            public int getWaiter_count() {
                return waiter_count;
            }

            public void setWaiter_count(int waiter_count) {
                this.waiter_count = waiter_count;
            }
        }
    }
}
