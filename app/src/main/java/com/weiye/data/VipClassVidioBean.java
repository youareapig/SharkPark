package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/6/21.
 */

public class VipClassVidioBean {

    /**
     * code : 3000
     * message : 成功
     * data : {"pv":[{"vimg":"Uploads/subject/2017-07-13//5966d7dc7bba7.jpg","vtitle":"海豚海豚","vurl":"海豚。avi"},{"vimg":"Uploads/subject/2017-07-13//5966d8130c067.jpg","vtitle":"111111","vurl":"11111.mp4"}],"grinfo":{"gpic":"Uploads/subject/class/2017-07-10//5962e622bcbb8.jpg","inform":"海豚班","gname":"海豚班"}}
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
         * pv : [{"vimg":"Uploads/subject/2017-07-13//5966d7dc7bba7.jpg","vtitle":"海豚海豚","vurl":"海豚。avi"},{"vimg":"Uploads/subject/2017-07-13//5966d8130c067.jpg","vtitle":"111111","vurl":"11111.mp4"}]
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
             * vimg : Uploads/subject/2017-07-13//5966d7dc7bba7.jpg
             * vtitle : 海豚海豚
             * vurl : 海豚。avi
             */

            private String vimg;
            private String vtitle;
            private String vurl;

            public String getVimg() {
                return vimg;
            }

            public void setVimg(String vimg) {
                this.vimg = vimg;
            }

            public String getVtitle() {
                return vtitle;
            }

            public void setVtitle(String vtitle) {
                this.vtitle = vtitle;
            }

            public String getVurl() {
                return vurl;
            }

            public void setVurl(String vurl) {
                this.vurl = vurl;
            }
        }
    }
}
