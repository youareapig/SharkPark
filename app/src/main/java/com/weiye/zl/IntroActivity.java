package com.weiye.zl;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.weiye.data.XYJJBean;
import com.weiye.myview.CustomProgressDialog;
import com.weiye.utils.SingleModleUrl;
import com.zhy.autolayout.AutoLayoutActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


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
        final CustomProgressDialog customProgressDialog = new CustomProgressDialog(this, "玩命加载中...", R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "TAB_XXJJDataService.ashx?op=getTAB_XXJJ");
        params.addBodyParameter("start", "1");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("tag", "H5代码" + result);
                Gson gson = new Gson();
                XYJJBean bean = gson.fromJson(result, XYJJBean.class);
                String h5 = bean.getRows().get(0).getJJMS();
                WebSettings webSettings = webView.getSettings();
                //TODO 适配手机屏幕
                webSettings.setLoadWithOverviewMode(true);
                webSettings.setUseWideViewPort(true);
                //webView.loadDataWithBaseURL("about:blank", html + h5, "text/html", "utf-8", null);
                webView.loadDataWithBaseURL(null, getNewContent(h5), "text/html", "utf-8", null);
                webView.setWebViewClient(new WebViewClient());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(IntroActivity.this, "数据加载失败", Toast.LENGTH_SHORT).show();
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
    //TODO 屏幕适配
    private String getNewContent(String htmltext) {
        Document doc = Jsoup.parse(htmltext);
        Elements elements = doc.getElementsByTag("img");
        for (Element element:elements){
            element.attr("width", "100%").attr("height", "auto");
        }
        return doc.toString();
    }

}