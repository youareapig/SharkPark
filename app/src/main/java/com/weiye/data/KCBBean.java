package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/6/6.
 */
public class KCBBean {

    /**
     * total : 2
     * rows : [{"TEMPFYFILED":"1","ID":"2","KCID":"2","KSSJ":"2017/6/5 0:00:00","JSSJ":"2017/6/5 0:00:00","SKJSID":"3","CJRQ":"","CJRID":"","KCMC":"植物大战1","KCMS":"植物大战1","ZBSX":"植物大战1","ZSXM":"罗梦婷","TX":"98","TXLJ":"UploadFile/201701310301318756332.jpg"},{"TEMPFYFILED":"2","ID":"1","KCID":"1","KSSJ":"2015/12/23 9:00:00","JSSJ":"2015/12/23 10:00:00","SKJSID":"2","CJRQ":"","CJRID":"","KCMC":"植物大战","KCMS":"植物大战","ZBSX":"植物大战","ZSXM":"肖雲云","TX":"100","TXLJ":"UploadFile/201703310303084831919.jpg"}]
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
         * ID : 2
         * KCID : 2
         * KSSJ : 2017/6/5 0:00:00
         * JSSJ : 2017/6/5 0:00:00
         * SKJSID : 3
         * CJRQ :
         * CJRID :
         * KCMC : 植物大战1
         * KCMS : 植物大战1
         * ZBSX : 植物大战1
         * ZSXM : 罗梦婷
         * TX : 98
         * TXLJ : UploadFile/201701310301318756332.jpg
         */

        private String TEMPFYFILED;
        private String ID;
        private String KCID;
        private String KSSJ;
        private String JSSJ;
        private String SKJSID;
        private String CJRQ;
        private String CJRID;
        private String KCMC;
        private String KCMS;
        private String ZBSX;
        private String ZSXM;
        private String TX;
        private String TXLJ;

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

        public String getKCID() {
            return KCID;
        }

        public void setKCID(String KCID) {
            this.KCID = KCID;
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

        public String getSKJSID() {
            return SKJSID;
        }

        public void setSKJSID(String SKJSID) {
            this.SKJSID = SKJSID;
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

        public String getTX() {
            return TX;
        }

        public void setTX(String TX) {
            this.TX = TX;
        }

        public String getTXLJ() {
            return TXLJ;
        }

        public void setTXLJ(String TXLJ) {
            this.TXLJ = TXLJ;
        }
    }
}
