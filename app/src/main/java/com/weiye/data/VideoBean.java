package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/6/22.
 */

public class VideoBean {

    /**
     * code : 1000
     * message : 查询成功
     * data : [{"vimg":"Uploads/subject/2017-07-07//595ef83aac949.jpg","vtitle":"课堂风采","vurl":"/video/vide1.mp4"},{"vimg":"Uploads/subject/2017-07-07//595ef87982a6c.jpg","vtitle":"学生动手课堂","vurl":"/video/vide1.mp4"},{"vimg":"Uploads/subject/2017-07-07//595ef8965c239.jpg","vtitle":"雏鹰起飞","vurl":"/video/vide1.mp4"},{"vimg":"Uploads/subject/2017-07-07//595ef8bb3e375.jpg","vtitle":"初升的太阳","vurl":"/video/vide1.mp4"},{"vimg":"Uploads/subject/2017-07-07//595ef8ee94a55.jpg","vtitle":"时代在召唤","vurl":"/video/vide1.mp4"},{"vimg":"Uploads/subject/2017-07-07//595ef9026413d.jpg","vtitle":"学生风采","vurl":"/video/vide1.mp4"}]
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
         * vimg : Uploads/subject/2017-07-07//595ef83aac949.jpg
         * vtitle : 课堂风采
         * vurl : /video/vide1.mp4
         */

        private String vimg;
        private String vtitle;
        private String vurl;

        public String getVimg() {
            return vimg;
        }

        public void setVimg(String vimg) {
            this.vimg = vimg;
        }

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
    }
}
