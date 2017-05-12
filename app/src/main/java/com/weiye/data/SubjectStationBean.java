package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/5/12.
 */
public class SubjectStationBean {

    /**
     * total : 4
     * rows : [{"TEMPFYFILED":"1","ID":"8","KTID":"1","FCMC":"植物大战3","FCMS":"植物大战3","TX":"8","LX":"1","BJTX":"","CJRQ":"2015/12/23 0:00:00","KSSJ":"2015/12/23 9:00:00","JSSJ":"2015/12/23 10:00:00","KCMC":"植物大战","KCMS":"植物大战","ZBSX":"植物大战","ZSXM":"郑建波","TXLJ":"UploadFile/4.jpg","BJTXLJ":""},{"TEMPFYFILED":"2","ID":"7","KTID":"1","FCMC":"植物大战2","FCMS":"植物大战2","TX":"7","LX":"1","BJTX":"","CJRQ":"2015/12/23 0:00:00","KSSJ":"2015/12/23 9:00:00","JSSJ":"2015/12/23 10:00:00","KCMC":"植物大战","KCMS":"植物大战","ZBSX":"植物大战","ZSXM":"郑建波","TXLJ":"UploadFile/3.jpg","BJTXLJ":""},{"TEMPFYFILED":"3","ID":"6","KTID":"1","FCMC":"植物大战1","FCMS":"植物大战1","TX":"6","LX":"1","BJTX":"","CJRQ":"2015/12/23 0:00:00","KSSJ":"2015/12/23 9:00:00","JSSJ":"2015/12/23 10:00:00","KCMC":"植物大战","KCMS":"植物大战","ZBSX":"植物大战","ZSXM":"郑建波","TXLJ":"UploadFile/2.jpg","BJTXLJ":""},{"TEMPFYFILED":"4","ID":"5","KTID":"1","FCMC":"植物大战","FCMS":"植物大战","TX":"5","LX":"1","BJTX":"","CJRQ":"2015/12/23 0:00:00","KSSJ":"2015/12/23 9:00:00","JSSJ":"2015/12/23 10:00:00","KCMC":"植物大战","KCMS":"植物大战","ZBSX":"植物大战","ZSXM":"郑建波","TXLJ":"UploadFile/1.jpg","BJTXLJ":""}]
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
         * ID : 8
         * KTID : 1
         * FCMC : 植物大战3
         * FCMS : 植物大战3
         * TX : 8
         * LX : 1
         * BJTX :
         * CJRQ : 2015/12/23 0:00:00
         * KSSJ : 2015/12/23 9:00:00
         * JSSJ : 2015/12/23 10:00:00
         * KCMC : 植物大战
         * KCMS : 植物大战
         * ZBSX : 植物大战
         * ZSXM : 郑建波
         * TXLJ : UploadFile/4.jpg
         * BJTXLJ :
         */

        private String TEMPFYFILED;
        private String ID;
        private String KTID;
        private String FCMC;
        private String FCMS;
        private String TX;
        private String LX;
        private String BJTX;
        private String CJRQ;
        private String KSSJ;
        private String JSSJ;
        private String KCMC;
        private String KCMS;
        private String ZBSX;
        private String ZSXM;
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

        public String getKTID() {
            return KTID;
        }

        public void setKTID(String KTID) {
            this.KTID = KTID;
        }

        public String getFCMC() {
            return FCMC;
        }

        public void setFCMC(String FCMC) {
            this.FCMC = FCMC;
        }

        public String getFCMS() {
            return FCMS;
        }

        public void setFCMS(String FCMS) {
            this.FCMS = FCMS;
        }

        public String getTX() {
            return TX;
        }

        public void setTX(String TX) {
            this.TX = TX;
        }

        public String getLX() {
            return LX;
        }

        public void setLX(String LX) {
            this.LX = LX;
        }

        public String getBJTX() {
            return BJTX;
        }

        public void setBJTX(String BJTX) {
            this.BJTX = BJTX;
        }

        public String getCJRQ() {
            return CJRQ;
        }

        public void setCJRQ(String CJRQ) {
            this.CJRQ = CJRQ;
        }

        public String getKSSJ() {
            return KSSJ;
        }

        public void setKSSJ(String KSSJ) {
            this.KSSJ = KSSJ;
        }

        public String getJSSJ() {
            return JSSJ;
        }

        public void setJSSJ(String JSSJ) {
            this.JSSJ = JSSJ;
        }

        public String getKCMC() {
            return KCMC;
        }

        public void setKCMC(String KCMC) {
            this.KCMC = KCMC;
        }

        public String getKCMS() {
            return KCMS;
        }

        public void setKCMS(String KCMS) {
            this.KCMS = KCMS;
        }

        public String getZBSX() {
            return ZBSX;
        }

        public void setZBSX(String ZBSX) {
            this.ZBSX = ZBSX;
        }

        public String getZSXM() {
            return ZSXM;
        }

        public void setZSXM(String ZSXM) {
            this.ZSXM = ZSXM;
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
