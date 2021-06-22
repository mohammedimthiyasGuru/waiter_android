package com.waiter.ordertaking.response;

import java.util.List;

public class FetchChiefListResponse {


    /**
     * Status : Success
     * Message : Chef List
     * Data : [{"_id":"609900c677ada17c96829761","rest_id":{"_id":"6098ff1b074e747b0fcd04b5","res_name":"Saleem RR Biriyani","log_id":"saleem@gmail.com","res_contact_no":9876543210,"res_google_link":"google.com","res_open_time":"06:00 AM","res_close_time":"10:00 PM","res_address":"Muthamil Nager ","res_person_name":"Mohammed","res_person_contact":9876543210,"res_date_of_exp":"2021-05-31","res_status":"Active","password":"12345","create_by":"Admin","updatedAt":"2021-05-10T09:38:35.556Z","createdAt":"2021-05-10T09:38:35.556Z","__v":0},"chef_name":"Imthiyas","chef_number":9514497862,"chef_emailid":"imthi@gmail.com","chef_address":"Chennai","chef_emergency_no":9876543210,"chef_status":"false","date_of_create":"Mon May 10 2021 15:15:42 GMT+0530 (India Standard Time)","updatedAt":"2021-05-17T05:20:12.234Z","createdAt":"2021-05-10T09:45:42.847Z","__v":0},{"_id":"609900e577ada17c96829762","rest_id":{"_id":"6098ff1b074e747b0fcd04b5","res_name":"Saleem RR Biriyani","log_id":"saleem@gmail.com","res_contact_no":9876543210,"res_google_link":"google.com","res_open_time":"06:00 AM","res_close_time":"10:00 PM","res_address":"Muthamil Nager ","res_person_name":"Mohammed","res_person_contact":9876543210,"res_date_of_exp":"2021-05-31","res_status":"Active","password":"12345","create_by":"Admin","updatedAt":"2021-05-10T09:38:35.556Z","createdAt":"2021-05-10T09:38:35.556Z","__v":0},"chef_name":"Iqbal","chef_number":9789868296,"chef_emailid":"iqbal@gmail.com","chef_address":"Chennai","chef_emergency_no":9876543210,"chef_status":"true","date_of_create":"Mon May 10 2021 15:16:12 GMT+0530 (India Standard Time)","updatedAt":"2021-05-10T09:46:13.183Z","createdAt":"2021-05-10T09:46:13.183Z","__v":0}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * _id : 609900c677ada17c96829761
     * rest_id : {"_id":"6098ff1b074e747b0fcd04b5","res_name":"Saleem RR Biriyani","log_id":"saleem@gmail.com","res_contact_no":9876543210,"res_google_link":"google.com","res_open_time":"06:00 AM","res_close_time":"10:00 PM","res_address":"Muthamil Nager ","res_person_name":"Mohammed","res_person_contact":9876543210,"res_date_of_exp":"2021-05-31","res_status":"Active","password":"12345","create_by":"Admin","updatedAt":"2021-05-10T09:38:35.556Z","createdAt":"2021-05-10T09:38:35.556Z","__v":0}
     * chef_name : Imthiyas
     * chef_number : 9514497862
     * chef_emailid : imthi@gmail.com
     * chef_address : Chennai
     * chef_emergency_no : 9876543210
     * chef_status : false
     * date_of_create : Mon May 10 2021 15:15:42 GMT+0530 (India Standard Time)
     * updatedAt : 2021-05-17T05:20:12.234Z
     * createdAt : 2021-05-10T09:45:42.847Z
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
        private String chef_name;
        private long chef_number;
        private String chef_emailid;
        private String chef_address;
        private long chef_emergency_no;
        private String chef_status;
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

        public String getChef_name() {
            return chef_name;
        }

        public void setChef_name(String chef_name) {
            this.chef_name = chef_name;
        }

        public long getChef_number() {
            return chef_number;
        }

        public void setChef_number(long chef_number) {
            this.chef_number = chef_number;
        }

        public String getChef_emailid() {
            return chef_emailid;
        }

        public void setChef_emailid(String chef_emailid) {
            this.chef_emailid = chef_emailid;
        }

        public String getChef_address() {
            return chef_address;
        }

        public void setChef_address(String chef_address) {
            this.chef_address = chef_address;
        }

        public long getChef_emergency_no() {
            return chef_emergency_no;
        }

        public void setChef_emergency_no(long chef_emergency_no) {
            this.chef_emergency_no = chef_emergency_no;
        }

        public String getChef_status() {
            return chef_status;
        }

        public void setChef_status(String chef_status) {
            this.chef_status = chef_status;
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
