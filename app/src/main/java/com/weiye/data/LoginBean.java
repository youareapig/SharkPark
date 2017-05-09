package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/5/5.
 */
public class LoginBean {


    private boolean Success;
    private String msg;
    private List<RowsBean> rows;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * ID : 2
         * YHZH : 15983302246
         * YHMM : 21232f297a57a5a743894a0e4a801fc3
         * ZJDLSJ : 2017-05-05 14:38:53
         * SFDL : 1
         */

        private int ID;
        private String YHZH;
        private String YHMM;
        private String ZJDLSJ;
        private int SFDL;

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
    }
}
