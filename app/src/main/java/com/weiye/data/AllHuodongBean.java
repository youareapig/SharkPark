package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/6/22.
 */

public class AllHuodongBean {

    /**
     * code : 1000
     * message : 查询成功
     * data : [{"title":"每月总结","bjimg":"Uploads/subject/active/2017-07-06//595e07cf77949.jpg","iswei":"0","content":"<p>每月总结<\/p>","weurl":""},{"title":"感恩---教师节","bjimg":"Uploads/subject/active/2017-07-06//595e090983dc5.jpg","iswei":"0","content":"<p>9月10日是教师节！<\/p>","weurl":""},{"title":"欢庆国庆","bjimg":"Uploads/subject/active/2017-07-06//595e0927d2363.jpg","iswei":"0","content":"<p>10月1号是国庆节<\/p>","weurl":""},{"title":"实验检测","bjimg":"Uploads/subject/active/2017-07-07//595ef3726a15e.jpg","iswei":"0","content":"<p>课堂实验检测<\/p>","weurl":""},{"title":"方法","bjimg":"Uploads/subject/active/2017-07-07//595ef3855c514.jpg","iswei":"0","content":"<p>课堂方法展示<\/p>","weurl":""}]
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
         * title : 每月总结
         * bjimg : Uploads/subject/active/2017-07-06//595e07cf77949.jpg
         * iswei : 0
         * content : <p>每月总结</p>
         * weurl :
         */

        private String title;
        private String bjimg;
        private String iswei;
        private String content;
        private String weurl;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBjimg() {
            return bjimg;
        }

        public void setBjimg(String bjimg) {
            this.bjimg = bjimg;
        }

        public String getIswei() {
            return iswei;
        }

        public void setIswei(String iswei) {
            this.iswei = iswei;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getWeurl() {
            return weurl;
        }

        public void setWeurl(String weurl) {
            this.weurl = weurl;
        }
    }
}
