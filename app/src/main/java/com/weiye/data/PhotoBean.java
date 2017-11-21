package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/5/12.
 */
public class PhotoBean {

    /**
     * code : 1000
     * message : 查询成功
     * data : [{"purl":["Uploads/subject/2017-07-20/597059d5eddc9.jpg","Uploads/subject/2017-07-20/597059d5efcd9.jpg","Uploads/subject/2017-07-20/597059d5f1c5e.jpg","Uploads/subject/2017-07-20/597059d5f3bf1.jpg","Uploads/subject/2017-07-20/597059d6018ff.jpg"],"title":"课堂风采"},{"purl":["Uploads/subject/2017-07-21/597167bc08e8e.png","Uploads/subject/2017-07-21/597167bc0b4fc.png","Uploads/subject/2017-07-21/597167bc0ffde.jpg","Uploads/subject/2017-07-21/597167bc1207d.jpg","Uploads/subject/2017-07-21/5971690ca054b.png"],"title":"小课堂"},{"purl":["Uploads/subject/2017-07-21/5971694763d49.jpg","Uploads/subject/2017-07-21/597169c9c8993.png","Uploads/subject/2017-07-21/597169c9c9b72.jpg","Uploads/subject/2017-07-21/597169c9cb6c4.png"],"title":"户外课堂"},{"purl":["Uploads/subject/2017-09-16/59bcea9db23c3.jpg","Uploads/subject/2017-09-16/59bceaaa1d8a1.jpg","Uploads/subject/2017-09-16/59bceab64f34f.jpg"],"title":"制作电路贺卡"},{"purl":["Uploads/subject/2017-09-16/59bceb0c25c81.jpg","Uploads/subject/2017-09-16/59bceb0c2998a.jpg","Uploads/subject/2017-09-16/59bceb1ff0159.jpg"],"title":"恐龙化石"}]
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
         * purl : ["Uploads/subject/2017-07-20/597059d5eddc9.jpg","Uploads/subject/2017-07-20/597059d5efcd9.jpg","Uploads/subject/2017-07-20/597059d5f1c5e.jpg","Uploads/subject/2017-07-20/597059d5f3bf1.jpg","Uploads/subject/2017-07-20/597059d6018ff.jpg"]
         * title : 课堂风采
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
