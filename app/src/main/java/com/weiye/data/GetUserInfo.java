package com.weiye.data;

/**
 * Created by DELL on 2017/6/21.
 */

public class GetUserInfo {

    /**
     * code : 3000
     * message : 信息查询成功
     * data : {"nickname":null,"sex":null,"tel":"15983302246","birthday":null}
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
         * sex : null
         * tel : 15983302246
         * birthday : null
         */

        private Object nickname;
        private Object sex;
        private String tel;
        private Object birthday;

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
            this.sex = sex;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public Object getBirthday() {
            return birthday;
        }

        public void setBirthday(Object birthday) {
            this.birthday = birthday;
        }
    }
}
