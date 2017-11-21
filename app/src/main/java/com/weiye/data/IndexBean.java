package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/5/9.
 */
public class IndexBean {

    /**
     * code : 1000
     * message : 获取数据成功
     * data : [{"title":"大一课程表","id":1,"pic":"Uploads/subject/2017-07-19//596ebee97800f.jpg"},{"title":"大二课程表","id":2,"pic":"Uploads/subject/2017-07-19//596ebf09eb57e.jpg"},{"title":"大三课程表","id":3,"pic":"Uploads/subject/2017-07-19//596ebf13b0ac5.jpg"},{"title":"测试","id":4,"pic":"Uploads/subject/151064936569.jpg"}]
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
         * title : 大一课程表
         * id : 1
         * pic : Uploads/subject/2017-07-19//596ebee97800f.jpg
         */

        private String title;
        private int id;
        private String pic;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
