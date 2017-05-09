package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/5/9.
 */
public class IndexBean {

    /**
     * total : 5
     * rows : [{"ID":5,"LXMC":"视频类","LXBC":"视频","LXMS":"视频类","TX":2,"TXLJ":"UploadFile/123.jpg","BJTX":3,"BJTXLJ":"UploadFile/123.jpg","CJRQ":"2017-05-01","CJRID":2},{"ID":4,"LXMC":"摄影类","LXBC":"观查","LXMS":"摄影类","TX":2,"TXLJ":"UploadFile/123.jpg","BJTX":3,"BJTXLJ":"UploadFile/123.jpg","CJRQ":"2017-05-01","CJRID":2},{"ID":3,"LXMC":"自然类","LXBC":"天文","LXMS":"自然类","TX":2,"TXLJ":"UploadFile/123.jpg","BJTX":3,"BJTXLJ":"UploadFile/123.jpg","CJRQ":"2017-05-01","CJRID":2},{"ID":2,"LXMC":"科学类","LXBC":"科学","LXMS":"科学类","TX":2,"TXLJ":"UploadFile/123.jpg","BJTX":3,"BJTXLJ":"UploadFile/123.jpg","CJRQ":"2017-05-01","CJRID":2},{"ID":1,"LXMC":"公开课","LXBC":"日常","LXMS":"公开课","TX":2,"TXLJ":"UploadFile/123.jpg","BJTX":3,"BJTXLJ":"UploadFile/123.jpg","CJRQ":"2017-05-01","CJRID":2}]
     */

    private int total;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * ID : 5
         * LXMC : 视频类
         * LXBC : 视频
         * LXMS : 视频类
         * TX : 2
         * TXLJ : UploadFile/123.jpg
         * BJTX : 3
         * BJTXLJ : UploadFile/123.jpg
         * CJRQ : 2017-05-01
         * CJRID : 2
         */

        private int ID;
        private String LXMC;
        private String LXBC;
        private String LXMS;
        private int TX;
        private String TXLJ;
        private int BJTX;
        private String BJTXLJ;
        private String CJRQ;
        private int CJRID;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getLXMC() {
            return LXMC;
        }

        public void setLXMC(String LXMC) {
            this.LXMC = LXMC;
        }

        public String getLXBC() {
            return LXBC;
        }

        public void setLXBC(String LXBC) {
            this.LXBC = LXBC;
        }

        public String getLXMS() {
            return LXMS;
        }

        public void setLXMS(String LXMS) {
            this.LXMS = LXMS;
        }

        public int getTX() {
            return TX;
        }

        public void setTX(int TX) {
            this.TX = TX;
        }

        public String getTXLJ() {
            return TXLJ;
        }

        public void setTXLJ(String TXLJ) {
            this.TXLJ = TXLJ;
        }

        public int getBJTX() {
            return BJTX;
        }

        public void setBJTX(int BJTX) {
            this.BJTX = BJTX;
        }

        public String getBJTXLJ() {
            return BJTXLJ;
        }

        public void setBJTXLJ(String BJTXLJ) {
            this.BJTXLJ = BJTXLJ;
        }

        public String getCJRQ() {
            return CJRQ;
        }

        public void setCJRQ(String CJRQ) {
            this.CJRQ = CJRQ;
        }

        public int getCJRID() {
            return CJRID;
        }

        public void setCJRID(int CJRID) {
            this.CJRID = CJRID;
        }
    }
}
