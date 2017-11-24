package com.weiye.zl;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.weiye.data.SubjectStationBean;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ScienceStationActivity extends AutoLayoutActivity {
    @BindView(R.id.scienceStationImg)
    RelativeLayout scienceStationImg;
    @BindView(R.id.scienceStation_BJ)
    ImageView scienceStationBJ;
    @BindView(R.id.scienceStation_title)
    TextView scienceStationTitle;
    @BindView(R.id.scienceStation_Time)
    TextView scienceStationTime;
    @BindView(R.id.scienceStation_Content)
    WebView scienceStationContent;
    @BindView(R.id.back)
    ImageView back;
    private Unbinder unbinder;
    private String kcID, img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science_station);
        unbinder = ButterKnife.bind(this);
        Intent intent = getIntent();
        kcID = intent.getStringExtra("id");
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
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
                img = bean.getData().getPic();
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

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
