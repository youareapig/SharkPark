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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.data.SubjectStationBean;
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
import qiu.niorgai.StatusBarCompat;

public class ScienceStationActivity extends AutoLayoutActivity implements ObservableScrollView.ScrollViewListener {
    @BindView(R.id.scienceStationImg)
    RelativeLayout scienceStationImg;
    @BindView(R.id.scienceStation_scrollview)
    ObservableScrollView scienceStationScrollview;
    @BindView(R.id.scienceStationBack)
    RelativeLayout scienceStationBack;
    @BindView(R.id.scienceStationShare)
    RelativeLayout scienceStationShare;
    @BindView(R.id.scienceStation_BJ)
    ImageView scienceStationBJ;
    @BindView(R.id.scienceStation_title)
    TextView scienceStationTitle;
    @BindView(R.id.scienceStation_Time)
    TextView scienceStationTime;
    @BindView(R.id.toubu)
    RelativeLayout toubu;
    @BindView(R.id.scienceStation_Content)
    WebView scienceStationContent;
    private Unbinder unbinder;
    private int height;
    private String kcID,img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science_station);
        StatusBarCompat.translucentStatusBar(this, false);
        unbinder = ButterKnife.bind(this);
        Intent intent = getIntent();
        kcID = intent.getStringExtra("id");
        init();
        changTitle();
    }

    private void changTitle() {
        scienceStationScrollview.smoothScrollTo(0, 20);
        scienceStationTitle.setBackgroundColor(Color.argb(0, 0xfd, 0x91, 0x5b));
        ViewTreeObserver observer = scienceStationImg.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                scienceStationImg.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height = scienceStationImg.getHeight();
                scienceStationImg.getWidth();
                scienceStationScrollview.setScrollViewListener(ScienceStationActivity.this);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= height) {
            float scale = (float) y / height;
            float alpha = (255 * scale);
            toubu.setBackgroundColor(Color.argb((int) alpha, 49, 189, 240));
        }
    }

    @OnClick({R.id.scienceStationShare, R.id.scienceStationBack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.scienceStationShare:
                break;
            case R.id.scienceStationBack:
                finish();
                break;

        }

    }


    private void init() {
        final CustomProgressDialog customProgressDialog = new CustomProgressDialog(this, null, R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Index/parkDetail");
        params.addBodyParameter("id", kcID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                SubjectStationBean bean = gson.fromJson(result, SubjectStationBean.class);
                img=bean.getData().getPic();
                if (bean.getCode() == 1000) {
                    Glide.with(ScienceStationActivity.this)
                            .load(SingleModleUrl.singleModleUrl().getImgUrl() + img)
                            .centerCrop()
                            .placeholder(R.mipmap.hui)
                            .error(R.mipmap.hui)
                            .into(scienceStationBJ);
                    scienceStationTime.setText(bean.getData().getAddtime());
                    scienceStationTitle.setText(bean.getData().getTitle());
                    WebSettings webSettings = scienceStationContent.getSettings();
                    webSettings.setLoadWithOverviewMode(true);
                    webSettings.setUseWideViewPort(true);
                    webSettings.setTextZoom(240);
                    scienceStationContent.loadDataWithBaseURL(null, getNewContent(bean.getData().getContent()), "text/html", "utf-8", null);
                    scienceStationContent.setWebViewClient(new WebViewClient());
                } else {
                    Toast.makeText(ScienceStationActivity.this, "暂时没介绍", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(ScienceStationActivity.this, "网络不佳，请稍后再试", Toast.LENGTH_SHORT).show();
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
