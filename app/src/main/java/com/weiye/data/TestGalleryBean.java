package com.weiye.data;

/**
 * Created by DELL on 2017/4/18.
 */
public class TestGalleryBean {
    private String mString;
    private int mImage;

    public TestGalleryBean(String mString, int mImage) {
        this.mString = mString;
        this.mImage = mImage;
    }

    public String getmString() {
        return mString;
    }

    public void setmString(String mString) {
        this.mString = mString;
    }

    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }
}
