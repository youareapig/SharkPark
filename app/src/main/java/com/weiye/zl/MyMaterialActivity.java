package com.weiye.zl;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.OptionPicker;
import qiu.niorgai.StatusBarCompat;

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
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_material);
        StatusBarCompat.translucentStatusBar(this, false);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.materialBack, R.id.name, R.id.sex, R.id.age})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.materialBack:
                finish();
                break;
            case R.id.name:
                break;
            case R.id.sex:
                OptionPicker picker =new OptionPicker(this,new String[]{
                   "男","女"
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
                picker1.setRange(1990,2017);
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
        }
    }
}
