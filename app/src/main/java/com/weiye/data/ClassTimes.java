package com.weiye.data;

/**
 * Created by DELL on 2017/11/23.
 */

public class ClassTimes {

    /**
     * code : 3000
     * message : 获取信息成功
     * data : {"totalnum":80,"enablenum":72,"withdrawed":8}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * totalnum : 80
         * enablenum : 72
         * withdrawed : 8
         */

        private int totalnum;
        private int enablenum;
        private int withdrawed;

        public int getTotalnum() {
            return totalnum;
        }

        public void setTotalnum(int totalnum) {
            this.totalnum = totalnum;
        }

        public int getEnablenum() {
            return enablenum;
        }

        public void setEnablenum(int enablenum) {
            this.enablenum = enablenum;
        }

        public int getWithdrawed() {
            return withdrawed;
        }

        public void setWithdrawed(int withdrawed) {
            this.withdrawed = withdrawed;
        }
    }
}
