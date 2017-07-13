package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/7/13.
 */

public class TeacherManageVideoBean {

    /**
     * code : 3000
     * message : 成功
     * data : {"grade":[{"gname":"章鱼班","gid":"4","inform":"章鱼班","gpic":"Uploads/subject/class/2017-07-10//5962e5a5c1c9c.jpg"},{"gname":"海豚班","gid":"7","inform":"海豚班","gpic":"Uploads/subject/class/2017-07-10//5962e622bcbb8.jpg"}],"pv":[{"vimg":"Uploads/subject/2017-07-13//5966d7c0e50d8.jpg","vtitle":"21212121","vurl":"2132132131"}]}
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
        private List<GradeBean> grade;
        private List<PvBean> pv;

        public List<GradeBean> getGrade() {
            return grade;
        }

        public void setGrade(List<GradeBean> grade) {
            this.grade = grade;
        }

        public List<PvBean> getPv() {
            return pv;
        }

        public void setPv(List<PvBean> pv) {
            this.pv = pv;
        }

        public static class GradeBean {
            /**
             * gname : 章鱼班
             * gid : 4
             * inform : 章鱼班
             * gpic : Uploads/subject/class/2017-07-10//5962e5a5c1c9c.jpg
             */

            private String gname;
            private String gid;
            private String inform;
            private String gpic;

            public String getGname() {
                return gname;
            }

            public void setGname(String gname) {
                this.gname = gname;
            }

            public String getGid() {
                return gid;
            }

            public void setGid(String gid) {
                this.gid = gid;
            }

            public String getInform() {
                return inform;
            }

            public void setInform(String inform) {
                this.inform = inform;
            }

            public String getGpic() {
                return gpic;
            }

            public void setGpic(String gpic) {
                this.gpic = gpic;
            }
        }

        public static class PvBean {
            /**
             * vimg : Uploads/subject/2017-07-13//5966d7c0e50d8.jpg
             * vtitle : 21212121
             * vurl : 2132132131
             */

            private String vimg;
            private String vtitle;
            private String vurl;

            public String getVimg() {
                return vimg;
            }

            public void setVimg(String vimg) {
                this.vimg = vimg;
            }

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
        }
    }
}
