package com.weiye.zl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhy.autolayout.AutoLayoutActivity;

import qiu.niorgai.StatusBarCompat;

public class AppearanceActivity extends AutoLayoutActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appearance);
        StatusBarCompat.translucentStatusBar(this, false);
    }
}
