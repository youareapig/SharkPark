package com.weiye.zl;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.utils.L;
import com.weiye.data.SubmitUserBean;
import com.weiye.myview.CustomProgressDialog;
import com.weiye.utils.ClassPathResource;
import com.weiye.utils.SingleModleUrl;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;
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
    @BindView(R.id.yyName)
    TextView yyName;
    @BindView(R.id.yyNameInput)
    EditText yyNameInput;
    @BindView(R.id.go4)
    ImageView go;
    @BindView(R.id.yySexInput)
    TextView yySexInput;
    @BindView(R.id.yySex)
    RelativeLayout yySex;
    @BindView(R.id.yyAgeInput)
    EditText yyAgeInput;
    @BindView(R.id.yyAge)
    RelativeLayout yyAge;
    @BindView(R.id.yyTel)
    EditText yyTel;
    @BindView(R.id.yySubmit)
    Button yySubmit;
    @BindView(R.id.main10)
    LinearLayout main10;
    @BindView(R.id.isyuyue)
    ScrollView isyuyue;
    @BindView(R.id.noyuyue)
    TextView noyuyue;
    private Unbinder unbinder;
    private String kcid, userID, stringyyName, stringyyAge, stringyySex, stringyyTel, sexID;
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
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.submitBack, R.id.yyNameInput, R.id.yySex, R.id.yyAge, R.id.yyTel, R.id.yySubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.submitBack:
                finish();
                break;
            case R.id.yySex:
                OptionPicker picker = new OptionPicker(this, new String[]{
                        "男", "女"
                });
                picker.setOffset(2);
                picker.setTextSize(24);
                picker.setTextColor(Color.BLACK);
                picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(String option) {
                        yyAgeInput.setText(option);
                    }
                });
                picker.show();
                break;

            case R.id.yySubmit:
                stringyyTel = yyTel.getText().toString().trim();
                stringyyName = yyNameInput.getText().toString().trim();
                stringyyAge = yyAgeInput.getText().toString().trim();
                stringyySex = yySexInput.getText().toString().trim();
                if (TextUtils.isEmpty(stringyyName) || TextUtils.isEmpty(stringyySex) || TextUtils.isEmpty(stringyyAge) || TextUtils.isEmpty(stringyyTel)) {
                    Toast.makeText(SubmitActivity.this, "请完善信息", Toast.LENGTH_SHORT).show();
                } else {
                    ClassPathResource classPathResource = new ClassPathResource();
                    boolean b = classPathResource.isMobileNO(stringyyTel);
                    if (b == true) {
                        submitVist();
                    } else {
                        Toast.makeText(SubmitActivity.this, "请填写正确的电话号码", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    private void init() {
        final CustomProgressDialog customProgressDialog = new CustomProgressDialog(this, "玩命加载中....", R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        main10.setVisibility(View.GONE);
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "User/ucarInfo");
        params.addBodyParameter("uid", userID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("tag","是否预约"+result);
                main10.setVisibility(View.VISIBLE);
                Gson gson = new Gson();
                SubmitUserBean bean = gson.fromJson(result, SubmitUserBean.class);
                if (bean.getCode() == 3000) {
                    isyuyue.setVisibility(View.VISIBLE);
                    noyuyue.setVisibility(View.GONE);
                    if (bean.getData().getTruename() != null) {
                        yyNameInput.setText(bean.getData().getTruename().toString());
                    }
                    if (bean.getData().getAge() != null) {
                        yyAgeInput.setText(bean.getData().getAge() + "岁");
                    }
                    if (bean.getData().getSex().equals("0")) {
                        yySexInput.setText("男");
                    } else {
                        yySexInput.setText("女");
                    }
                    yyTel.setText(bean.getData().getTelnumber());
                } else {
                    isyuyue.setVisibility(View.GONE);
                    noyuyue.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(SubmitActivity.this, "获取用户信息失败", Toast.LENGTH_SHORT).show();
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

    private void submitVist() {
        if (stringyySex.equals("男")) {
            sexID = "0";
        } else {
            sexID = "1";
        }
        final CustomProgressDialog customProgressDialog = new CustomProgressDialog(this, "玩命加载中....", R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "User/addCar");
        params.addBodyParameter("uid", userID);
        params.addBodyParameter("truename", stringyyName);
        params.addBodyParameter("sex", sexID);
        params.addBodyParameter("age", "24");
        params.addBodyParameter("tel", stringyyTel);
        Log.d("tag","提交参数说明"+userID+"  "+sexID+"  "+stringyyAge+"  "+stringyyName+"  "+stringyyTel);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("tag","提交"+result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.getString("code").equals("3007")) {
                        final AlertDialog dialog = new AlertDialog.Builder(SubmitActivity.this).create();
                        LayoutInflater inflater = getLayoutInflater();
                        View v = inflater.inflate(R.layout.submitsuccess, null);
                        dialog.setView(v);
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();
                        v.findViewById(R.id.haode).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(v.getContext(), CourseActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                    } else {
                        Toast.makeText(SubmitActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(SubmitActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
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


}
