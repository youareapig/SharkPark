package com.weiye.zl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.weiye.data.GetUserInfo;
import com.weiye.myview.CustomProgressDialog;
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
import cn.addapp.pickers.picker.DatePicker;
import cn.addapp.pickers.picker.SinglePicker;

public class MyMaterialActivity extends AutoLayoutActivity {
    @BindView(R.id.materialBack)
    RelativeLayout materialBack;
    @BindView(R.id.nameText)
    EditText nameText;
    @BindView(R.id.name)
    RelativeLayout name;
    @BindView(R.id.go)
    ImageView go;
    @BindView(R.id.sexText)
    TextView sexText;
    @BindView(R.id.sex)
    RelativeLayout sex;
    @BindView(R.id.go1)
    ImageView go1;
    @BindView(R.id.ageText)
    TextView ageText;
    @BindView(R.id.age)
    RelativeLayout age;
    @BindView(R.id.save)
    TextView save;
    @BindView(R.id.main25)
    LinearLayout main25;
    @BindView(R.id.nc)
    TextView nc;
    @BindView(R.id.tvTel)
    TextView tvTel;
    @BindView(R.id.etTel)
    TextView etTel;
    @BindView(R.id.tel)
    RelativeLayout tel;
    private Unbinder unbinder;
    private String stringName, stringSex, stringDate;
    private String userID;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_material);
        unbinder = ButterKnife.bind(this);
        //TODO 输入框光标问题
        nameText.setSelection(nameText.getText().length());
        nameText.setCursorVisible(false);
        nameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameText.setCursorVisible(true);
                nameText.setSelection(nameText.getText().length());
            }
        });
        sharedPreferences = getSharedPreferences("UserTag", MODE_PRIVATE);
        userID = sharedPreferences.getString("userid", "未知");
        getUserInfo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.materialBack, R.id.name, R.id.sex, R.id.age, R.id.save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.materialBack:
                finish();
                break;
            case R.id.name:
                break;
            case R.id.sex:
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
                        sexText.setText(item);
                    }
                });
                picker.show();
                break;
            case R.id.age:
                final DatePicker picker1 = new DatePicker(this);
                picker1.setCanLoop(false);
                picker1.setWheelModeEnable(true);
                picker1.setTopPadding(15);
                picker1.setRangeStart(1900, 8, 29);
                picker1.setRangeEnd(2100, 1, 11);
                picker1.setSelectedItem(2017, 9, 24);
                picker1.setWeightEnable(true);
                LineConfig config1 = new LineConfig();
                config1.setColor(Color.parseColor("#000000"));//线颜色
                config1.setAlpha(140);//线透明度
                picker1.setSelectedTextColor(Color.BLACK);
                picker1.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String year, String month, String day) {
                        ageText.setText(year + " - " + month + " - " + day);
                    }
                });
                picker1.setOnWheelListener(new DatePicker.OnWheelListener() {
                    @Override
                    public void onYearWheeled(int index, String year) {
                        picker1.setTitleText(year + "-" + picker1.getSelectedMonth() + "-" + picker1.getSelectedDay());
                    }

                    @Override
                    public void onMonthWheeled(int index, String month) {
                        picker1.setTitleText(picker1.getSelectedYear() + "-" + month + "-" + picker1.getSelectedDay());
                    }

                    @Override
                    public void onDayWheeled(int index, String day) {
                        picker1.setTitleText(picker1.getSelectedYear() + "-" + picker1.getSelectedMonth() + "-" + day);
                    }
                });
                picker1.show();
                break;
            case R.id.save:
                if (TextUtils.isEmpty(nameText.getText().toString().trim())) {
                    Toast.makeText(MyMaterialActivity.this, "请填写您的昵称", Toast.LENGTH_SHORT).show();
                } else {
                    stringSex = sexText.getText().toString();
                    String sex;
                    if (stringSex.equals("男")) {
                        sex = "0";
                    } else {
                        sex = "1";
                    }
                    updateUserInfo(sex);
                }

                break;
        }
    }

    /**
     * 性别 0表示男， 1表示女  不能用中文
     */
    private void updateUserInfo(String userSex) {
        stringName = nameText.getText().toString().trim();
        stringSex = sexText.getText().toString().trim();
        stringDate = ageText.getText().toString().trim();
        final CustomProgressDialog customProgressDialog = new CustomProgressDialog(this, null, R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Member/editUser");
        params.addBodyParameter("id", userID);
        params.addBodyParameter("nickname", stringName);
        params.addBodyParameter("sex", userSex);
        params.addBodyParameter("birthday", stringDate);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.getString("code").equals("3003")) {
                        Toast.makeText(MyMaterialActivity.this, "资料更新成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MyMaterialActivity.this, MainActivity.class);
                        intent.putExtra("fTag", 3);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    } else if (jsonObject.getString("code").equals("-3003")) {
                        Toast.makeText(MyMaterialActivity.this, "您未做任何修改！", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MyMaterialActivity.this, "资料更新失败，请稍后再试", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(MyMaterialActivity.this, "网络不佳，请稍后再试！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                customProgressDialog.cancel();
            }
        });

    }

    //TODO 获取用户信息
    private void getUserInfo() {
        main25.setVisibility(View.GONE);
        final CustomProgressDialog customProgressDialog = new CustomProgressDialog(this, null, R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Member/userInfo");
        params.addBodyParameter("id", userID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                main25.setVisibility(View.VISIBLE);
                Gson gson = new Gson();
                GetUserInfo bean = gson.fromJson(result, GetUserInfo.class);
                if (bean.getCode() == 3000) {
                    if (bean.getData().getNickname() != null) {
                        nameText.setText(bean.getData().getNickname().toString());
                    }
                    if (bean.getData().getSex() != null) {
                        if (bean.getData().getSex().equals("0")) {
                            sexText.setText("男");
                        } else {
                            sexText.setText("女");
                        }
                    }
                    if (bean.getData().getBirthday() != null) {
                        ageText.setText(bean.getData().getBirthday().toString());
                    }
                    etTel.setText(bean.getData().getTel());
                }

//                String str = bean.getRows().get(0).getCSRQ();
                //字符串截取
//                String[] s = str.split(" ");
//                String ss = s[0];
//                ageText.setText(ss);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(MyMaterialActivity.this, "网络不佳，请稍后再试", Toast.LENGTH_SHORT).show();
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
