package com.weiye.data;

/**
 * Created by DELL on 2017/6/26.
 */

public class SubmitUserBean {

    /**
     * code : 3000
     * message : 成功获取信息
     * data : {"truename":"鲨鱼宝贝","age":"17","isfres":"1","tel":"13713899194","sex":"1"}
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
         * truename : 鲨鱼宝贝
         * age : 17
         * isfres : 1
         * tel : 13713899194
         * sex : 1
         */

        private String truename;
        private String age;
        private String isfres;
        private String tel;
        private String sex;

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getIsfres() {
            return isfres;
        }

        public void setIsfres(String isfres) {
            this.isfres = isfres;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
