package com.waiter.ordertaking.response;

import java.util.List;

public class DropDownCatListResponse {

    /**
     * Status : Success
     * Message : dropdown list Details
     * Data : [{"title":"Title 1"},{"title":"Title 2"},{"title":"Title 3"}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * title : Title 1
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
        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
