package com.weiye.utils;

/**
 * Created by Dell on 2017/3/7.
 * 单例模式实现全局变量
 */
public class SingleModleUrl {
    private String imgUrl = "http://www.sharkparkcd.xyz:8820/";
    private String testUrl = "http://www.sharkparkcd.xyz:8820/app.php/";

//    private String imgUrl = "http://192.168.10.130/";
//    private String testUrl = "http://192.168.10.130/app.php/";


    public String getImgUrl() {
        return imgUrl;
    }

    private SingleModleUrl() {
    }

    public String getTestUrl() {
        return testUrl;
    }

    private static class SingleModleUrlHolder {
        private static SingleModleUrl singleModleUrl = new SingleModleUrl();
    }

    public static SingleModleUrl singleModleUrl() {
        return SingleModleUrlHolder.singleModleUrl;
    }
}
