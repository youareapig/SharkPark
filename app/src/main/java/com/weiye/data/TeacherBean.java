package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/5/12.
 */
public class TeacherBean {

    /**
     * code : 1000
     * message : 获取数据成功
     * data : {"uname":"图图老师","desc":"<p>毕业于西京学院，大学期间数次参加省级比赛，带领团队屡获奖项。责任心强，并且有着丰富的带队经验，注重团队合作。在深入了解STEM的科学教育之后，赞同寓教于乐的教育理念！用自己热爱教育事业的心营造出轻松的课堂氛围，借鉴之前的带队经验，让学生做课题的主任，感受科学的无穷魅力，在快乐中学校与成长!<\/p>","imgs":[""]}
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
         * uname : 图图老师
         * desc : <p>毕业于西京学院，大学期间数次参加省级比赛，带领团队屡获奖项。责任心强，并且有着丰富的带队经验，注重团队合作。在深入了解STEM的科学教育之后，赞同寓教于乐的教育理念！用自己热爱教育事业的心营造出轻松的课堂氛围，借鉴之前的带队经验，让学生做课题的主任，感受科学的无穷魅力，在快乐中学校与成长!</p>
         * imgs : [""]
         */

        private String uname;
        private String desc;
        private List<String> imgs;

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public List<String> getImgs() {
            return imgs;
        }

        public void setImgs(List<String> imgs) {
            this.imgs = imgs;
        }
    }
}
