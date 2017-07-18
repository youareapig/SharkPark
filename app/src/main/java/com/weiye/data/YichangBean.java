package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/7/12.
 */

public class YichangBean {

    /**
     * code : 3000
     * message : 成功
     * data : {"nums":{"totalnum":"50","enablenum":"48","withdrawed":"2"},"course":[{"dates":"2017-07-12","week":"周三","coname":"英语","datename":"09:30-10:30","info":"正常上课"}]}
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
         * nums : {"totalnum":"50","enablenum":"48","withdrawed":"2"}
         * course : [{"dates":"2017-07-12","week":"周三","coname":"英语","datename":"09:30-10:30","info":"正常上课"}]
         */

        private NumsBean nums;
        private List<CourseBean> course;

        public NumsBean getNums() {
            return nums;
        }

        public void setNums(NumsBean nums) {
            this.nums = nums;
        }

        public List<CourseBean> getCourse() {
            return course;
        }

        public void setCourse(List<CourseBean> course) {
            this.course = course;
        }

        public static class NumsBean {
            /**
             * totalnum : 50
             * enablenum : 48
             * withdrawed : 2
             */

            private String totalnum;
            private String enablenum;
            private String withdrawed;

            public String getTotalnum() {
                return totalnum;
            }

            public void setTotalnum(String totalnum) {
                this.totalnum = totalnum;
            }

            public String getEnablenum() {
                return enablenum;
            }

            public void setEnablenum(String enablenum) {
                this.enablenum = enablenum;
            }

            public String getWithdrawed() {
                return withdrawed;
            }

            public void setWithdrawed(String withdrawed) {
                this.withdrawed = withdrawed;
            }
        }

        public static class CourseBean {
            /**
             * dates : 2017-07-12
             * week : 周三
             * coname : 英语
             * datename : 09:30-10:30
             * info : 正常上课
             */

            private String dates;
            private String week;
            private String coname;
            private String datename;
            private String info;

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

            public String getConame() {
                return coname;
            }

            public void setConame(String coname) {
                this.coname = coname;
            }

            public String getDatename() {
                return datename;
            }

            public void setDatename(String datename) {
                this.datename = datename;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }
        }
    }
}