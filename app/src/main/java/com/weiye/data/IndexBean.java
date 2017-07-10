package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/5/9.
 */
public class IndexBean {

    /**
     * code : 1000
     * message : 成功
     * data : [{"sbid":"1","sbtitle":"技术与工程学","sbpic":"Uploads/subject/2017-06-21//594a2a9d72f6a.jpg"},{"sbid":"2","sbtitle":"物质与科学","sbpic":"Uploads/subject/2017-06-21//594a2ac50ebc3.jpg"},{"sbid":"3","sbtitle":"地球与宇宙科学","sbpic":"Uploads/subject/2017-06-21//594a2ade87ccf.jpg"},{"sbid":"4","sbtitle":"生命与科学","sbpic":"Uploads/subject/2017-06-21//594a2af3d4659.jpg"}]
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


        private String sbid;
        private String sbtitle;
        private String sbpic;

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
    }
}
