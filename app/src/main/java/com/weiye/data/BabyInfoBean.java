package com.weiye.data;

/**
 * Created by DELL on 2017/11/22.
 */

public class BabyInfoBean {

    /**
     * code : 3000
     * message : 获取信息成功
     * data : {"id":1,"truename":"王二麻子","sex":0,"sbid":1,"birthday":"2014-11-17","location":1,"gid":1,"gname":"海洋班"}
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
         * id : 1
         * truename : 王二麻子
         * sex : 0
         * sbid : 1
         * birthday : 2014-11-17
         * location : 1
         * gid : 1
         * gname : 海洋班
         */

        private int id;
        private String truename;
        private int sex;
        private int sbid;
        private String birthday;
        private int location;
        private int gid;
        private String gname;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getSbid() {
            return sbid;
        }

        public void setSbid(int sbid) {
            this.sbid = sbid;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public int getLocation() {
            return location;
        }

        public void setLocation(int location) {
            this.location = location;
        }

        public int getGid() {
            return gid;
        }

        public void setGid(int gid) {
            this.gid = gid;
        }

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }
    }
}
