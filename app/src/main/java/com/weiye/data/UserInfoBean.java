package com.weiye.data;

/**
 * Created by DELL on 2017/5/10.
 */
public class UserInfoBean {

    /**
     * code : 3000
     * message : 查询成功
     * data : {"tel":"15983302246","headpic":"Uploads/users/default.png"}
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
         * tel : 15983302246
         * headpic : Uploads/users/default.png
         */

        private String tel;
        private String headpic;

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
