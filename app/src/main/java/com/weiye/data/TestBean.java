package com.weiye.data;

/**
 * Created by DELL on 2017/4/14.
 */
public class TestBean {
    private int img;
    private String name;

    public TestBean(int img, String name) {
        this.img = img;
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
