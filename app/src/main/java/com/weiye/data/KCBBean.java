package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/6/6.
 */
public class KCBBean {

    /**
     * total : 1
     * rows : [{"week":"星期五","monthWeek":"六月第二周","date":"2017-06-09","rows":[{"TEMPFYFILED":"1","ID":"1","KCID":"1","KSSJ":"2017/6/9 9:00:00","JSSJ":"2017/6/9 10:00:00","SKJSID":"2","CJRQ":"","CJRID":"","KCMC":"植物大战","KCMS":"植物大战","ZBSX":"植物大战","ZSXM":"肖雲云","TX":"100","TXLJ":"UploadFile/201703310303084831919.jpg"}]},{"week":"星期六","monthWeek":"六月第二周","date":"2017-06-10","rows":[]},{"week":"星期日","monthWeek":"六月第二周","date":"2017-06-11","rows":[]},{"week":"星期一","monthWeek":"六月第二周","date":"2017-06-12","rows":[]},{"week":"星期二","monthWeek":"六月第二周","date":"2017-06-13","rows":[]},{"week":"星期三","monthWeek":"六月第二周","date":"2017-06-14","rows":[]},{"week":"星期四","monthWeek":"六月第二周","date":"2017-06-15","rows":[]}]
     */

    private int total;
    private List<RowsBeanX> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBeanX> getRows() {
        return rows;
    }

    public void setRows(List<RowsBeanX> rows) {
        this.rows = rows;
    }

    public static class RowsBeanX {
        /**
         * week : 星期五
         * monthWeek : 六月第二周
         * date : 2017-06-09
         * rows : [{"TEMPFYFILED":"1","ID":"1","KCID":"1","KSSJ":"2017/6/9 9:00:00","JSSJ":"2017/6/9 10:00:00","SKJSID":"2","CJRQ":"","CJRID":"","KCMC":"植物大战","KCMS":"植物大战","ZBSX":"植物大战","ZSXM":"肖雲云","TX":"100","TXLJ":"UploadFile/201703310303084831919.jpg"}]
         */

        private String week;
        private String monthWeek;
        private String date;
        private List<RowsBean> rows;

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getMonthWeek() {
            return monthWeek;
        }

        public void setMonthWeek(String monthWeek) {
            this.monthWeek = monthWeek;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
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
             * KCID : 1
             * KSSJ : 2017/6/9 9:00:00
             * JSSJ : 2017/6/9 10:00:00
             * SKJSID : 2
             * CJRQ :
             * CJRID :
             * KCMC : 植物大战
             * KCMS : 植物大战
             * ZBSX : 植物大战
             * ZSXM : 肖雲云
             * TX : 100
             * TXLJ : UploadFile/201703310303084831919.jpg
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
}
