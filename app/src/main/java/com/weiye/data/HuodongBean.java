package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/5/12.
 */
public class HuodongBean {

    /**
     * code : 1000
     * message : 查询成功
     * data : [{"title":"6月12号开学典礼","bjimg":"Uploads/subject/active/2017-07-06//595dd634650cb.jpg","isvideo":"0","vurl":"","photos":["Uploads/subject/active/2017-07-06/595dd668c9dbc.jpg","Uploads/subject/active/2017-07-06/595dd668cb84d.jpg"]},{"title":"展望未来","bjimg":"Uploads/subject/active/2017-07-06//595dfe7dae682.jpg","isvideo":"1","vurl":"","photos":["Uploads/subject/active/2017-07-06/595dfe7db2639.jpg"]},{"title":"课间实验室","bjimg":"Uploads/subject/active/2017-07-06//595e00307e57a.jpg","isvideo":"1","vurl":"/video/vide1.mp4","photos":[""]}]
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
         * title : 6月12号开学典礼
         * bjimg : Uploads/subject/active/2017-07-06//595dd634650cb.jpg
         * isvideo : 0
         * vurl :
         * photos : ["Uploads/subject/active/2017-07-06/595dd668c9dbc.jpg","Uploads/subject/active/2017-07-06/595dd668cb84d.jpg"]
         */

        private String title;
        private String bjimg;
        private String isvideo;
        private String vurl;
        private List<String> photos;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBjimg() {
            return bjimg;
        }

        public void setBjimg(String bjimg) {
            this.bjimg = bjimg;
        }

        public String getIsvideo() {
            return isvideo;
        }

        public void setIsvideo(String isvideo) {
            this.isvideo = isvideo;
        }

        public String getVurl() {
            return vurl;
        }

        public void setVurl(String vurl) {
            this.vurl = vurl;
        }

        public List<String> getPhotos() {
            return photos;
        }

        public void setPhotos(List<String> photos) {
            this.photos = photos;
        }
    }
}
