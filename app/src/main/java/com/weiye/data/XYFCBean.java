package com.weiye.data;

import java.util.List;

/**
 * Created by DELL on 2017/5/12.
 */
public class XYFCBean {

    /**
     * code : 1000
     * message : 获取数据成功
     * data : {"one":[{"url":"Uploads/subject/2017-09-18/59bf7565befc3.jpg","type":3},{"url":"Uploads/subject/2017-09-18/59bf757f0ba7a.png","type":3},{"url":"Uploads/subject/2017-09-18/59bf7599afa53.jpg","type":3},{"url":"Uploads/subject/2017-09-18/59bf75a94c705.jpg","type":3}],"two":[{"url":"Uploads/subject/2017-09-18/59bf74581f307.jpg","type":4},{"url":"Uploads/subject/2017-09-18/59bf751085e5e.png","type":4},{"url":"Uploads/subject/2017-09-18/59bf751f173fc.png","type":4},{"url":"Uploads/subject/2017-09-18/59bf752d58139.png","type":4},{"url":"Uploads/subject/2017-09-18/59bf75380f94a.png","type":4}]}
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
        private List<OneBean> one;
        private List<TwoBean> two;

        public List<OneBean> getOne() {
            return one;
        }

        public void setOne(List<OneBean> one) {
            this.one = one;
        }

        public List<TwoBean> getTwo() {
            return two;
        }

        public void setTwo(List<TwoBean> two) {
            this.two = two;
        }

        public static class OneBean {
            /**
             * url : Uploads/subject/2017-09-18/59bf7565befc3.jpg
             * type : 3
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

        public static class TwoBean {
            /**
             * url : Uploads/subject/2017-09-18/59bf74581f307.jpg
             * type : 4
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
    }
}
