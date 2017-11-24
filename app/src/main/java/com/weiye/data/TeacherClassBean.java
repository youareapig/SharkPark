package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/11/23.
 */

public class TeacherClassBean {

    /**
     * code : 3000
     * message : 获取信息成功
     * data : [{"id":1,"gname":"海洋班"}]
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
         * id : 1
         * gname : 海洋班
         */

        private int id;
        private String gname;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }
    }
}
