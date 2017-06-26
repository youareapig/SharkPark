package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/6/21.
 */

public class SubjectBean {

    /**
     * code : 1000
     * message : 成功
     * data : {"sbid":"2","sbtitle":"物质与科学","sbdesc":"物质与科学物质与科学物质与科学物质与科学物质与科学","bjpic":null,"teacher":[{"tid":"1","headpic":"Uploads/teacher/2017-06-21//594a307aa3ba9.jpg","nickname":"张三老师"}]}
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
         * sbid : 2
         * sbtitle : 物质与科学
         * sbdesc : 物质与科学物质与科学物质与科学物质与科学物质与科学
         * bjpic : null
         * teacher : [{"tid":"1","headpic":"Uploads/teacher/2017-06-21//594a307aa3ba9.jpg","nickname":"张三老师"}]
         */

        private String sbid;
        private String sbtitle;
        private String sbdesc;
        private Object bjpic;
        private List<TeacherBean> teacher;

        public String getSbid() {
            return sbid;
        }

        public void setSbid(String sbid) {
            this.sbid = sbid;
        }

        public String getSbtitle() {
            return sbtitle;
        }

        public void setSbtitle(String sbtitle) {
            this.sbtitle = sbtitle;
        }

        public String getSbdesc() {
            return sbdesc;
        }

        public void setSbdesc(String sbdesc) {
            this.sbdesc = sbdesc;
        }

        public Object getBjpic() {
            return bjpic;
        }

        public void setBjpic(Object bjpic) {
            this.bjpic = bjpic;
        }

        public List<TeacherBean> getTeacher() {
            return teacher;
        }

        public void setTeacher(List<TeacherBean> teacher) {
            this.teacher = teacher;
        }

        public static class TeacherBean {
            /**
             * tid : 1
             * headpic : Uploads/teacher/2017-06-21//594a307aa3ba9.jpg
             * nickname : 张三老师
             */

            private String tid;
            private String headpic;
            private String nickname;

            public String getTid() {
                return tid;
            }

            public void setTid(String tid) {
                this.tid = tid;
            }

            public String getHeadpic() {
                return headpic;
            }

            public void setHeadpic(String headpic) {
                this.headpic = headpic;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }
        }
    }
}
