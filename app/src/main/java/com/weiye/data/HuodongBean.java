package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/5/12.
 */
public class HuodongBean {

    /**
     * total : 66
     * rows : [{"TEMPFYFILED":"1","ID":"52","HDMS":"活动15","TX":"6","CJRQ":"","CJRID":"","BJTX":"5","BJSFSP":"0","ZT":"0","TXLJ":"UploadFile/2.jpg","BJTXLJ":""},{"TEMPFYFILED":"2","ID":"49","HDMS":"活动12","TX":"6","CJRQ":"","CJRID":"","BJTX":"5","BJSFSP":"0","ZT":"0","TXLJ":"UploadFile/2.jpg","BJTXLJ":""},{"TEMPFYFILED":"3","ID":"46","HDMS":"活动9","TX":"5","CJRQ":"","CJRID":"","BJTX":"5","BJSFSP":"0","ZT":"0","TXLJ":"UploadFile/1.jpg","BJTXLJ":"UploadFile/123.jpg"},{"TEMPFYFILED":"4","ID":"46","HDMS":"活动9","TX":"5","CJRQ":"","CJRID":"","BJTX":"5","BJSFSP":"0","ZT":"0","TXLJ":"UploadFile/1.jpg","BJTXLJ":"UploadFile/123.jpg"},{"TEMPFYFILED":"5","ID":"46","HDMS":"活动9","TX":"5","CJRQ":"","CJRID":"","BJTX":"5","BJSFSP":"0","ZT":"0","TXLJ":"UploadFile/1.jpg","BJTXLJ":"UploadFile/123.jpg"},{"TEMPFYFILED":"6","ID":"46","HDMS":"活动9","TX":"5","CJRQ":"","CJRID":"","BJTX":"5","BJSFSP":"0","ZT":"0","TXLJ":"UploadFile/1.jpg","BJTXLJ":"http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4"},{"TEMPFYFILED":"7","ID":"46","HDMS":"活动9","TX":"5","CJRQ":"","CJRID":"","BJTX":"5","BJSFSP":"0","ZT":"0","TXLJ":"UploadFile/1.jpg","BJTXLJ":"UploadFile/1.jpg"},{"TEMPFYFILED":"8","ID":"46","HDMS":"活动9","TX":"5","CJRQ":"","CJRID":"","BJTX":"5","BJSFSP":"0","ZT":"0","TXLJ":"UploadFile/1.jpg","BJTXLJ":"UploadFile/2.jpg"},{"TEMPFYFILED":"9","ID":"46","HDMS":"活动9","TX":"5","CJRQ":"","CJRID":"","BJTX":"5","BJSFSP":"0","ZT":"0","TXLJ":"UploadFile/1.jpg","BJTXLJ":"UploadFile/3.jpg"},{"TEMPFYFILED":"10","ID":"46","HDMS":"活动9","TX":"5","CJRQ":"","CJRID":"","BJTX":"5","BJSFSP":"0","ZT":"0","TXLJ":"UploadFile/1.jpg","BJTXLJ":"UploadFile/4.jpg"}]
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
         * TEMPFYFILED : 1
         * ID : 52
         * HDMS : 活动15
         * TX : 6
         * CJRQ :
         * CJRID :
         * BJTX : 5
         * BJSFSP : 0
         * ZT : 0
         * TXLJ : UploadFile/2.jpg
         * BJTXLJ :
         */

        private String TEMPFYFILED;
        private String ID;
        private String HDMS;
        private String TX;
        private String CJRQ;
        private String CJRID;
        private String BJTX;
        private String BJSFSP;
        private String ZT;
        private String TXLJ;
        private String BJTXLJ;

        public String getTEMPFYFILED() {
            return TEMPFYFILED;
        }

        public void setTEMPFYFILED(String TEMPFYFILED) {
            this.TEMPFYFILED = TEMPFYFILED;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getHDMS() {
            return HDMS;
        }

        public void setHDMS(String HDMS) {
            this.HDMS = HDMS;
        }

        public String getTX() {
            return TX;
        }

        public void setTX(String TX) {
            this.TX = TX;
        }

        public String getCJRQ() {
            return CJRQ;
        }

        public void setCJRQ(String CJRQ) {
            this.CJRQ = CJRQ;
        }

        public String getCJRID() {
            return CJRID;
        }

        public void setCJRID(String CJRID) {
            this.CJRID = CJRID;
        }

        public String getBJTX() {
            return BJTX;
        }

        public void setBJTX(String BJTX) {
            this.BJTX = BJTX;
        }

        public String getBJSFSP() {
            return BJSFSP;
        }

        public void setBJSFSP(String BJSFSP) {
            this.BJSFSP = BJSFSP;
        }

        public String getZT() {
            return ZT;
        }

        public void setZT(String ZT) {
            this.ZT = ZT;
        }

        public String getTXLJ() {
            return TXLJ;
        }

        public void setTXLJ(String TXLJ) {
            this.TXLJ = TXLJ;
        }

        public String getBJTXLJ() {
            return BJTXLJ;
        }

        public void setBJTXLJ(String BJTXLJ) {
            this.BJTXLJ = BJTXLJ;
        }
    }
}
