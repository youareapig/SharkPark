package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/5/10.
 */
public class UserInfoBean {

    /**
     * total : 1
     * rows : [{"ID":10,"YHZH":"18140463430","YHMM":"e10adc3949ba59abbe56e057f20f883e","ZJDLSJ":"2017-06-05","SFDL":1,"ZSXM":null,"LX":1,"LXDH":null,"XB":"男","NC":null,"TXLJ":"UploadFile/20170605195706269.png","CSRQ":"2017/5/9 10:30:16","NN":"0"}]
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
         * ID : 10
         * YHZH : 18140463430
         * YHMM : e10adc3949ba59abbe56e057f20f883e
         * ZJDLSJ : 2017-06-05
         * SFDL : 1
         * ZSXM : null
         * LX : 1
         * LXDH : null
         * XB : 男
         * NC : null
         * TXLJ : UploadFile/20170605195706269.png
         * CSRQ : 2017/5/9 10:30:16
         * NN : 0
         */

        private int ID;
        private String YHZH;
        private String YHMM;
        private String ZJDLSJ;
        private int SFDL;
        private Object ZSXM;
        private int LX;
        private Object LXDH;
        private String XB;
        private Object NC;
        private String TXLJ;
        private String CSRQ;
        private String NN;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getYHZH() {
            return YHZH;
        }

        public void setYHZH(String YHZH) {
            this.YHZH = YHZH;
        }

        public String getYHMM() {
            return YHMM;
        }

        public void setYHMM(String YHMM) {
            this.YHMM = YHMM;
        }

        public String getZJDLSJ() {
            return ZJDLSJ;
        }

        public void setZJDLSJ(String ZJDLSJ) {
            this.ZJDLSJ = ZJDLSJ;
        }

        public int getSFDL() {
            return SFDL;
        }

        public void setSFDL(int SFDL) {
            this.SFDL = SFDL;
        }

        public Object getZSXM() {
            return ZSXM;
        }

        public void setZSXM(Object ZSXM) {
            this.ZSXM = ZSXM;
        }

        public int getLX() {
            return LX;
        }

        public void setLX(int LX) {
            this.LX = LX;
        }

        public Object getLXDH() {
            return LXDH;
        }

        public void setLXDH(Object LXDH) {
            this.LXDH = LXDH;
        }

        public String getXB() {
            return XB;
        }

        public void setXB(String XB) {
            this.XB = XB;
        }

        public Object getNC() {
            return NC;
        }

        public void setNC(Object NC) {
            this.NC = NC;
        }

        public String getTXLJ() {
            return TXLJ;
        }

        public void setTXLJ(String TXLJ) {
            this.TXLJ = TXLJ;
        }

        public String getCSRQ() {
            return CSRQ;
        }

        public void setCSRQ(String CSRQ) {
            this.CSRQ = CSRQ;
        }

        public String getNN() {
            return NN;
        }

        public void setNN(String NN) {
            this.NN = NN;
        }
    }
}
