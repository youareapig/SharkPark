package com.weiye.zl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import qiu.niorgai.StatusBarCompat;

public class SettingActivity extends AutoLayoutActivity {
    @BindView(R.id.materialBack)
    RelativeLayout materialBack;
    @BindView(R.id.about)
    RelativeLayout about;
    @BindView(R.id.ideal)
    RelativeLayout ideal;
    @BindView(R.id.go)
    ImageView go;
    @BindView(R.id.updateversion)
    RelativeLayout updateversion;
    @BindView(R.id.btnloginout)
    Button btnloginout;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        StatusBarCompat.translucentStatusBar(this, false);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.materialBack, R.id.about, R.id.ideal, R.id.updateversion, R.id.btnloginout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.materialBack:
                finish();
                break;
            case R.id.about:
                Intent intent=new Intent(SettingActivity.this,AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.ideal:
                Intent intent1=new Intent(SettingActivity.this,IdealActivity.class);
                startActivity(intent1);
                break;
            case R.id.updateversion:
                break;
            case R.id.btnloginout:
                break;
        }
    }
}
