package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/5/12.
 */
public class TeacherBean {

    /**
     * code : 1000
     * message : 成功
     * data : {"truename":"李晓晕","desc":"老师经验凤舞","pic":["Uploads/teacher/2017-06-21/594a307aa5669.jpg","Uploads/teacher/2017-06-21/594a3ed87968f.jpg"]}
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
         * truename : 李晓晕
         * desc : 老师经验凤舞
         * pic : ["Uploads/teacher/2017-06-21/594a307aa5669.jpg","Uploads/teacher/2017-06-21/594a3ed87968f.jpg"]
         */

        private String truename;
        private String desc;
        private List<String> pic;

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public List<String> getPic() {
            return pic;
        }

        public void setPic(List<String> pic) {
            this.pic = pic;
        }
    }
}
