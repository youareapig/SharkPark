package com.weiye.zl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.autolayout.AutoLayoutActivity;

import qiu.niorgai.StatusBarCompat;

public class AboutActivity extends AutoLayoutActivity {
    private RelativeLayout back;
    private TextView aboutText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        StatusBarCompat.translucentStatusBar(this, false);
        aboutText= (TextView) findViewById(R.id.aboutText);
        back= (RelativeLayout) findViewById(R.id.aboutBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
