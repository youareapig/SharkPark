package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/6/21.
 */

public class VipClassVidioBean {

    /**
     * code : 3000
     * message : 查询成功
     * data : [{"vimg":"Uploads/subject/15112308504.jpg","title":"海洋测试视频","vurl":"Video/a1.mp4"}]
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
         * vimg : Uploads/subject/15112308504.jpg
         * title : 海洋测试视频
         * vurl : Video/a1.mp4
         */

        private String vimg;
        private String title;
        private String vurl;

        public String getVimg() {
            return vimg;
        }

        public void setVimg(String vimg) {
            this.vimg = vimg;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getVurl() {
            return vurl;
        }

        public void setVurl(String vurl) {
            this.vurl = vurl;
        }
    }
}
