package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/7/10.
 */

public class Park_1Bean {

    /**
     * code : 1000
     * message : 获取数据成功
     * data : [{"id":2,"uname":"图图老师","headpic":"Uploads/manager/151064738323.png"},{"id":3,"uname":"Gotti老师","headpic":"Uploads/manager/151064747063.jpg"},{"id":4,"uname":"莹子老师","headpic":"Uploads/manager/151064754599.jpg"},{"id":5,"uname":"泡泡老师","headpic":"Uploads/manager/151064760720.jpg"},{"id":6,"uname":"云云老师","headpic":"Uploads/manager/151064770896.jpg"}]
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
         * id : 2
         * uname : 图图老师
         * headpic : Uploads/manager/151064738323.png
         */

        private int id;
        private String uname;
        private String headpic;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getHeadpic() {
            return headpic;
        }

        public void setHeadpic(String headpic) {
            this.headpic = headpic;
        }
    }
}
