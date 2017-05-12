package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/5/12.
 */
public class InfoBean {


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
         * GLID : 3
         * TX : 7
         * MS :
         * LX : 2
         * CJRQ :
         * SP : 1
         * SFSP : 1
         * TXLJ : UploadFile/3.jpg
         * SPLJ : UploadFile/123.jpg
         */

        private String TEMPFYFILED;
        private String ID;
        private String GLID;
        private String TX;
        private String MS;
        private String LX;
        private String CJRQ;
        private String SP;
        private String SFSP;
        private String TXLJ;
        private String SPLJ;

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

        public String getGLID() {
            return GLID;
        }

        public void setGLID(String GLID) {
            this.GLID = GLID;
        }

        public String getTX() {
            return TX;
        }

        public void setTX(String TX) {
            this.TX = TX;
        }

        public String getMS() {
            return MS;
        }

        public void setMS(String MS) {
            this.MS = MS;
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

        public String getSP() {
            return SP;
        }

        public void setSP(String SP) {
            this.SP = SP;
        }

        public String getSFSP() {
            return SFSP;
        }

        public void setSFSP(String SFSP) {
            this.SFSP = SFSP;
        }

        public String getTXLJ() {
            return TXLJ;
        }

        public void setTXLJ(String TXLJ) {
            this.TXLJ = TXLJ;
        }

        public String getSPLJ() {
            return SPLJ;
        }

        public void setSPLJ(String SPLJ) {
            this.SPLJ = SPLJ;
        }
    }
}
