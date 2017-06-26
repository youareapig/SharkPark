package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/6/22.
 */

public class VideoBean {

    /**
     * code : 1000
     * message : 查询成功
     * data : [{"vtitle":"视频来吧","vurl":"Video/vide1.mp4","vimg":"Uploads/subject/2017-06-22//594b322e5e045.jpg"}]
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
         * vtitle : 视频来吧
         * vurl : Video/vide1.mp4
         * vimg : Uploads/subject/2017-06-22//594b322e5e045.jpg
         */

        private String vtitle;
        private String vurl;
        private String vimg;

        public String getVtitle() {
            return vtitle;
        }

        public void setVtitle(String vtitle) {
            this.vtitle = vtitle;
        }

        public String getVurl() {
            return vurl;
        }

        public void setVurl(String vurl) {
            this.vurl = vurl;
        }

        public String getVimg() {
            return vimg;
        }

        public void setVimg(String vimg) {
            this.vimg = vimg;
        }
    }
}
