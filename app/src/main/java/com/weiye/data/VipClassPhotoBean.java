package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/7/13.
 */

public class VipClassPhotoBean {

    /**
     * code : 3000
     * message : 成功
     * data : {"pv":[{"purl":["Uploads/subject/2017-07-13/5966d838da86d.jpg"],"ptitle":"new "},{"purl":["Uploads/subject/2017-07-13/5966d84d30f50.png"],"ptitle":"test"}],"grinfo":{"gpic":"Uploads/subject/class/2017-07-10//5962e622bcbb8.jpg","inform":"海豚班","gname":"海豚班"}}
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
         * pv : [{"purl":["Uploads/subject/2017-07-13/5966d838da86d.jpg"],"ptitle":"new "},{"purl":["Uploads/subject/2017-07-13/5966d84d30f50.png"],"ptitle":"test"}]
         * grinfo : {"gpic":"Uploads/subject/class/2017-07-10//5962e622bcbb8.jpg","inform":"海豚班","gname":"海豚班"}
         */

        private GrinfoBean grinfo;
        private List<PvBean> pv;

        public GrinfoBean getGrinfo() {
            return grinfo;
        }

        public void setGrinfo(GrinfoBean grinfo) {
            this.grinfo = grinfo;
        }

        public List<PvBean> getPv() {
            return pv;
        }

        public void setPv(List<PvBean> pv) {
            this.pv = pv;
        }

        public static class GrinfoBean {
            /**
             * gpic : Uploads/subject/class/2017-07-10//5962e622bcbb8.jpg
             * inform : 海豚班
             * gname : 海豚班
             */

            private String gpic;
            private String inform;
            private String gname;

            public String getGpic() {
                return gpic;
            }

            public void setGpic(String gpic) {
                this.gpic = gpic;
            }

            public String getInform() {
                return inform;
            }

            public void setInform(String inform) {
                this.inform = inform;
            }

            public String getGname() {
                return gname;
            }

            public void setGname(String gname) {
                this.gname = gname;
            }
        }

        public static class PvBean {
            /**
             * purl : ["Uploads/subject/2017-07-13/5966d838da86d.jpg"]
             * ptitle : new
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
