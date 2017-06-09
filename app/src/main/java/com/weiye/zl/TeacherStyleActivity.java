package com.weiye.zl;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.adapter.BannerAdapter;
import com.weiye.data.InfoBean;
import com.weiye.myview.CustomProgressDialog;
import com.weiye.myview.ObservableScrollView;
import com.weiye.utils.SingleModleUrl;
import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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
    TextView teacherProduct;
    @BindView(R.id.main3)
    LinearLayout main3;
    private Unbinder unbinder;
    private int height;
    private List<InfoBean.RowsBean> bannerList;
    private ImageView[] indexTips, indexBannerImage;
    private String teacherID, name, product;
    private CustomProgressDialog customProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_style);
        unbinder = ButterKnife.bind(this);
        Intent intent = getIntent();
        teacherID = intent.getStringExtra("teacherID");
        name = intent.getStringExtra("teacherName");
        product = intent.getStringExtra("teacherProduct");
        teacherName.setText(name);
        teacherProduct.setText(product);
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
        customProgressDialog = new CustomProgressDialog(this, "玩命加载中...", R.drawable.frame,R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        main3.setVisibility(View.GONE);
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "TAB_LXXXDataService.ashx?op=getTAB_LXSPTXXX");
        params.addBodyParameter("LXID", teacherID);
        params.addBodyParameter("start", "0");
        params.addBodyParameter("LX", "2");
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                main3.setVisibility(View.VISIBLE);
                Gson gson = new Gson();
                InfoBean benaInfo = gson.fromJson(result, InfoBean.class);
                bannerList = benaInfo.getRows();

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
                    ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl() + bannerList.get(i).getTXLJ(), imageView);

                }
                teacherStyleBanner.setOnPageChangeListener(TeacherStyleActivity.this);
                teacherStyleBanner.setAdapter(new BannerAdapter(indexBannerImage));
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(TeacherStyleActivity.this, "获取老师信息失败", Toast.LENGTH_SHORT).show();
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
}
