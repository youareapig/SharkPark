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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class ShiZiActivity extends AutoLayoutActivity {
    private WebView webView;
    private RelativeLayout back;
    private TextView shiziTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shi_zi);
        webView = (WebView) findViewById(R.id.shiziweb);
        back = (RelativeLayout) findViewById(R.id.shiziBack);
        shiziTitle= (TextView) findViewById(R.id.shiziTitle);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        visit();
    }

    private void visit() {
        final CustomProgressDialog customProgressDialog = new CustomProgressDialog(this, null, R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Index/detailLst");
        params.addBodyParameter("tp","2");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                IntroBean bean = gson.fromJson(result, IntroBean.class);
                if (bean.getCode() == 1000) {
                    //shiziTitle.setText(bean.getData().getTitle());
                    WebSettings webSettings = webView.getSettings();
                    //TODO 适配手机屏幕
                    webSettings.setLoadWithOverviewMode(true);
                    webSettings.setUseWideViewPort(true);
                    webSettings.setTextZoom(250);
                    String h5 = bean.getData().getIntrotext();
                    //webView.loadDataWithBaseURL("about:blank", html + h5, "text/html", "utf-8", null);
                    webView.loadDataWithBaseURL(null, getNewContent(h5), "text/html", "utf-8", null);
                    Log.d("tag","师资--------------------->"+h5);
                    webView.setWebViewClient(new WebViewClient());
                }if (bean.getCode() == -1000) {
                    Toast.makeText(ShiZiActivity.this, "暂无更多介绍", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(ShiZiActivity.this, "数据加载失败", Toast.LENGTH_SHORT).show();
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
