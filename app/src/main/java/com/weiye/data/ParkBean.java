package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/6/22.
 */

public class ParkBean {

    /**
     * code : 1000
     * message : 获取数据成功
     * data : {"banner":[{"url":"Uploads/banner/151013303710.jpg","type":1},{"url":"Uploads/banner/151013304548.jpg","type":1},{"url":"Uploads/banner/151013305723.jpg","type":1}],"pic":[{"url":"Uploads/park/2017-07-20/59705e4437e68.jpg","type":2},{"url":"Uploads/park/2017-07-20/59705e443a326.jpg","type":2},{"url":"Uploads/park/2017-07-20/59705e443d9c3.png","type":2}],"isup":{"id":2,"title":"无处不在的小科学","pic":"Uploads/park/2017-07-20/5970656d0f8db.jpg"},"data":[{"id":3,"title":"你发现过生活中有趣的东西吗？","pic":"Uploads/park/2017-07-20/59706bafd88ff.jpg","addtime":"2017-11-14 11:33:02"},{"id":4,"title":"科学启蒙第一步\u2014\u2014实验","pic":"Uploads/park/2017-07-21/5971647b844d0.jpg","addtime":"2017-07-21 10:24:31"},{"id":5,"title":"6个科学小实验\u2014\u2014简单易操作","pic":"Uploads/park/2017-08-09/598ac0cd9b709.jpg","addtime":"2017-08-09 15:59:09"},{"id":6,"title":"科普小知识，替你解答十万个为什么","pic":"Uploads/park/2017-08-09/598ac203bd499.jpg","addtime":"2017-08-09 16:04:19"},{"id":7,"title":"科学和生活，一样不落","pic":"Uploads/park/2017-08-09/598ac35cf2275.jpg","addtime":"2017-08-09 16:10:04"},{"id":8,"title":"生活中的与众不同，等你来发现","pic":"Uploads/park/2017-08-09/598ac662679db.jpg","addtime":"2017-08-09 16:22:58"},{"id":9,"title":"能动手的就不要懒惰，赶快行动起来吧！","pic":"Uploads/park/2017-08-10/598bff7a550f4.jpg","addtime":"2017-08-10 14:39:17"},{"id":10,"title":"十三个冷知识，你知道几个？","pic":"Uploads/park/2017-08-10/598c0467d0f93.jpg","addtime":"2017-08-10 15:12:01"}]}
     */

    private int code;
    private String message;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * banner : [{"url":"Uploads/banner/151013303710.jpg","type":1},{"url":"Uploads/banner/151013304548.jpg","type":1},{"url":"Uploads/banner/151013305723.jpg","type":1}]
         * pic : [{"url":"Uploads/park/2017-07-20/59705e4437e68.jpg","type":2},{"url":"Uploads/park/2017-07-20/59705e443a326.jpg","type":2},{"url":"Uploads/park/2017-07-20/59705e443d9c3.png","type":2}]
         * isup : {"id":2,"title":"无处不在的小科学","pic":"Uploads/park/2017-07-20/5970656d0f8db.jpg"}
         * data : [{"id":3,"title":"你发现过生活中有趣的东西吗？","pic":"Uploads/park/2017-07-20/59706bafd88ff.jpg","addtime":"2017-11-14 11:33:02"},{"id":4,"title":"科学启蒙第一步\u2014\u2014实验","pic":"Uploads/park/2017-07-21/5971647b844d0.jpg","addtime":"2017-07-21 10:24:31"},{"id":5,"title":"6个科学小实验\u2014\u2014简单易操作","pic":"Uploads/park/2017-08-09/598ac0cd9b709.jpg","addtime":"2017-08-09 15:59:09"},{"id":6,"title":"科普小知识，替你解答十万个为什么","pic":"Uploads/park/2017-08-09/598ac203bd499.jpg","addtime":"2017-08-09 16:04:19"},{"id":7,"title":"科学和生活，一样不落","pic":"Uploads/park/2017-08-09/598ac35cf2275.jpg","addtime":"2017-08-09 16:10:04"},{"id":8,"title":"生活中的与众不同，等你来发现","pic":"Uploads/park/2017-08-09/598ac662679db.jpg","addtime":"2017-08-09 16:22:58"},{"id":9,"title":"能动手的就不要懒惰，赶快行动起来吧！","pic":"Uploads/park/2017-08-10/598bff7a550f4.jpg","addtime":"2017-08-10 14:39:17"},{"id":10,"title":"十三个冷知识，你知道几个？","pic":"Uploads/park/2017-08-10/598c0467d0f93.jpg","addtime":"2017-08-10 15:12:01"}]
         */

        private IsupBean isup;
        private List<BannerBean> banner;
        private List<PicBean> pic;
        private List<DataBean> data;

        public IsupBean getIsup() {
            return isup;
        }

        public void setIsup(IsupBean isup) {
            this.isup = isup;
        }

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<PicBean> getPic() {
            return pic;
        }

        public void setPic(List<PicBean> pic) {
            this.pic = pic;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class IsupBean {
            /**
             * id : 2
             * title : 无处不在的小科学
             * pic : Uploads/park/2017-07-20/5970656d0f8db.jpg
             */

            private int id;
            private String title;
            private String pic;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }

        public static class BannerBean {
            /**
             * url : Uploads/banner/151013303710.jpg
             * type : 1
             */

            private String url;
            private int type;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }

        public static class PicBean {
            /**
             * url : Uploads/park/2017-07-20/59705e4437e68.jpg
             * type : 2
             */

            private String url;
            private int type;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }

        public static class DataBean {
            /**
             * id : 3
             * title : 你发现过生活中有趣的东西吗？
             * pic : Uploads/park/2017-07-20/59706bafd88ff.jpg
             * addtime : 2017-11-14 11:33:02
             */

            private int id;
            private String title;
            private String pic;
            private String addtime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }
        }
    }
}
