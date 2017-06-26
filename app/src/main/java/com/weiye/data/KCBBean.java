package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/6/6.
 */
public class KCBBean {

    /**
     * code : 1000
     * message : 成功
     * data : [{"cid":"1","dates":"2017-06-26","week":"周一","pic":"/Public/week/w1.png","children":[{"cid":"2","datename":"9:30-10:30","course":[{"coid":"1","coname":"植物大战僵尸","uids":null,"ischeckd":0},{"coid":"2","coname":"英语","uids":null,"ischeckd":0}]},{"cid":"3","datename":"11：00-12:30","course":[{"coid":"3","coname":"语文","uids":null,"ischeckd":0},{"coid":"4","coname":"数学","uids":null,"ischeckd":0}]},{"cid":"4","datename":"2:30-5:30","course":[{"coid":"5","coname":"体育","uids":null,"ischeckd":0}]}]}]
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
         * cid : 1
         * dates : 2017-06-26
         * week : 周一
         * pic : /Public/week/w1.png
         * children : [{"cid":"2","datename":"9:30-10:30","course":[{"coid":"1","coname":"植物大战僵尸","uids":null,"ischeckd":0},{"coid":"2","coname":"英语","uids":null,"ischeckd":0}]},{"cid":"3","datename":"11：00-12:30","course":[{"coid":"3","coname":"语文","uids":null,"ischeckd":0},{"coid":"4","coname":"数学","uids":null,"ischeckd":0}]},{"cid":"4","datename":"2:30-5:30","course":[{"coid":"5","coname":"体育","uids":null,"ischeckd":0}]}]
         */

        private String cid;
        private String dates;
        private String week;
        private String pic;
        private List<ChildrenBean> children;

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getDates() {
            return dates;
        }

        public void setDates(String dates) {
            this.dates = dates;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean {
            /**
             * cid : 2
             * datename : 9:30-10:30
             * course : [{"coid":"1","coname":"植物大战僵尸","uids":null,"ischeckd":0},{"coid":"2","coname":"英语","uids":null,"ischeckd":0}]
             */

            private String cid;
            private String datename;
            private List<CourseBean> course;

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getDatename() {
                return datename;
            }

            public void setDatename(String datename) {
                this.datename = datename;
            }

            public List<CourseBean> getCourse() {
                return course;
            }

            public void setCourse(List<CourseBean> course) {
                this.course = course;
            }

            public static class CourseBean {
                /**
                 * coid : 1
                 * coname : 植物大战僵尸
                 * uids : null
                 * ischeckd : 0
                 */

                private String coid;
                private String coname;
                private Object uids;
                private int ischeckd;

                public String getCoid() {
                    return coid;
                }

                public void setCoid(String coid) {
                    this.coid = coid;
                }

                public String getConame() {
                    return coname;
                }

                public void setConame(String coname) {
                    this.coname = coname;
                }

                public Object getUids() {
                    return uids;
                }

                public void setUids(Object uids) {
                    this.uids = uids;
                }

                public int getIscheckd() {
                    return ischeckd;
                }

                public void setIscheckd(int ischeckd) {
                    this.ischeckd = ischeckd;
                }
            }
        }
    }
}
