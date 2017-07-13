package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/7/13.
 */

public class ManagerBean {

    /**
     * code : 3000
     * message : 查询成功
     * data : [{"phone":"15983302246","babyname":"黄小欧","oage":"24","sex":"0"}]
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
         * phone : 15983302246
         * babyname : 黄小欧
         * oage : 24
         * sex : 0
         */

        private String phone;
        private String babyname;
        private String oage;
        private String sex;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getBabyname() {
            return babyname;
        }

        public void setBabyname(String babyname) {
            this.babyname = babyname;
        }

        public String getOage() {
            return oage;
        }

        public void setOage(String oage) {
            this.oage = oage;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
