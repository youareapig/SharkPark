package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/6/22.
 */

public class VideoBean {

    /**
     * code : 1000
     * message : 查询成功
     * data : [{"vimg":"Uploads/subject/2017-08-10//598c109a6c4dc.jpg","title":"科普小讲堂","vurl":"/Video/a1.flv"},{"vimg":"Uploads/subject/2017-08-10//598c130112ac0.jpg","title":"3D眼镜制作","vurl":"/Video/a2.mp4"},{"vimg":"Uploads/subject/2017-09-16//59bcea2755f15.jpg","title":"大象牙膏","vurl":"/Video/daxiangyagao"},{"vimg":"Uploads/subject/2017-09-16//59bcf40a1693e.jpg","title":"爆沸反应","vurl":"/Video/baofeifanying"},{"vimg":"Uploads/subject/151064042089.jpg","title":"测试添加","vurl":"/Video/tt4"}]
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
         * vimg : Uploads/subject/2017-08-10//598c109a6c4dc.jpg
         * title : 科普小讲堂
         * vurl : /Video/a1.flv
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
