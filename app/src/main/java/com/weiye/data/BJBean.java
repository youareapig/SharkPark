package com.weiye.data;

/**
 * Created by DELL on 2017/11/23.
 */

public class BJBean {

    /**
     * code : 3000
     * message : 获取信息成功
     * data : {"gname":"海洋班","inform":"我知道卡","pic":"Uploads/subject/151089670141.jpg"}
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
         * gname : 海洋班
         * inform : 我知道卡
         * pic : Uploads/subject/151089670141.jpg
         */

        private String gname;
        private String inform;
        private String pic;

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public String getInform() {
            return inform;
        }

        public void setInform(String inform) {
            this.inform = inform;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
