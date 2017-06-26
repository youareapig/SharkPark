package com.weiye.data;

/**
 * Created by DELL on 2017/6/26.
 */

public class SubmitUserBean {

    /**
     * code : 3000
     * message : 成功
     * data : {"truename":null,"age":"28","sex":"0","telnumber":"15983302246"}
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
         * truename : null
         * age : 28
         * sex : 0
         * telnumber : 15983302246
         */

        private Object truename;
        private String age;
        private String sex;
        private String telnumber;

        public Object getTruename() {
            return truename;
        }

        public void setTruename(Object truename) {
            this.truename = truename;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getTelnumber() {
            return telnumber;
        }

        public void setTelnumber(String telnumber) {
            this.telnumber = telnumber;
        }
    }
}
