package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/5/9.
 */
public class IndexBean {

    /**
     * code : 1000
     * message : 成功
     * data : [{"sbid":"1","sbtitle":"大一","sbpic":"Uploads/subject/2017-07-18//596da596f0dfd.jpg","agenumber":"0-6岁"},{"sbid":"2","sbtitle":"大二","sbpic":"Uploads/subject/2017-07-18//596da5bbc5bd9.jpg","agenumber":"3-6岁"},{"sbid":"3","sbtitle":"大三","sbpic":"Uploads/subject/2017-07-18//596da5c801078.jpg","agenumber":"5-10岁"}]
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
         * sbid : 1
         * sbtitle : 大一
         * sbpic : Uploads/subject/2017-07-18//596da596f0dfd.jpg
         * agenumber : 0-6岁
         */

        private String sbid;
        private String sbtitle;
        private String sbpic;
        private String agenumber;

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

        public String getSbpic() {
            return sbpic;
        }

        public void setSbpic(String sbpic) {
            this.sbpic = sbpic;
        }

        public String getAgenumber() {
            return agenumber;
        }

        public void setAgenumber(String agenumber) {
            this.agenumber = agenumber;
        }
    }
}
