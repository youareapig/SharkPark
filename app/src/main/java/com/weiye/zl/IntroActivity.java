package com.weiye.zl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.weiye.data.XYJJBean;
import com.weiye.utils.SingleModleUrl;
import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import qiu.niorgai.StatusBarCompat;

public class IntroActivity extends AutoLayoutActivity {
    private WebView webView;
    private RelativeLayout back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        webView = (WebView) findViewById(R.id.webview);
        back = (RelativeLayout) findViewById(R.id.back3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        visit();
    }

    private void visit() {
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "TAB_XXJJDataService.ashx?op=getTAB_XXJJ");
        params.addBodyParameter("start", "1");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("tag", "H5代码" + result);
                Gson gson = new Gson();
                XYJJBean bean = gson.fromJson(result, XYJJBean.class);
                String h5 = bean.getRows().get(0).getJJMS();
                webView.loadDataWithBaseURL("about:blank", h5, "text/html", "utf-8", null);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

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
