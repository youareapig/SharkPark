package com.weiye.data;

/**
 * Created by DELL on 2017/6/22.
 */

public class AllHuodongBean {

    /**
     * code : 1000
     * message : 成功
     * data : {"bjimg":"Uploads/subject/active/2017-06-22//594b7b8f4536f.jpg","isvideo":"1","content":"&lt;p&gt;哈哈&lt;/p&gt;","vurl":"http://www.baidu.com"}
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
         * bjimg : Uploads/subject/active/2017-06-22//594b7b8f4536f.jpg
         * isvideo : 1
         * content : &lt;p&gt;哈哈&lt;/p&gt;
         * vurl : http://www.baidu.com
         */

        private String bjimg;
        private String isvideo;
        private String content;
        private String vurl;

        public String getBjimg() {
            return bjimg;
        }

        public void setBjimg(String bjimg) {
            this.bjimg = bjimg;
        }

        public String getIsvideo() {
            return isvideo;
        }

        public void setIsvideo(String isvideo) {
            this.isvideo = isvideo;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getVurl() {
            return vurl;
        }

        public void setVurl(String vurl) {
            this.vurl = vurl;
        }
    }
}
