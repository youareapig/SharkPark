package com.weiye.zl;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.picker.SinglePicker;

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
    TextView yySubmit;
    @BindView(R.id.main10)
    LinearLayout main10;
    @BindView(R.id.isyuyue)
    ScrollView isyuyue;
    private Unbinder unbinder;
    private String kcid, userID, stringyyName, stringyyAge, stringyySex, stringyyTel, sexID, userType, userTimes;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        unbinder = ButterKnife.bind(this);
        Intent intent = getIntent();
        kcid = intent.getStringExtra("kcid");
        sharedPreferences = getSharedPreferences("UserTag", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        userID = sharedPreferences.getString("userid", "未知");
        userType = sharedPreferences.getString("usertype", "未知");
        userTimes = sharedPreferences.getString("usertimes", "1");
        yyNameInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yyNameInput.setSelection(yyNameInput.getText().length());
            }
        });

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
                final AlertDialog dialog1 = new AlertDialog.Builder(this).create();
                LayoutInflater inflater1 = getLayoutInflater();
                View view1 = inflater1.inflate(R.layout.sureback, null);
                dialog1.setView(view1);
                dialog1.setCanceledOnTouchOutside(true);
                dialog1.show();
                view1.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                view1.findViewById(R.id.cancle).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.cancel();
                    }
                });
                break;
            case R.id.yySex:
                SinglePicker<String> picker = new SinglePicker<>(this, new String[]{"男", "女"
                });
                picker.setCanLoop(true);//不禁用循环
                picker.setTopBackgroundColor(Color.parseColor("#ffffff"));
                picker.setTopHeight(50);
                picker.setWeightEnable(true);
                picker.setWeightWidth(1);
                picker.setHeight(600);
                picker.setTopLineColor(Color.parseColor("#eeeeee"));
                picker.setTopLineHeight(1);
                picker.setTitleTextColor(Color.BLACK);
                picker.setTitleTextSize(12);
                picker.setCancelTextColor(Color.parseColor("#000000"));
                picker.setCancelTextSize(13);
                picker.setSubmitTextColor(Color.parseColor("#000000"));
                picker.setSubmitTextSize(13);
                picker.setSelectedTextColor(Color.parseColor("#000000"));
                picker.setUnSelectedTextColor(Color.parseColor("#888888"));
                LineConfig config = new LineConfig();
                config.setColor(Color.parseColor("#000000"));//线颜色
                config.setAlpha(140);//线透明度
                config.setRatio((float) (1.0 / 8.0));//线比率
                picker.setLineConfig(config);
                picker.setItemWidth(100);
                picker.setBackgroundColor(Color.parseColor("#ffffff"));
                picker.setSelectedIndex(0);
                picker.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        yySexInput.setText(item);
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
        final CustomProgressDialog customProgressDialog = new CustomProgressDialog(this, null, R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        main10.setVisibility(View.GONE);
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Member/ucarInfo");
        params.addBodyParameter("uid", userID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                main10.setVisibility(View.VISIBLE);
                Gson gson = new Gson();
                SubmitUserBean bean = gson.fromJson(result, SubmitUserBean.class);
                if (bean.getCode() == 3000) {

                    if (bean.getData().getAge() != null) {
                        yyAgeInput.setText(bean.getData().getAge());
                    }
                    if (bean.getData().getSex() != null) {
                        if (bean.getData().getSex().equals("0")) {
                            yySexInput.setText("男");
                        } else {
                            yySexInput.setText("女");
                        }
                    }
                    if (bean.getData().getTel() != null) {
                        yyTel.setText(bean.getData().getTel());
                    }

                } else {
                    Toast.makeText(SubmitActivity.this, "网络不佳，请稍后再试", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(SubmitActivity.this, "网络不佳，请稍后再试", Toast.LENGTH_SHORT).show();
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
        final CustomProgressDialog customProgressDialog = new CustomProgressDialog(this, null, R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Member/addCar");
        params.addBodyParameter("uid", userID);
        params.addBodyParameter("truename", stringyyName);
        params.addBodyParameter("sex", sexID);
        params.addBodyParameter("age", stringyyAge);
        params.addBodyParameter("tel", stringyyTel);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.getString("code").equals("3007")) {
                        final AlertDialog dialog = new AlertDialog.Builder(SubmitActivity.this).create();
                        LayoutInflater inflater = getLayoutInflater();
                        View v = inflater.inflate(R.layout.submitsuccess, null);
                        dialog.setView(v);
                        //Todo 设置对话框透明度
                        Window window = dialog.getWindow();
                        WindowManager.LayoutParams layoutParams = window.getAttributes();
                        layoutParams.alpha = 0.6f;
                        window.setAttributes(layoutParams);

                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();

                        editor.putString("usertimes", "0");
                        editor.commit();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(SubmitActivity.this, MainActivity.class);
                                intent.putExtra("fTag", 0);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        }, 3000);


                    } else if (jsonObject.getString("code").equals("-3006")) {
                        Toast.makeText(SubmitActivity.this, "不能重复预约！", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SubmitActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(SubmitActivity.this, "网络不佳，请稍后再试", Toast.LENGTH_SHORT).show();
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
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    //TODO 重写返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            final AlertDialog dialog1 = new AlertDialog.Builder(this).create();
            LayoutInflater inflater1 = getLayoutInflater();
            View view1 = inflater1.inflate(R.layout.sureback, null);
            dialog1.setView(view1);
            dialog1.setCanceledOnTouchOutside(true);
            dialog1.show();
            view1.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            view1.findViewById(R.id.cancle).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog1.cancel();
                }
            });
            return true;
        }
        return false;
    }
}
