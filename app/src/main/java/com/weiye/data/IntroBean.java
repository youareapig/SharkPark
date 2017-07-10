package com.weiye.data;

/**
 * Created by DELL on 2017/7/7.
 */

public class IntroBean {

    /**
     * code : 1000
     * message : 查询成功
     * data : {"title":"师资力量介绍1","introtext":"<p>我们都是属于这里哈哈鼻血的<\/p>"}
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
         * title : 师资力量介绍1
         * introtext : <p>我们都是属于这里哈哈鼻血的</p>
         */

        private String title;
        private String introtext;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIntrotext() {
            return introtext;
        }

        public void setIntrotext(String introtext) {
            this.introtext = introtext;
        }
    }
}
