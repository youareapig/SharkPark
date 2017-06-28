package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/6/6.
 */
public class KCBBean {

    /**
     * code : 1000
     * message : 成功
     * data : [{"cid":"5","dates":"2017-06-28","week":"周三","pic":"/Public/week/w3.png","children":[{"cid":"6","datename":"10:30-12:30","course":[{"coid":"6","coname":"历史","uids":"","coage":"大四","ischeckd":0},{"coid":"7","coname":"针织","uids":"","coage":"大三","ischeckd":0},{"coid":"8","coname":"转入","uids":null,"coage":"大雾","ischeckd":0}]},{"cid":"7","datename":"11:30-1:30","course":[{"coid":"9","coname":"你猜","uids":null,"coage":"","ischeckd":0}]},{"cid":"8","datename":"2:30-2:30","course":[{"coid":"10","coname":"语文和历史","uids":"","coage":"","ischeckd":0}]}]},{"cid":"9","dates":"2017-06-29","week":"周四","pic":"/Public/week/w4.png","children":[{"cid":"10","datename":"10:30-12:30","course":[{"coid":"11","coname":"数学","uids":"","coage":"","ischeckd":0}]},{"cid":"11","datename":"1:20-2:30","course":[{"coid":"12","coname":"语文","uids":"","coage":"","ischeckd":0}]}]},{"cid":"12","dates":"2017-06-30","week":"周五","pic":"/Public/week/w5.png","children":[{"cid":"2","datename":"5:30-8:30","course":[{"coid":"1","coname":"数学","uids":null,"coage":"","ischeckd":0},{"coid":"2","coname":"语文","uids":"","coage":"","ischeckd":0}]},{"cid":"13","datename":"9:30-10:40","course":[]},{"cid":"14","datename":"11:30-1:30","course":[]}]}]
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
         * cid : 5
         * dates : 2017-06-28
         * week : 周三
         * pic : /Public/week/w3.png
         * children : [{"cid":"6","datename":"10:30-12:30","course":[{"coid":"6","coname":"历史","uids":"","coage":"大四","ischeckd":0},{"coid":"7","coname":"针织","uids":"","coage":"大三","ischeckd":0},{"coid":"8","coname":"转入","uids":null,"coage":"大雾","ischeckd":0}]},{"cid":"7","datename":"11:30-1:30","course":[{"coid":"9","coname":"你猜","uids":null,"coage":"","ischeckd":0}]},{"cid":"8","datename":"2:30-2:30","course":[{"coid":"10","coname":"语文和历史","uids":"","coage":"","ischeckd":0}]}]
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
             * cid : 6
             * datename : 10:30-12:30
             * course : [{"coid":"6","coname":"历史","uids":"","coage":"大四","ischeckd":0},{"coid":"7","coname":"针织","uids":"","coage":"大三","ischeckd":0},{"coid":"8","coname":"转入","uids":null,"coage":"大雾","ischeckd":0}]
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
                 * coid : 6
                 * coname : 历史
                 * uids :
                 * coage : 大四
                 * ischeckd : 0
                 */

                private String coid;
                private String coname;
                private String uids;
                private String coage;
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

                public String getUids() {
                    return uids;
                }

                public void setUids(String uids) {
                    this.uids = uids;
                }

                public String getCoage() {
                    return coage;
                }

                public void setCoage(String coage) {
                    this.coage = coage;
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
