package com.weiye.data;


import java.util.List;

/**
 * Created by DELL on 2017/7/12.
 */

public class WeishangBean {

    /**
     * code : 3000
     * message : 获取课程成功
     * data : [{"dates":"2017-11-23","week":"周四","datename":"10:30-11:30","coname":"小学","id":4},{"dates":"2017-11-24","week":"周五","datename":"10:30-11:30","coname":"初中","id":5},{"dates":"2017-11-25","week":"周六","datename":"16:15-17:45","coname":"幼儿园","id":6},{"dates":"2017-11-26","week":"周日","datename":"16:15-17:45","coname":"研究生","id":7},{"dates":"2017-11-27","week":"周一","datename":"16:15-17:45","coname":"你猜","id":8}]
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
         * dates : 2017-11-23
         * week : 周四
         * datename : 10:30-11:30
         * coname : 小学
         * id : 4
         */

        private String dates;
        private String week;
        private String datename;
        private String coname;
        private int id;

        public String getDates() {
            return dates;
        }

        public void setDates(String dates) {
            this.dates = dates;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getDatename() {
            return datename;
        }

        public void setDatename(String datename) {
            this.datename = datename;
        }

        public String getConame() {
            return coname;
        }

        public void setConame(String coname) {
            this.coname = coname;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
