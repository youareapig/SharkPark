package com.weiye.data;

/**
 * Created by DELL on 2017/5/5.
 */
public class LoginBean {

    /**
     * code : 3000
     * message : 登录成功
     * data : {"id":"2","tel":"15983302246","headpic":"Uploads/users/default.png","isfres":"1","utype":"3"}
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
         * id : 2
         * tel : 15983302246
         * headpic : Uploads/users/default.png
         * isfres : 1
         * utype : 3
         */

        private String id;
        private String tel;
        private String headpic;
        private String isfres;
        private String utype;

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

        public String getIsfres() {
            return isfres;
        }

        public void setIsfres(String isfres) {
            this.isfres = isfres;
        }

        public String getUtype() {
            return utype;
        }

        public void setUtype(String utype) {
            this.utype = utype;
        }
    }
}
