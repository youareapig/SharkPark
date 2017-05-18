package com.weiye.data;

/**
 * Created by DELL on 2017/5/16.
 */
public class TestBean {
    private String string;
    private boolean aBoolean;

    public TestBean(String string, boolean aBoolean) {
        this.string = string;
        this.aBoolean = aBoolean;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public boolean isaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }
}
