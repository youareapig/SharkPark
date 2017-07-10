package com.weiye.zl;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.weiye.data.IntroBean;
import com.weiye.myview.CustomProgressDialog;
import com.weiye.utils.SingleModleUrl;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;
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
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        webView = (WebView) findViewById(R.id.webview);
        back = (RelativeLayout) findViewById(R.id.back3);
        title= (TextView) findViewById(R.id.introTitle);
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
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Index/detailLst");
        params.addBodyParameter("tp", "1");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                IntroBean bean = gson.fromJson(result, IntroBean.class);
                if (bean.getCode() == 1000) {
                    title.setText(bean.getData().getTitle());
                    WebSettings webSettings = webView.getSettings();
                    //TODO 适配手机屏幕
                    webSettings.setLoadWithOverviewMode(true);
                    webSettings.setUseWideViewPort(true);
                    webSettings.setTextZoom(250);
                    String h5 = bean.getData().getIntrotext();
                    //webView.loadDataWithBaseURL("about:blank", html + h5, "text/html", "utf-8", null);
                    webView.loadDataWithBaseURL(null, getNewContent(h5), "text/html", "utf-8", null);
                    webView.setWebViewClient(new WebViewClient());
                }if (bean.getCode() == -1000) {
                    Toast.makeText(IntroActivity.this, "暂无更多介绍", Toast.LENGTH_SHORT).show();
                }


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
        for (Element element : elements) {
            element.attr("width", "100%").attr("height", "auto");
        }
        return doc.toString();
    }

}