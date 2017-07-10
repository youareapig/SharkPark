package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/7/10.
 */

public class Park_1Bean {

    /**
     * code : 1000
     * message : 查询成功
     * data : {"teacher":[{"tid":"1","headpic":"Uploads/teacher/2017-07-05//595c5b66d2598.png","nickname":"小二"},{"tid":"2","headpic":"Uploads/teacher/2017-07-05//595c5acc74d66.jpg","nickname":"四大爷"},{"tid":"3","headpic":"Uploads/teacher/2017-07-07//595eff9ba1af8.jpg","nickname":"王麻子"},{"tid":"4","headpic":"Uploads/teacher/2017-07-07//595f0041e2df3.jpg","nickname":"赵五"},{"tid":"5","headpic":"Uploads/teacher/2017-07-07//595f010f39ead.jpg","nickname":"小六"},{"tid":"6","headpic":"Uploads/teacher/2017-07-07//595f012df27b1.jpg","nickname":"老七"}],"video":[{"vtitle":"课堂风采","vurl":"/video/vide1.mp4","vimg":"Uploads/subject/2017-07-07//595ef83aac949.jpg"},{"vtitle":"学生动手课堂","vurl":"/video/vide1.mp4","vimg":"Uploads/subject/2017-07-07//595ef87982a6c.jpg"},{"vtitle":"雏鹰起飞","vurl":"/video/vide1.mp4","vimg":"Uploads/subject/2017-07-07//595ef8965c239.jpg"},{"vtitle":"初升的太阳","vurl":"/video/vide1.mp4","vimg":"Uploads/subject/2017-07-07//595ef8bb3e375.jpg"},{"vtitle":"时代在召唤","vurl":"/video/vide1.mp4","vimg":"Uploads/subject/2017-07-07//595ef8ee94a55.jpg"},{"vtitle":"学生风采","vurl":"/video/vide1.mp4","vimg":"Uploads/subject/2017-07-07//595ef9026413d.jpg"}]}
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
        private List<TeacherBean> teacher;
        private List<VideoBean> video;

        public List<TeacherBean> getTeacher() {
            return teacher;
        }

        public void setTeacher(List<TeacherBean> teacher) {
            this.teacher = teacher;
        }

        public List<VideoBean> getVideo() {
            return video;
        }

        public void setVideo(List<VideoBean> video) {
            this.video = video;
        }

        public static class TeacherBean {
            /**
             * tid : 1
             * headpic : Uploads/teacher/2017-07-05//595c5b66d2598.png
             * nickname : 小二
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

        public static class VideoBean {
            /**
             * vtitle : 课堂风采
             * vurl : /video/vide1.mp4
             * vimg : Uploads/subject/2017-07-07//595ef83aac949.jpg
             */

            private String vtitle;
            private String vurl;
            private String vimg;

            public String getVtitle() {
                return vtitle;
            }

            public void setVtitle(String vtitle) {
                this.vtitle = vtitle;
            }

            public String getVurl() {
                return vurl;
            }

            public void setVurl(String vurl) {
                this.vurl = vurl;
            }

            public String getVimg() {
                return vimg;
            }

            public void setVimg(String vimg) {
                this.vimg = vimg;
            }
        }
    }
}
