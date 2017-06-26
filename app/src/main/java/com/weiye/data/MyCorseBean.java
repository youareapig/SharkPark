package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/6/26.
 */

public class MyCorseBean {

    /**
     * code : 3000
     * message : 信息查询成功
     * data : [{"coid":"1","carid":"1","coname":"植物大战僵尸22","week":"周一","datename":"9:30-10:30","dates":"2017-06-26"},{"coid":"6","carid":"2","coname":"心理将康教育","week":"周一","datename":"9:30-10:30","dates":"2017-06-26"}]
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
         * coid : 1
         * carid : 1
         * coname : 植物大战僵尸22
         * week : 周一
         * datename : 9:30-10:30
         * dates : 2017-06-26
         */

        private String coid;
        private String carid;
        private String coname;
        private String week;
        private String datename;
        private String dates;

        public String getCoid() {
            return coid;
        }

        public void setCoid(String coid) {
            this.coid = coid;
        }

        public String getCarid() {
            return carid;
        }

        public void setCarid(String carid) {
            this.carid = carid;
        }

        public String getConame() {
            return coname;
        }

        public void setConame(String coname) {
            this.coname = coname;
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

        public String getDates() {
            return dates;
        }

        public void setDates(String dates) {
            this.dates = dates;
        }
    }
}
