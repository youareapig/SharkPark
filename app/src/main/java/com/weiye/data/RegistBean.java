package com.weiye.data;

/**
 * Created by DELL on 2017/6/21.
 */

public class RegistBean {

    /**
     * code : 3002
     * message : 注册成功
     * data : {"id":"1","tel":"15983302246","headpic":"Uploads/users/default.png"}
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
         * tel : 15983302246
         * headpic : Uploads/users/default.png
         */

        private String id;
        private String tel;
        private String headpic;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getHeadpic() {
            return headpic;
        }

        public void setHeadpic(String headpic) {
            this.headpic = headpic;
        }
    }
}
