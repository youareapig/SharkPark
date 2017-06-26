package com.weiye.data;

/**
 * Created by DELL on 2017/6/21.
 */

public class GetUserInfo {

    /**
     * code : 3000
     * message : 信息查询成功
     * data : {"nickname":null,"sex":"0","birthday":null}
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
         * nickname : null
         * sex : 0
         * birthday : null
         */

        private Object nickname;
        private String sex;
        private Object birthday;

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public Object getBirthday() {
            return birthday;
        }

        public void setBirthday(Object birthday) {
            this.birthday = birthday;
        }
    }
}
