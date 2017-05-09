package com.weiye.zl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.weiye.data.AboutBean;
import com.weiye.myview.CustomProgressDialog;
import com.weiye.utils.SingleModleUrl;
import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import qiu.niorgai.StatusBarCompat;

public class AboutActivity extends AutoLayoutActivity {
    private RelativeLayout back;
    private TextView aboutText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        aboutText= (TextView) findViewById(R.id.aboutText);
        back= (RelativeLayout) findViewById(R.id.aboutBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        about();
    }
    private void about(){
        final CustomProgressDialog customProgressDialog=new CustomProgressDialog(this,"玩命加载中...",R.drawable.frame);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params=new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl()+"TAB_GYWMDataService.ashx?op=getTAB_GYWM");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.v("tag",result);
                Gson gson=new Gson();
                AboutBean bean=gson.fromJson(result,AboutBean.class);
                aboutText.setText(bean.getRows().get(0).getGYWM());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.v("tag","请求失败");
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
}
