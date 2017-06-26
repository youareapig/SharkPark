package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/6/22.
 */

public class ParkBean {

    /**
     * code : 1000
     * message : 请求成功
     * data : {"info":[{"pic":"Uploads/park/2017-06-22/594b430942c9f.jpg","title":"喜洋洋","addtime":"2017-06-22 12:09:45","id":"2"}],"banner":[{"url":"Uploads/banner/2017-06-22//594b40d559500.png"},{"url":"Uploads/banner/2017-06-22//594b40df3590d.png"},{"url":"Uploads/banner/2017-06-22//594b40e7e653e.png"}],"pic":["Uploads/park/2017-06-22/594b429b12581.jpg","Uploads/park/2017-06-22/594b429b14a04.jpg","Uploads/park/2017-06-22/594b429b15f69.jpg"]}
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
        private List<InfoBean> info;
        private List<BannerBean> banner;
        private List<String> pic;

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

        public static class InfoBean {
            /**
             * pic : Uploads/park/2017-06-22/594b430942c9f.jpg
             * title : 喜洋洋
             * addtime : 2017-06-22 12:09:45
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

        public static class BannerBean {
            /**
             * url : Uploads/banner/2017-06-22//594b40d559500.png
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
