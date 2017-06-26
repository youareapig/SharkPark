package com.weiye.data;

/**
 * Created by DELL on 2017/5/12.
 */
public class SubjectStationBean {

    /**
     * code : 1000
     * message : 成功
     * data : {"title":"喜洋洋","pic":"Uploads/park/2017-06-22/594b430942c9f.jpg","content":"&lt;p&gt;hha&amp;nbsp;&lt;/p&gt;","addtime":"2017-06-22 13:42:24"}
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
         * title : 喜洋洋
         * pic : Uploads/park/2017-06-22/594b430942c9f.jpg
         * content : &lt;p&gt;hha&amp;nbsp;&lt;/p&gt;
         * addtime : 2017-06-22 13:42:24
         */

        private String title;
        private String pic;
        private String content;
        private String addtime;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }
    }
}
