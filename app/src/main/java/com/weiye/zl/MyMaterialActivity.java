package com.weiye.zl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.OptionPicker;

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
    Button save;
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
                OptionPicker picker = new OptionPicker(this, new String[]{
                        "男", "女"
                });
                picker.setOffset(2);
                picker.setTextSize(24);
                picker.setTextColor(Color.BLACK);
                picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(String option) {
                        sexText.setText(option);
                    }


                });
                picker.show();
                break;
            case R.id.age:
                DatePicker picker1 = new DatePicker(this, DatePicker.YEAR_MONTH_DAY);
                picker1.setRange(1990, 2017);
                picker1.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String year, String month, String day) {
                        //TODO 点击确定监听事件
                        Log.d("date", "日期：" + year + month + day);
                        ageText.setText(year + "" + "-" + month + "-" + day);
                    }
                });
                picker1.show();
                break;
            case R.id.save:
                stringSex = sexText.getText().toString();
                String sex;
                if (stringSex.equals("男")) {
                    sex = "0";
                } else {
                    sex = "1";
                }
                updateUserInfo(sex);
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
        final CustomProgressDialog customProgressDialog = new CustomProgressDialog(this, "玩命加载中...", R.drawable.frame,R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "User/editUser");
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
                        Intent intent=new Intent(MyMaterialActivity.this,MainActivity.class);
                        intent.putExtra("fTag",3);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(MyMaterialActivity.this, "资料更新失败，请稍后再试", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(MyMaterialActivity.this, "更新用户信息失败", Toast.LENGTH_SHORT).show();
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
        final CustomProgressDialog customProgressDialog = new CustomProgressDialog(this, "玩命加载中...", R.drawable.frame,R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "User/userInfo");
        params.addBodyParameter("id", userID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                GetUserInfo bean = gson.fromJson(result, GetUserInfo.class);
                if (bean.getCode()==3000){
                    if (bean.getData().getNickname()==null){
                        nameText.setText(" ");
                    }else {
                        nameText.setText(bean.getData().getNickname().toString());
                    }
                    if (bean.getData().getSex().equals("0")){
                        sexText.setText("男");
                    }else {
                        sexText.setText("女");
                    }
                    if (bean.getData().getBirthday()==null){
                        ageText.setText("2001-2-2");
                    }else {
                        ageText.setText(bean.getData().getBirthday().toString());
                    }
                }

//                String str = bean.getRows().get(0).getCSRQ();
                //字符串截取
//                String[] s = str.split(" ");
//                String ss = s[0];
//                ageText.setText(ss);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(MyMaterialActivity.this, "获取用户信息失败", Toast.LENGTH_SHORT).show();
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
