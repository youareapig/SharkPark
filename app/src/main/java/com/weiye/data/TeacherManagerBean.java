package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/7/13.
 */

public class TeacherManagerBean {

    /**
     * code : 3000
     * message : 成功
     * data : {"grade":[{"gname":"章鱼班","gid":"4","inform":"章鱼班","gpic":"Uploads/subject/class/2017-07-10//5962e5a5c1c9c.jpg"},{"gname":"海豚班","gid":"7","inform":"海豚班","gpic":"Uploads/subject/class/2017-07-10//5962e622bcbb8.jpg"}],"pv":[{"purl":["Uploads/subject/2017-07-13/5966d82ad0fc3.jpg","Uploads/subject/2017-07-13/5966f899f23f5.jpg"],"ptitle":"313131313"}]}
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
             * purl : ["Uploads/subject/2017-07-13/5966d82ad0fc3.jpg","Uploads/subject/2017-07-13/5966f899f23f5.jpg"]
             * ptitle : 313131313
             */

            private String ptitle;
            private List<String> purl;

            public String getPtitle() {
                return ptitle;
            }

            public void setPtitle(String ptitle) {
                this.ptitle = ptitle;
            }

            public List<String> getPurl() {
                return purl;
            }

            public void setPurl(List<String> purl) {
                this.purl = purl;
            }
        }
    }
}
