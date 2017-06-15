package com.weiye.zl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.weiye.utils.SingleModleUrl;
import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.qqtheme.framework.picker.OptionPicker;

public class SubmitActivity extends AutoLayoutActivity {
    @BindView(R.id.submitBack)
    RelativeLayout submitBack;

    @BindView(R.id.submit1)
    Button submit1;
    @BindView(R.id.name)
    RelativeLayout name;
    @BindView(R.id.nc1)
    TextView nc1;
    @BindView(R.id.nameText5)
    EditText nameText5;
    @BindView(R.id.name1)
    RelativeLayout name1;
    @BindView(R.id.go)
    ImageView go;
    @BindView(R.id.sexText5)
    TextView sexText5;
    @BindView(R.id.sex3)
    RelativeLayout sex3;
    @BindView(R.id.nc2)
    TextView nc2;
    @BindView(R.id.ageText5)
    EditText ageText5;
    @BindView(R.id.name3)
    RelativeLayout name3;
    @BindView(R.id.go2)
    ImageView go2;
    @BindView(R.id.fumuText5)
    TextView fumuText5;
    @BindView(R.id.jiazhang)
    RelativeLayout jiazhang;
    @BindView(R.id.telText5)
    EditText telText5;
    @BindView(R.id.name2)
    RelativeLayout name2;
    private Unbinder unbinder;
    private String kcid, userID, stringJZ, stringTl;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        unbinder = ButterKnife.bind(this);
        Intent intent = getIntent();
        kcid = intent.getStringExtra("kcid");
        sharedPreferences = getSharedPreferences("UserTag", MODE_PRIVATE);
        userID = sharedPreferences.getString("userid", "未知");
        Log.d("tag", "收到的id" + kcid);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.submitBack, R.id.sex3, R.id.jiazhang, R.id.submit1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.submitBack:
                finish();
                break;
            case R.id.sex3:
                OptionPicker picker = new OptionPicker(this, new String[]{
                        "男", "女"
                });
                picker.setOffset(2);
                picker.setTextSize(24);
                picker.setTextColor(Color.BLACK);
                picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(String option) {
                        sexText5.setText(option);
                    }


                });
                picker.show();
                break;
            case R.id.jiazhang:
                OptionPicker picker1 = new OptionPicker(this, new String[]{
                        "爸爸", "妈妈"
                });
                picker1.setOffset(2);
                picker1.setTextSize(24);
                picker1.setTextColor(Color.BLACK);
                picker1.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(String option) {
                        fumuText5.setText(option);
                    }


                });
                picker1.show();
                break;
            case R.id.submit1:
                stringJZ = fumuText5.getText().toString().trim();
                stringTl = telText5.getText().toString().trim();
                if (TextUtils.isEmpty(nameText5.getText().toString()) || TextUtils.isEmpty(sexText5.getText().toString()) || TextUtils.isEmpty(ageText5.getText().toString()) || TextUtils.isEmpty(stringJZ) || TextUtils.isEmpty(stringTl)) {
                    Toast.makeText(SubmitActivity.this, "请完善信息", Toast.LENGTH_SHORT).show();
                } else {
                    init();
                }
                break;
        }
    }

    private void init() {
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "TAB_KCYYDataService.ashx?op=UpdateTAB_KCYY");
        params.addBodyParameter("YHID", userID);
        params.addBodyParameter("JZLX", stringJZ);
        params.addBodyParameter("JZDH", stringTl);
        params.addBodyParameter("KCID", kcid);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("tag", "提交信息" + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("tag", "提交信息失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }
}
