package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/5/12.
 */
public class XYFCBean {

    /**
     * code : 1000
     * message : 成功
     * data : [{"title":"校园风采","imgurl":["Uploads/subject/2017-06-22/594b8503d593a.jpg","Uploads/subject/2017-06-22/594b8503d7d29.jpg","Uploads/subject/2017-06-22/594b8503d9267.jpg"]},{"title":"教学环境","imgurl":["Uploads/subject/2017-06-22/594b85396dc4c.png","Uploads/subject/2017-06-22/594b85396e3ee.jpg","Uploads/subject/2017-06-22/594b85396f65c.jpg"]}]
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
         * title : 校园风采
         * imgurl : ["Uploads/subject/2017-06-22/594b8503d593a.jpg","Uploads/subject/2017-06-22/594b8503d7d29.jpg","Uploads/subject/2017-06-22/594b8503d9267.jpg"]
         */

        private String title;
        private List<String> imgurl;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImgurl() {
            return imgurl;
        }

        public void setImgurl(List<String> imgurl) {
            this.imgurl = imgurl;
        }
    }
}
