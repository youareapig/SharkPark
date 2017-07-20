package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/6/22.
 */

public class ParkBean {

    /**
     * code : 1000
     * message : 请求成功
     * data : {"info":[{"pic":"Uploads/park/2017-07-20/59706bafd88ff.jpg","title":"你发现过生活中有趣的东西吗？","addtime":"2017-07-20 17:50:01","id":"3"}],"banner":[{"url":"Uploads/banner/2017-07-18//596dabecc88d9.jpg"},{"url":"Uploads/banner/2017-07-18//596dac0d7b963.jpg"}],"pic":["Uploads/park/2017-07-20/59705e4437e68.jpg","Uploads/park/2017-07-20/59705e443a326.jpg","Uploads/park/2017-07-20/59705e443d9c3.png"],"up":{"pic":"Uploads/park/2017-07-20/5970656d0f8db.jpg","title":"无处不在的小科学","addtime":"2017-07-20 18:07:40","id":"2"}}
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
         * info : [{"pic":"Uploads/park/2017-07-20/59706bafd88ff.jpg","title":"你发现过生活中有趣的东西吗？","addtime":"2017-07-20 17:50:01","id":"3"}]
         * banner : [{"url":"Uploads/banner/2017-07-18//596dabecc88d9.jpg"},{"url":"Uploads/banner/2017-07-18//596dac0d7b963.jpg"}]
         * pic : ["Uploads/park/2017-07-20/59705e4437e68.jpg","Uploads/park/2017-07-20/59705e443a326.jpg","Uploads/park/2017-07-20/59705e443d9c3.png"]
         * up : {"pic":"Uploads/park/2017-07-20/5970656d0f8db.jpg","title":"无处不在的小科学","addtime":"2017-07-20 18:07:40","id":"2"}
         */

        private UpBean up;
        private List<InfoBean> info;
        private List<BannerBean> banner;
        private List<String> pic;

        public UpBean getUp() {
            return up;
        }

        public void setUp(UpBean up) {
            this.up = up;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<String> getPic() {
            return pic;
        }

        public void setPic(List<String> pic) {
            this.pic = pic;
        }

        public static class UpBean {
            /**
             * pic : Uploads/park/2017-07-20/5970656d0f8db.jpg
             * title : 无处不在的小科学
             * addtime : 2017-07-20 18:07:40
             * id : 2
             */

            private String pic;
            private String title;
            private String addtime;
            private String id;

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

        public static class InfoBean {
            /**
             * pic : Uploads/park/2017-07-20/59706bafd88ff.jpg
             * title : 你发现过生活中有趣的东西吗？
             * addtime : 2017-07-20 17:50:01
             * id : 3
             */

            private String pic;
            private String title;
            private String addtime;
            private String id;

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

        public static class BannerBean {
            /**
             * url : Uploads/banner/2017-07-18//596dabecc88d9.jpg
             */

            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
