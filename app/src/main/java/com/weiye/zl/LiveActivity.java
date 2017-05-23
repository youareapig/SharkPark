package com.weiye.zl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zhy.autolayout.AutoLayoutActivity;

public class LiveActivity extends AutoLayoutActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        webView = (WebView) findViewById(R.id.live);
        webView.loadUrl("http://www.xiaomaizhibo.com/cloudlive/live.html?m=5150&code=10360&at=1495183704&sign=970eac2561c661102b36e69c861284b3ad769f0d");
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                // TODO Auto-generated method stub
//                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
//                view.loadUrl(url);
//                return false;
//            }
//        });
    }
}

