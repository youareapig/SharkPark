package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/7/7.
 */

public class CourseBeans {

    /**
     * code : 1000
     * message : 查询成功
     * data : [{"coname":"音乐"},{"coname":"生物"},{"coname":"英语"},{"coname":"活动课"},{"coname":"数学"},{"coname":"化学"},{"coname":"语文"},{"coname":"体育"},{"coname":"物理"},{"coname":"国学"},{"coname":"舞蹈"},{"coname":"数学"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * coname : 音乐
         */

        private String coname;

        public String getConame() {
            return coname;
        }

        public void setConame(String coname) {
            this.coname = coname;
        }
    }
}
