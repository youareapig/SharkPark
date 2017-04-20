package com.weiye.zl;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.weiye.adapter.BannerAdapter;
import com.weiye.myview.ObservableScrollView;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    private Unbinder unbinder;
    private int height;
    private List<Integer> bannerList;
    private ImageView[] indexTips, indexBannerImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_style);
        StatusBarCompat.translucentStatusBar(this, false);
        unbinder = ButterKnife.bind(this);
        changTitle();
        setBanner();
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

    private void setBanner() {
        bannerList = new ArrayList<>();
        bannerList.add(R.mipmap.aaa);
        bannerList.add(R.mipmap.aaaa);

        indexTips = new ImageView[bannerList.size()];
        for (int i = 0; i < indexTips.length; i++) {
            ImageView imageView = new ImageView(this);
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
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            indexBannerImage[i] = imageView;
            imageView.setImageResource(bannerList.get(i));
        }
        teacherStyleBanner.setOnPageChangeListener(this);
        teacherStyleBanner.setAdapter(new BannerAdapter(indexBannerImage));
       // teacherStyleBanner.setCurrentItem((indexBannerImage.length)*100,true);


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
}
