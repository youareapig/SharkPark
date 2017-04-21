package com.weiye.data;

/**
 * Created by DELL on 2017/4/21.
 */
public class TestCurrBean {
    private int icon;
    private String text1;
    private String text2;

    public TestCurrBean(int icon, String text1, String text2) {
        this.icon = icon;
        this.text1 = text1;
        this.text2 = text2;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }
}
