package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/5/12.
 */
public class TeacherBean {



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
         * ID : 16
         * LXID : 4
         * RYID : 5
         * MS : 22
         * CJRQ :
         * ZSXM :
         * NC :
         * TX :
         * TXLJ :
         */

        private String TEMPFYFILED;
        private String ID;
        private String LXID;
        private String RYID;
        private String MS;
        private String CJRQ;
        private String ZSXM;
        private String NC;
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

        public String getLXID() {
            return LXID;
        }

        public void setLXID(String LXID) {
            this.LXID = LXID;
        }

        public String getRYID() {
            return RYID;
        }

        public void setRYID(String RYID) {
            this.RYID = RYID;
        }

        public String getMS() {
            return MS;
        }

        public void setMS(String MS) {
            this.MS = MS;
        }

        public String getCJRQ() {
            return CJRQ;
        }

        public void setCJRQ(String CJRQ) {
            this.CJRQ = CJRQ;
        }

        public String getZSXM() {
            return ZSXM;
        }

        public void setZSXM(String ZSXM) {
            this.ZSXM = ZSXM;
        }

        public String getNC() {
            return NC;
        }

        public void setNC(String NC) {
            this.NC = NC;
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
