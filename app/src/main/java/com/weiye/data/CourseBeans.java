package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/7/7.
 */

public class CourseBeans {

    /**
     * code : 1000
     * message : 查询成功
     * data : {"data":[{"coname":"数学"}],"time":[{"datename":"10:30-11:30"}]}
     */

    private int code;
    private String message;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        private List<DataBean> data;
        private List<TimeBean> time;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public List<TimeBean> getTime() {
            return time;
        }

        public void setTime(List<TimeBean> time) {
            this.time = time;
        }

        public static class DataBean {
            /**
             * coname : 数学
             */

            private String coname;

            public String getConame() {
                return coname;
            }

            public void setConame(String coname) {
                this.coname = coname;
            }
        }

        public static class TimeBean {
            /**
             * datename : 10:30-11:30
             */

            private String datename;

            public String getDatename() {
                return datename;
            }

            public void setDatename(String datename) {
                this.datename = datename;
            }
        }
    }
}
