package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/5/12.
 */
public class HuodongBean {

    /**
     * code : 1000
     * message : 成功
     * data : [{"id":"1","title":"5月1号活动","isvideo":"0","bjimg":"Uploads/subject/active/2017-06-22//594b7b6613507.jpg"},{"id":"2","title":"6月1号活动能够","isvideo":"1","bjimg":"Uploads/subject/active/2017-06-22//594b7b8f4536f.jpg"}]
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
         * id : 1
         * title : 5月1号活动
         * isvideo : 0
         * bjimg : Uploads/subject/active/2017-06-22//594b7b6613507.jpg
         */

        private String id;
        private String title;
        private String isvideo;
        private String bjimg;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIsvideo() {
            return isvideo;
        }

        public void setIsvideo(String isvideo) {
            this.isvideo = isvideo;
        }

        public String getBjimg() {
            return bjimg;
        }

        public void setBjimg(String bjimg) {
            this.bjimg = bjimg;
        }
    }
}
