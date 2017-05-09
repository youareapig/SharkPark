package com.weiye.zl;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.weiye.myview.CustomProgressDialog;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import qiu.niorgai.StatusBarCompat;

public class IdealActivity extends AutoLayoutActivity {
    @BindView(R.id.IdealBack)
    RelativeLayout IdealBack;
    @BindView(R.id.inputIdeal)
    EditText inputIdeal;
    @BindView(R.id.submit)
    Button submit;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideal);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.IdealBack, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.IdealBack:
                finish();
                break;
            case R.id.submit:
                CustomProgressDialog customProgressDialog=new CustomProgressDialog(this,"玩命加载中...",R.drawable.frame);
                customProgressDialog.setCanceledOnTouchOutside(false);
                customProgressDialog.show();
                break;
        }
    }
}
