package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/11/22.
 */

public class BabyBean {

    /**
     * code : 3000
     * message : 查询成功
     * data : [{"truename":"王二麻子","id":1},{"truename":"王五","id":2},{"truename":"老三","id":3}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * truename : 王二麻子
         * id : 1
         */

        private String truename;
        private int id;

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
