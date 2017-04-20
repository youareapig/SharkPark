package com.weiye.zl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhy.autolayout.AutoLayoutActivity;

import qiu.niorgai.StatusBarCompat;

public class ScreenActivity extends AutoLayoutActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        StatusBarCompat.translucentStatusBar(this, false);
    }
}
