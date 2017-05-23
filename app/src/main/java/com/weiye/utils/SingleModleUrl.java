package com.weiye.utils;

/**
 * Created by Dell on 2017/3/7.
 * 单例模式实现全局变量
 */
public class SingleModleUrl {
    private String testUrl = "http://192.168.10.253/Service/";
    private String imgUrl="http://192.168.10.253/";
//    private String testUrl = "http://211.149.248.144:8820/Service/";
//    private String imgUrl="http://211.149.248.144:8820/";

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    private SingleModleUrl() {
    }

    public String getTestUrl() {
        return testUrl;
    }

    public void setTestUrl(String testUrl) {
        this.testUrl = testUrl;
    }

    private static class SingleModleUrlHolder {
        private static SingleModleUrl singleModleUrl = new SingleModleUrl();
    }

    public static SingleModleUrl singleModleUrl() {
        return SingleModleUrlHolder.singleModleUrl;
    }
}
