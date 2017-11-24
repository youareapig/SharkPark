package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/7/13.
 */

public class VipClassPhotoBean {

    /**
     * code : 3000
     * message : 查询成功
     * data : [{"purl":["Uploads/subject/fe25131ca13e2a5929631959126eff4a.jpg","Uploads/subject/8775ab25d8399ee3f1bed3b9608feaf9.jpg"],"title":"海洋测试"},{"purl":["Uploads/subject/2017-08-10/598c2d4282919.jpg"],"title":"123"}]
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
         * purl : ["Uploads/subject/fe25131ca13e2a5929631959126eff4a.jpg","Uploads/subject/8775ab25d8399ee3f1bed3b9608feaf9.jpg"]
         * title : 海洋测试
         */

        private String title;
        private List<String> purl;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getPurl() {
            return purl;
        }

        public void setPurl(List<String> purl) {
            this.purl = purl;
        }
    }
}
