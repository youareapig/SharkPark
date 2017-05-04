package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/5/2.
 */
public class AboutBean {

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
         * ID : 1
         * GYWM : 与萨取异物翘尾巴我期望完全千万完全为其权威网球完全千万完全为其完全为其权威网球完全期望完全完全千万完全千万千瓦
         * CJRQ : 0001-01-02
         * CJRID : 0
         */

        private int ID;
        private String GYWM;
        private String CJRQ;
        private int CJRID;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getGYWM() {
            return GYWM;
        }

        public void setGYWM(String GYWM) {
            this.GYWM = GYWM;
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
