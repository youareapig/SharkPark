package com.weiye.zl;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.data.AllHuodongBean;
import com.weiye.myview.CustomProgressDialog;
import com.weiye.myview.ObservableScrollView;
import com.weiye.utils.SingleModleUrl;
import com.zhy.autolayout.AutoLayoutActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;

public class SchoolWeiChatActivity extends AutoLayoutActivity {
    @BindView(R.id.scrollview2)
    ScrollView scrollview2;
    @BindView(R.id.back1)
    RelativeLayout back1;
    @BindView(R.id.share1)
    RelativeLayout share1;
    @BindView(R.id.title1)
    RelativeLayout title1;
    @BindView(R.id.huodongweb1)
    WebView huodongweb1;
    private Unbinder unbinder;
    private String weixinUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_next);
        unbinder = ButterKnife.bind(this);
        Intent intent = getIntent();
        weixinUrl = intent.getStringExtra("wuxin");
        huodongweb1.loadUrl(weixinUrl);
        huodongweb1.setWebViewClient(new WebViewClient());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://www.sharkpark.cn/");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("http://www.sharkpark.cn/");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(weixinUrl);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment(weixinUrl);
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(weixinUrl);
        //oks.setImageUrl(SingleModleUrl.singleModleUrl().getImgUrl() + img);
        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            @Override
            public void onShare(Platform platform, Platform.ShareParams paramsToShare) {
                if ("QZone".equals(platform.getName())) {
                    paramsToShare.setTitle(null);
                    paramsToShare.setTitleUrl(null);
                }
                if ("SinaWeibo".equals(platform.getName())) {
                    paramsToShare.setUrl(null);
                    paramsToShare.setText(weixinUrl);
                }
                if ("Wechat".equals(platform.getName())) {
                    Bitmap imageData = BitmapFactory.decodeResource(getResources(), R.mipmap.logo);
                    paramsToShare.setImageData(imageData);
                }
                if ("WechatMoments".equals(platform.getName())) {
                    Bitmap imageData = BitmapFactory.decodeResource(getResources(), R.mipmap.logo);
                    paramsToShare.setImageData(imageData);
                }

            }
        });

// 启动分享GUI
        oks.show(this);
    }

    @OnClick({R.id.back1, R.id.share1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.share1:
                showShare();
                break;
        }
    }
}
