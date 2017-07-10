package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/5/12.
 */
public class PhotoBean {

    /**
     * code : 1000
     * message : 查询成功
     * data : [{"purl":["Uploads/subject/2017-07-07/595ef64f44054.jpg","Uploads/subject/2017-07-07/595ef64f44ae1.jpg","Uploads/subject/2017-07-07/595ef64f4538a.jpg"],"ptitle":"课堂实验"},{"purl":["Uploads/subject/2017-07-07/595ef66b7d9b2.jpg","Uploads/subject/2017-07-07/595ef66b7e3ee.jpg","Uploads/subject/2017-07-07/595ef66b7eabc.jpg"],"ptitle":"校园风采"},{"purl":["Uploads/subject/2017-07-07/595ef6f2df54e.jpg","Uploads/subject/2017-07-07/595ef6f2e025a.jpg","Uploads/subject/2017-07-07/595ef6f2e0a8b.jpg"],"ptitle":"雏鹰起飞"},{"purl":["Uploads/subject/2017-07-07/595ef72e6ffdb.jpg","Uploads/subject/2017-07-07/595ef72e715a4.jpg","Uploads/subject/2017-07-07/595ef72e71ca2.jpg"],"ptitle":"初升的太阳"},{"purl":["Uploads/subject/2017-07-07/595ef77eb412e.jpg","Uploads/subject/2017-07-07/595ef77eb49fa.jpg","Uploads/subject/2017-07-07/595ef77eb51ac.jpg"],"ptitle":"时代在召唤"},{"purl":["Uploads/subject/2017-07-07/595ef7f14dd83.jpg","Uploads/subject/2017-07-07/595ef7f14e7f3.jpg","Uploads/subject/2017-07-07/595ef7f14ef4b.jpg"],"ptitle":"科学风采"}]
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
         * purl : ["Uploads/subject/2017-07-07/595ef64f44054.jpg","Uploads/subject/2017-07-07/595ef64f44ae1.jpg","Uploads/subject/2017-07-07/595ef64f4538a.jpg"]
         * ptitle : 课堂实验
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
