package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/7/12.
 */

public class YichangBean {

    /**
     * code : 3000
     * message : 获取课程成功
     * data : [{"dates":"2017-11-23","week":"周四","coname":"小学","datename":"10:30-11:30","info":"旷课"}]
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
         * coname : 小学
         * datename : 10:30-11:30
         * info : 旷课
         */

        private String dates;
        private String week;
        private String coname;
        private String datename;
        private String info;

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

        public String getConame() {
            return coname;
        }

        public void setConame(String coname) {
            this.coname = coname;
        }

        public String getDatename() {
            return datename;
        }

        public void setDatename(String datename) {
            this.datename = datename;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }
}
