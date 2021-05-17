package com.example.ordertakingapp.response;

import java.util.List;

public class FetchWaiterListResponse {


    /**
     * Status : Success
     * Message : Waiter List
     * Data : [{"_id":"6099001377ada17c9682975e","rest_id":{"_id":"6098ff1b074e747b0fcd04b5","res_name":"Saleem RR Biriyani","log_id":"saleem@gmail.com","res_contact_no":9876543210,"res_google_link":"google.com","res_open_time":"06:00 AM","res_close_time":"10:00 PM","res_address":"Muthamil Nager ","res_person_name":"Mohammed","res_person_contact":9876543210,"res_date_of_exp":"2021-05-31","res_status":"Active","password":"12345","create_by":"Admin","updatedAt":"2021-05-10T09:38:35.556Z","createdAt":"2021-05-10T09:38:35.556Z","__v":0},"waiter_name":"Santhosh","waiter_number":9876543212,"waiter_emailid":"santhosh@gmail.com","waiter_address":"Saleem","waiter_emergency_no":9876543210,"waiter_status":"true","date_of_create":"Mon May 10 2021 15:12:43 GMT+0530 (India Standard Time)","updatedAt":"2021-05-17T06:06:27.457Z","createdAt":"2021-05-10T09:42:43.668Z","__v":0},{"_id":"6099003577ada17c9682975f","rest_id":{"_id":"6098ff1b074e747b0fcd04b5","res_name":"Saleem RR Biriyani","log_id":"saleem@gmail.com","res_contact_no":9876543210,"res_google_link":"google.com","res_open_time":"06:00 AM","res_close_time":"10:00 PM","res_address":"Muthamil Nager ","res_person_name":"Mohammed","res_person_contact":9876543210,"res_date_of_exp":"2021-05-31","res_status":"Active","password":"12345","create_by":"Admin","updatedAt":"2021-05-10T09:38:35.556Z","createdAt":"2021-05-10T09:38:35.556Z","__v":0},"waiter_name":"Dinesh","waiter_number":9876543214,"waiter_emailid":"dinesh@gmail.com","waiter_address":"Trichy","waiter_emergency_no":9876543210,"waiter_status":"true","date_of_create":"Mon May 10 2021 15:13:17 GMT+0530 (India Standard Time)","updatedAt":"2021-05-10T09:43:17.923Z","createdAt":"2021-05-10T09:43:17.923Z","__v":0}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * _id : 6099001377ada17c9682975e
     * rest_id : {"_id":"6098ff1b074e747b0fcd04b5","res_name":"Saleem RR Biriyani","log_id":"saleem@gmail.com","res_contact_no":9876543210,"res_google_link":"google.com","res_open_time":"06:00 AM","res_close_time":"10:00 PM","res_address":"Muthamil Nager ","res_person_name":"Mohammed","res_person_contact":9876543210,"res_date_of_exp":"2021-05-31","res_status":"Active","password":"12345","create_by":"Admin","updatedAt":"2021-05-10T09:38:35.556Z","createdAt":"2021-05-10T09:38:35.556Z","__v":0}
     * waiter_name : Santhosh
     * waiter_number : 9876543212
     * waiter_emailid : santhosh@gmail.com
     * waiter_address : Saleem
     * waiter_emergency_no : 9876543210
     * waiter_status : true
     * date_of_create : Mon May 10 2021 15:12:43 GMT+0530 (India Standard Time)
     * updatedAt : 2021-05-17T06:06:27.457Z
     * createdAt : 2021-05-10T09:42:43.668Z
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
         * _id : 6098ff1b074e747b0fcd04b5
         * res_name : Saleem RR Biriyani
         * log_id : saleem@gmail.com
         * res_contact_no : 9876543210
         * res_google_link : google.com
         * res_open_time : 06:00 AM
         * res_close_time : 10:00 PM
         * res_address : Muthamil Nager
         * res_person_name : Mohammed
         * res_person_contact : 9876543210
         * res_date_of_exp : 2021-05-31
         * res_status : Active
         * password : 12345
         * create_by : Admin
         * updatedAt : 2021-05-10T09:38:35.556Z
         * createdAt : 2021-05-10T09:38:35.556Z
         * __v : 0
         */

        private RestIdBean rest_id;
        private String waiter_name;
        private long waiter_number;
        private String waiter_emailid;
        private String waiter_address;
        private long waiter_emergency_no;
        private String waiter_status;
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

        public String getWaiter_name() {
            return waiter_name;
        }

        public void setWaiter_name(String waiter_name) {
            this.waiter_name = waiter_name;
        }

        public long getWaiter_number() {
            return waiter_number;
        }

        public void setWaiter_number(long waiter_number) {
            this.waiter_number = waiter_number;
        }

        public String getWaiter_emailid() {
            return waiter_emailid;
        }

        public void setWaiter_emailid(String waiter_emailid) {
            this.waiter_emailid = waiter_emailid;
        }

        public String getWaiter_address() {
            return waiter_address;
        }

        public void setWaiter_address(String waiter_address) {
            this.waiter_address = waiter_address;
        }

        public long getWaiter_emergency_no() {
            return waiter_emergency_no;
        }

        public void setWaiter_emergency_no(long waiter_emergency_no) {
            this.waiter_emergency_no = waiter_emergency_no;
        }

        public String getWaiter_status() {
            return waiter_status;
        }

        public void setWaiter_status(String waiter_status) {
            this.waiter_status = waiter_status;
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
        }
    }
}
