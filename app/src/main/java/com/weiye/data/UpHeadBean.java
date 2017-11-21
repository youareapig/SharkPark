package com.weiye.data;

/**
 * Created by DELL on 2017/11/17.
 */

public class UpHeadBean {

    /**
     * code : 3004
     * message : 修改头像成功
     * data : {"headpic":"Uploads/user/164c914ff5f41cb1fae2bd29e3f38a5c.jpg"}
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
         * headpic : Uploads/user/164c914ff5f41cb1fae2bd29e3f38a5c.jpg
         */

        private String headpic;

        public String getHeadpic() {
            return headpic;
        }

        public void setHeadpic(String headpic) {
            this.headpic = headpic;
        }
    }
}
