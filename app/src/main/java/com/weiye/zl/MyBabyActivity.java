package com.weiye.zl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.weiye.data.BabyInfoBean;
import com.weiye.mycourse.MyCoruseActivity;
import com.weiye.myview.CustomProgressDialog;
import com.weiye.utils.SingleModleUrl;
import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyBabyActivity extends AutoLayoutActivity {
    @BindView(R.id.babyback)
    RelativeLayout babyback;
    @BindView(R.id.baby_name)
    TextView babyName;
    @BindView(R.id.baby_sex)
    TextView babySex;
    @BindView(R.id.baby_bir)
    TextView babyBir;
    @BindView(R.id.baby_school)
    TextView babySchool;
    @BindView(R.id.baby_grade)
    TextView babyGrade;
    @BindView(R.id.babycourse)
    RelativeLayout babycourse;
    @BindView(R.id.babay_bj)
    RelativeLayout babayBj;
    @BindView(R.id.layout)
    LinearLayout layout;
    private Unbinder unbinder;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String userType, babyID, mTag, gID;
    private CustomProgressDialog customProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_baby);
        unbinder = ButterKnife.bind(this);
        init();
        getBabyInfo();
    }

    private void init() {
        sharedPreferences = getSharedPreferences("UserTag", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        userType = sharedPreferences.getString("usertype", "未知");
        Intent intent = getIntent();
        babyID = intent.getStringExtra("babyId");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.babyback, R.id.babycourse, R.id.babay_bj})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.babyback:
                finish();
                break;
            case R.id.babycourse:
                Intent intent = new Intent(MyBabyActivity.this, MyCoruseActivity.class);
                editor.putString("babyId", babyID);
                editor.commit();
                startActivity(intent);
                break;
            case R.id.babay_bj:
                Intent intent1 = new Intent(MyBabyActivity.this, SubjectActivity.class);
                startActivity(intent1);
                break;
        }
    }

    private void getBabyInfo() {
        layout.setVisibility(View.GONE);
        customProgressDialog = new CustomProgressDialog(this, null, R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Member/mybabyInfo");
        params.addBodyParameter("bid", babyID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                BabyInfoBean bean = gson.fromJson(result, BabyInfoBean.class);
                if (bean.getCode() == 3000) {
                    layout.setVisibility(View.VISIBLE);
                    gID = bean.getData().getGid() + "";
                    editor.putString("ggid", gID);
                    editor.commit();
                    babyName.setText(bean.getData().getTruename());
                    babyBir.setText(bean.getData().getBirthday());
                    if (bean.getData().getLocation() == 1) {
                        babySchool.setText("高新校区");
                    } else {
                        babySchool.setText("少年宫");
                    }
                    if (bean.getData().getSbid() == 1) {
                        mTag = "大一";
                    } else if (bean.getData().getSbid() == 2) {
                        mTag = "大二";
                    } else if (bean.getData().getSbid() == 3) {
                        mTag = "大三";
                    }
                    babyGrade.setText(bean.getData().getGname() + mTag);
                    if (bean.getData().getSex() == 1) {
                        babySex.setText("女");
                    } else if (bean.getData().getSex() == 0) {
                        babySex.setText("男");
                    }

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                customProgressDialog.cancel();
            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
