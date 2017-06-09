package com.weiye.zl;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.qqtheme.framework.picker.OptionPicker;

public class SubmitActivity extends AutoLayoutActivity {
    @BindView(R.id.submitBack)
    RelativeLayout submitBack;
    @BindView(R.id.name)
    RelativeLayout name;
    @BindView(R.id.nc1)
    TextView nc1;
    @BindView(R.id.nameText)
    EditText nameText;
    @BindView(R.id.name1)
    RelativeLayout name1;
    @BindView(R.id.go)
    ImageView go;
    @BindView(R.id.sexText)
    TextView sexText;
    @BindView(R.id.sex3)
    RelativeLayout sex3;
    @BindView(R.id.nc2)
    TextView nc2;
    @BindView(R.id.nameText3)
    EditText nameText3;
    @BindView(R.id.name3)
    RelativeLayout name3;
    @BindView(R.id.go2)
    ImageView go2;
    @BindView(R.id.sexText2)
    TextView sexText2;
    @BindView(R.id.jiazhang)
    RelativeLayout jiazhang;
    @BindView(R.id.nameText2)
    EditText nameText2;
    @BindView(R.id.name2)
    RelativeLayout name2;
    @BindView(R.id.submit1)
    Button submit1;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        unbinder = ButterKnife.bind(this);
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
                        sexText.setText(option);
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
                        sexText2.setText(option);
                    }


                });
                picker1.show();
                break;
            case R.id.submit1:
                break;
        }
    }
}
