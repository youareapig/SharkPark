package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/5/19.
 */
public class XYJJBean {

    /**
     * total : 1
     * rows : [{"ID":1,"JJMS":"<div>成都佳佩爱教育科技有限公司（成都鲨鱼公园儿童大学）是中国青少年科技教育知名品牌，专业针对3-12岁儿童，提供动手科学产品及趣味性的课程学习，课程覆盖物理、化学、技术、工程、数学、艺术、生物、天文等领域。<\/div>","CJRQ":"0001-01-02","CJRID":0}]
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
         * ID : 1
         * JJMS : <div>成都佳佩爱教育科技有限公司（成都鲨鱼公园儿童大学）是中国青少年科技教育知名品牌，专业针对3-12岁儿童，提供动手科学产品及趣味性的课程学习，课程覆盖物理、化学、技术、工程、数学、艺术、生物、天文等领域。</div>
         * CJRQ : 0001-01-02
         * CJRID : 0
         */

        private int ID;
        private String JJMS;
        private String CJRQ;
        private int CJRID;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getJJMS() {
            return JJMS;
        }

        public void setJJMS(String JJMS) {
            this.JJMS = JJMS;
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
