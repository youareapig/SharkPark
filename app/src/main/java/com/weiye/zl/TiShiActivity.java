package com.weiye.zl;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TiShiActivity extends AutoLayoutActivity {

    @BindView(R.id.mycourseBack)
    RelativeLayout mycourseBack;
    @BindView(R.id.wodeyuyue)
    RelativeLayout wodeyuyue;
    @BindView(R.id.wuyuyue)
    RelativeLayout wuyuyue;
    private Unbinder unbinder;
    private SharedPreferences sharedPreferences;
    private String userTimes, userType, userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ti_shi);
        unbinder = ButterKnife.bind(this);
        init();
    }

    private void init() {
        sharedPreferences = getSharedPreferences("UserTag", MODE_PRIVATE);
        userTimes = sharedPreferences.getString("usertimes", "未知");
        userType = sharedPreferences.getString("usertype", "未知");
        userID = sharedPreferences.getString("userid", "未知");
        //TODO 非会员用户
        if (userType.equals("3")) {
            if (userTimes.equals("0")) {
                wodeyuyue.setVisibility(View.VISIBLE);
                wuyuyue.setVisibility(View.GONE);
            } else {
                wodeyuyue.setVisibility(View.GONE);
                wuyuyue.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.mycourseBack)
    public void onViewClicked() {
        finish();
    }
}
