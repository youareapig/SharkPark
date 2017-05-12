package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/5/12.
 */
public class XYFCBean {

    /**
     * total : 1
     * rows : [{"TEMPFYFILED":"1","ID":"1","FCMS":"风采","TX":"5","LX":"0","CJRQ":"","CJRID":"","TXLJ":"UploadFile/1.jpg"}]
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
         * ID : 1
         * FCMS : 风采
         * TX : 5
         * LX : 0
         * CJRQ :
         * CJRID :
         * TXLJ : UploadFile/1.jpg
         */

        private String TEMPFYFILED;
        private String ID;
        private String FCMS;
        private String TX;
        private String LX;
        private String CJRQ;
        private String CJRID;
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

        public String getTXLJ() {
            return TXLJ;
        }

        public void setTXLJ(String TXLJ) {
            this.TXLJ = TXLJ;
        }
    }
}
