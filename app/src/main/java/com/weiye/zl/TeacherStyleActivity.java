package com.weiye.zl;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.adapter.BannerAdapter;
import com.weiye.data.TeacherBean;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import qiu.niorgai.StatusBarCompat;

public class TeacherStyleActivity extends AutoLayoutActivity implements ObservableScrollView.ScrollViewListener, ViewPager.OnPageChangeListener {
    @BindView(R.id.teacherStyle_banner)
    ViewPager teacherStyleBanner;
    @BindView(R.id.teacherStyle_scroll)
    ObservableScrollView teacherStyleScroll;
    @BindView(R.id.teacherStyle_Back)
    RelativeLayout teacherStyleBack;
    @BindView(R.id.teacherStyle_title)
    RelativeLayout teacherStyleTitle;
    @BindView(R.id.teacherStyle_top)
    RelativeLayout teacherStyleTop;
    @BindView(R.id.teacherStyle_group)
    LinearLayout teacherStyleGroup;
    @BindView(R.id.teacherName)
    TextView teacherName;
    @BindView(R.id.teacherProduct)
    WebView teacherProduct;
    @BindView(R.id.main3)
    LinearLayout main3;
    private Unbinder unbinder;
    private int height;
    private List<String> bannerList;
    private ImageView[] indexTips, indexBannerImage;
    private String teacherID;
    private CustomProgressDialog customProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_style);
        StatusBarCompat.translucentStatusBar(this, false);
        unbinder = ButterKnife.bind(this);
        Intent intent = getIntent();
        teacherID = intent.getStringExtra("teacherID");
        changTitle();
        visit();
    }

    private void changTitle() {
        teacherStyleScroll.smoothScrollTo(0, 20);
        teacherStyleTitle.setBackgroundColor(Color.argb(0, 0xfd, 0x91, 0x5b));
        ViewTreeObserver observer = teacherStyleTop.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                teacherStyleTop.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height = teacherStyleTop.getHeight();
                teacherStyleTop.getWidth();
                teacherStyleScroll.setScrollViewListener(TeacherStyleActivity.this);
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
            teacherStyleTitle.setBackgroundColor(Color.argb((int) alpha, 49, 189, 240));
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setImageBackground(position % indexBannerImage.length);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setImageBackground(int selectItems) {
        for (int i = 0; i < indexTips.length; i++) {
            if (i == selectItems) {
                indexTips[i].setBackgroundResource(R.drawable.viewpage_check);
            } else {
                indexTips[i].setBackgroundResource(R.drawable.viewpage_goods);
            }
        }
    }

    private void visit() {
        customProgressDialog = new CustomProgressDialog(this, null, R.drawable.frame,R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        main3.setVisibility(View.GONE);
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Index/teacherInfo");
        params.addBodyParameter("tid",teacherID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                main3.setVisibility(View.VISIBLE);
                Gson gson = new Gson();
                TeacherBean bean=gson.fromJson(result,TeacherBean.class);
                bannerList=bean.getData().getImgs();
                teacherName.setText(bean.getData().getUname());

                WebSettings webSettings = teacherProduct.getSettings();
                //TODO 适配手机屏幕
                webSettings.setLoadWithOverviewMode(true);
                webSettings.setUseWideViewPort(true);
                webSettings.setTextZoom(250);
                teacherProduct.loadDataWithBaseURL(null, getNewContent(bean.getData().getDesc()), "text/html", "utf-8", null);
                teacherProduct.setWebViewClient(new WebViewClient());
                indexTips = new ImageView[bannerList.size()];
                for (int i = 0; i < indexTips.length; i++) {
                    ImageView imageView = new ImageView(TeacherStyleActivity.this);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(15, 15);
                    layoutParams.leftMargin = 10;
                    layoutParams.rightMargin = 10;
                    imageView.setLayoutParams(layoutParams);
                    indexTips[i] = imageView;
                    if (i == 0) {
                        indexTips[i].setBackgroundResource(R.drawable.viewpage_check);
                    } else {
                        indexTips[i].setBackgroundResource(R.drawable.viewpage_goods);

                    }

                    teacherStyleGroup.addView(imageView);
                }
                indexBannerImage = new ImageView[bannerList.size()];
                for (int i = 0; i < indexBannerImage.length; i++) {
                    ImageView imageView = new ImageView(TeacherStyleActivity.this);
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    indexBannerImage[i] = imageView;
                    Glide.with(TeacherStyleActivity.this)
                            .load(SingleModleUrl.singleModleUrl().getImgUrl() + bannerList.get(i))
                            .error(R.mipmap.hui)
                            .placeholder(R.mipmap.hui)
                            .centerCrop()
                            .into(imageView);

                }
                teacherStyleBanner.setOnPageChangeListener(TeacherStyleActivity.this);
                teacherStyleBanner.setAdapter(new BannerAdapter(indexBannerImage));
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(TeacherStyleActivity.this, "网络不佳，请稍后再试", Toast.LENGTH_SHORT).show();
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

    @OnClick(R.id.teacherStyle_Back)
    public void onViewClicked() {
        finish();
    }
    private String getNewContent(String htmltext) {
        Document doc = Jsoup.parse(htmltext);
        Elements elements = doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width", "100%").attr("height", "auto");
        }
        return doc.toString();
    }
}
