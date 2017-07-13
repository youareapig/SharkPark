package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/5/12.
 */
public class TeacherBean {

    /**
     * code : 1000
     * message : 成功
     * data : {"nickname":"小六","desc":"","pic":["Uploads/teacher/2017-07-07/595f010f3a5f9.jpg","Uploads/teacher/2017-07-07/595f010f3acb4.jpg"]}
     */

    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * nickname : 小六
         * desc :
         * pic : ["Uploads/teacher/2017-07-07/595f010f3a5f9.jpg","Uploads/teacher/2017-07-07/595f010f3acb4.jpg"]
         */

        private String nickname;
        private String desc;
        private List<String> pic;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public List<String> getPic() {
            return pic;
        }

        public void setPic(List<String> pic) {
            this.pic = pic;
        }
    }
}
