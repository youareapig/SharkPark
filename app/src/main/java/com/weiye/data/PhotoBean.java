package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/5/12.
 */
public class PhotoBean {

    /**
     * code : 1000
     * message : 查询成功
     * data : [{"ptitle":"第一张","purl":["Uploads/subject/2017-06-21/594a307b8d545.png","Uploads/subject/2017-06-21/594a307b8e59b.png","Uploads/subject/2017-06-21/594a307b8f45c.png","Uploads/subject/2017-06-21/594a307b90280.png","Uploads/subject/2017-06-21/594a307b9130b.png","Uploads/subject/2017-06-21/594a307b920da.png"]},{"ptitle":"第二张","purl":["Uploads/subject/2017-06-21/594a309804bfe.jpg","Uploads/subject/2017-06-21/594a309805297.png","Uploads/subject/2017-06-21/594a309805815.png","Uploads/subject/2017-06-21/594a309805dd0.png"]}]
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
         * ptitle : 第一张
         * purl : ["Uploads/subject/2017-06-21/594a307b8d545.png","Uploads/subject/2017-06-21/594a307b8e59b.png","Uploads/subject/2017-06-21/594a307b8f45c.png","Uploads/subject/2017-06-21/594a307b90280.png","Uploads/subject/2017-06-21/594a307b9130b.png","Uploads/subject/2017-06-21/594a307b920da.png"]
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
