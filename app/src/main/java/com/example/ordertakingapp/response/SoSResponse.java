package com.example.ordertakingapp.response;

import java.util.List;

public class SoSResponse {

    /**
     * Status : Success
     * Message : SOS Details
     * Data : [{"Name":"Mohammed1","Number":9876543210},{"Name":"Mohammed5","Number":9876543212},{"Name":"Mohammed5","Number":9876543213},{"Name":"Mohammed4","Number":9876543214}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * Name : Mohammed1
     * Number : 9876543210
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
        private String Name;
        private long Number;

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public long getNumber() {
            return Number;
        }

        public void setNumber(long Number) {
            this.Number = Number;
        }
    }
}
